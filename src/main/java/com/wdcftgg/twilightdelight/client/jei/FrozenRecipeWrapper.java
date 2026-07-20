package com.wdcftgg.twilightdelight.client.jei;

import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.Collections;

public final class FrozenRecipeWrapper implements IRecipeWrapper {

    private final FrozenRecipe recipe;

    public FrozenRecipeWrapper(FrozenRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, Collections.singletonList(this.recipe.getInputStacks()));
        ingredients.setOutput(VanillaTypes.ITEM, this.recipe.getResult());
    }

    public FrozenRecipe getRecipe() {
        return this.recipe;
    }
}
