package com.wdcftgg.twilightdelight.common.recipe.frozen;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FrozenRecipe {

    private final ResourceLocation id;
    private final List<FrozenIngredient> ingredients;
    private final ItemStack result;

    public FrozenRecipe(ResourceLocation id, FrozenIngredient ingredient, ItemStack result) {
        this(id, Collections.singletonList(ingredient), result);
    }

    public FrozenRecipe(ResourceLocation id, List<FrozenIngredient> ingredients, ItemStack result) {
        if (id == null || ingredients == null || ingredients.isEmpty() || ingredients.contains(null) || result.isEmpty()) {
            throw new IllegalArgumentException("Frozen recipe id, ingredient and result are required");
        }
        this.id = id;
        this.ingredients = Collections.unmodifiableList(new ArrayList<>(ingredients));
        this.result = result.copy();
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public boolean matches(ItemStack input) {
        for (FrozenIngredient ingredient : this.ingredients) {
            if (ingredient.matches(input)) {
                return true;
            }
        }
        return false;
    }

    public List<ItemStack> getInputStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        for (FrozenIngredient ingredient : this.ingredients) {
            stacks.addAll(ingredient.getMatchingStacks());
        }
        return Collections.unmodifiableList(stacks);
    }

    public ItemStack getResult() {
        return this.result.copy();
    }
}
