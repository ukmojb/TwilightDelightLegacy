package com.wdcftgg.twilightdelight.common.tile;

import com.wdcftgg.farmersdelightlegacy.common.recipe.CampfireCookingRecipe;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityStove;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MazeStoveTileEntity extends TileEntityStove {

    @Override
    public boolean addItem(ItemStack itemStackIn, CampfireCookingRecipe recipe, int slot) {
        if (!super.addItem(itemStackIn, recipe, slot)) {
            return false;
        }
        ResourceLocation resultId = recipe.getResultStack().getItem().getRegistryName();
        if (isTwilightResult(resultId)) {
            setField(slot + 6, Math.max(1, recipe.getCookingTime() / 2));
        }
        return true;
    }

    public static boolean isTwilightResult(ResourceLocation resultId) {
        return resultId != null
                && ("twilightforest".equals(resultId.getNamespace()) || "twilightdelight".equals(resultId.getNamespace()));
    }
}
