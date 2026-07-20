package com.wdcftgg.twilightdelight.mixin;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockStove;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntitySkillet;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntitySkillet.class)
public abstract class SkilletTileEntityMixin extends TileEntity {

    @Shadow(remap = false)
    private int cookingTime;

    @Shadow(remap = false)
    private int cookingTimeTotal;

    @Inject(method = "update", at = @At("HEAD"))
    private void twilightdelight$accelerateWithMazeStove(CallbackInfo ci) {
        if (this.world == null || this.world.isRemote || this.cookingTimeTotal <= 0) {
            return;
        }
        IBlockState below = this.world.getBlockState(this.pos.down());
        if (below.getBlock() == TwilightDelightBlocks.MAZE_STOVE && below.getValue(BlockStove.LIT)) {
            this.cookingTime++;
        }
    }
}
