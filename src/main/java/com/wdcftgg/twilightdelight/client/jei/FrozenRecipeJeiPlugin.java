package com.wdcftgg.twilightdelight.client.jei;

import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenIngredient;
import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenRecipe;
import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenRecipeCatalysts;
import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenRecipeManager;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public final class FrozenRecipeJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new FrozenRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        FrozenRecipeManager.initialize();
        List<FrozenRecipeWrapper> wrappers = new ArrayList<>();
        for (FrozenRecipe recipe : FrozenRecipeManager.getRecipes()) {
            wrappers.add(new FrozenRecipeWrapper(recipe));
        }
        registry.addRecipes(wrappers, FrozenRecipeCategory.UID);

        for (String token : FrozenRecipeCatalysts.getTokens()) {
            try {
                for (ItemStack catalyst : FrozenIngredient.fromToken(token).getMatchingStacks()) {
                    if (!catalyst.isEmpty()) {
                        registry.addRecipeCatalyst(catalyst, FrozenRecipeCategory.UID);
                    }
                }
            } catch (RuntimeException exception) {
                // A catalyst may belong to optional content that is not registered in this instance.
            }
        }
    }
}
