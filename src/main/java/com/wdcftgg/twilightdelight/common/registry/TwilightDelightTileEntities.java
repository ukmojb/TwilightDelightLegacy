package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.tile.FieryCookingPotTileEntity;
import com.wdcftgg.twilightdelight.common.tile.MazeStoveTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class TwilightDelightTileEntities {

    private TwilightDelightTileEntities() {
    }

    public static void registerAll() {
        GameRegistry.registerTileEntity(MazeStoveTileEntity.class,
                new ResourceLocation(TwilightDelightLegacy.MOD_ID, "maze_stove"));
        GameRegistry.registerTileEntity(FieryCookingPotTileEntity.class,
                new ResourceLocation(TwilightDelightLegacy.MOD_ID, "fiery_cooking_pot"));
    }
}
