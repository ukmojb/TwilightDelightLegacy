package com.wdcftgg.twilightdelight.common.recipe.frozen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FrozenIngredient {

    private final Item item;
    private final int metadata;
    private final String oreName;

    private FrozenIngredient(Item item, int metadata, String oreName) {
        this.item = item;
        this.metadata = metadata;
        this.oreName = oreName;
    }

    public static FrozenIngredient fromJson(JsonElement json) {
        if (json.isJsonPrimitive()) {
            return fromToken(json.getAsString());
        }

        JsonObject object = json.getAsJsonObject();
        if (object.has("ore")) {
            return forOre(object.get("ore").getAsString());
        }
        if (!object.has("item")) {
            throw new IllegalArgumentException("Frozen ingredient must define 'item' or 'ore'");
        }

        Item item = resolveItem(object.get("item").getAsString());
        int metadata = object.has("meta") ? object.get("meta").getAsInt() : OreDictionary.WILDCARD_VALUE;
        return forItem(item, metadata);
    }

    public static FrozenIngredient tryFromJson(JsonElement json) {
        try {
            return fromJson(json);
        } catch (UnknownItemException ignored) {
            return null;
        }
    }

    public static FrozenIngredient fromToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Frozen ingredient token cannot be empty");
        }

        String value = token.trim();
        if (value.startsWith("ore:")) {
            return forOre(value.substring("ore:".length()));
        }

        int metadata = OreDictionary.WILDCARD_VALUE;
        int separator = value.lastIndexOf('@');
        if (separator > value.indexOf(':')) {
            metadata = Integer.parseInt(value.substring(separator + 1));
            value = value.substring(0, separator);
        }
        return forItem(resolveItem(value), metadata);
    }

    public static FrozenIngredient forItem(Item item, int metadata) {
        if (item == null) {
            throw new IllegalArgumentException("Frozen ingredient item cannot be null");
        }
        return new FrozenIngredient(item, metadata, null);
    }

    public static FrozenIngredient forOre(String oreName) {
        if (oreName == null || oreName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ore Dictionary name cannot be empty");
        }
        return new FrozenIngredient(null, OreDictionary.WILDCARD_VALUE, oreName.trim());
    }

    public boolean matches(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (this.oreName != null) {
            int expectedId = OreDictionary.getOreID(this.oreName);
            for (int oreId : OreDictionary.getOreIDs(stack)) {
                if (oreId == expectedId) {
                    return true;
                }
            }
            return false;
        }
        return stack.getItem() == this.item
                && (this.metadata == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == this.metadata);
    }

    public List<ItemStack> getMatchingStacks() {
        if (this.oreName != null) {
            List<ItemStack> stacks = new ArrayList<>();
            for (ItemStack stack : OreDictionary.getOres(this.oreName, false)) {
                if (!stack.isEmpty()) {
                    stacks.add(stack.copy());
                }
            }
            return Collections.unmodifiableList(stacks);
        }

        int displayMetadata = this.metadata == OreDictionary.WILDCARD_VALUE ? 0 : this.metadata;
        return Collections.singletonList(new ItemStack(this.item, 1, displayMetadata));
    }

    private static Item resolveItem(String itemId) {
        ResourceLocation id = new ResourceLocation(itemId);
        Item item = ForgeRegistries.ITEMS.getValue(id);
        if (item == null) {
            throw new UnknownItemException(id);
        }
        return item;
    }

    private static final class UnknownItemException extends IllegalArgumentException {
        private UnknownItemException(ResourceLocation id) {
            super("Unknown item '" + id + "'");
        }
    }
}
