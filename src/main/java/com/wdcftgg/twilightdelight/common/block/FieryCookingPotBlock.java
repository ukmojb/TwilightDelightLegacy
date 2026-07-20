package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockCookingPot;
import com.wdcftgg.twilightdelight.common.tile.FieryCookingPotTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FieryCookingPotBlock extends BlockCookingPot {

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new FieryCookingPotTileEntity();
    }
}
