package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockStove;
import com.wdcftgg.twilightdelight.common.tile.MazeStoveTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MazeStoveBlock extends BlockStove {

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MazeStoveTileEntity();
    }
}
