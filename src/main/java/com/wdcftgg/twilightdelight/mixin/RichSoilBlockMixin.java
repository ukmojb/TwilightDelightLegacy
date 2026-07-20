package com.wdcftgg.twilightdelight.mixin;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockRichSoil;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.util.RichSoilConversionHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.TFBlocks;
import twilightforest.enums.PlantVariant;

import java.util.Random;

@Mixin(BlockRichSoil.class)
public abstract class RichSoilBlockMixin {

    @Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
    private void twilightdelight$convertTwilightPlants(World world, BlockPos pos, IBlockState state,
                                                       Random random, CallbackInfo ci) {
        if (world.isRemote) {
            return;
        }
        RichSoilConversionHelper.convert(world, pos, random);
        IBlockState above = world.getBlockState(pos.up());
        if (above.getBlock() == TFBlocks.twilight_plant
                && above.getValue(BlockTFPlant.VARIANT) == PlantVariant.MUSHGLOOM) {
            world.setBlockState(pos.up(), TwilightDelightBlocks.MUSHGLOOM_COLONY.getDefaultState(), 3);
            ci.cancel();
        }
    }
}
