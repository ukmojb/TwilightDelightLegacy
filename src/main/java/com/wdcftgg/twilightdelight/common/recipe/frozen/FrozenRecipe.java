package com.wdcftgg.twilightdelight.common.recipe.frozen;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public final class FrozenRecipe {

    private final ResourceLocation id;
    private final FrozenIngredient ingredient;
    private final ItemStack result;

    public FrozenRecipe(ResourceLocation id, FrozenIngredient ingredient, ItemStack result) {
        if (id == null || ingredient == null || result.isEmpty()) {
            throw new IllegalArgumentException("Frozen recipe id, ingredient and result are required");
        }
        this.id = id;
        this.ingredient = ingredient;
        this.result = result.copy();
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public boolean matches(ItemStack input) {
        return this.ingredient.matches(input);
    }

    public List<ItemStack> getInputStacks() {
        return this.ingredient.getMatchingStacks();
    }

    public ItemStack getResult() {
        return this.result.copy();
    }
}
