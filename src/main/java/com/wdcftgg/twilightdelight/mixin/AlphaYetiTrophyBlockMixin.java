package com.wdcftgg.twilightdelight.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.TFSounds;
import twilightforest.block.BlockTFTrophy;
import twilightforest.enums.BossVariant;
import twilightforest.tileentity.TileEntityTFTrophy;

@Mixin(BlockTFTrophy.class)
public abstract class AlphaYetiTrophyBlockMixin {

    @Inject(method = "onBlockActivated", at = @At("HEAD"))
    private void twilightdelight$playAlphaYetiSound(World world, BlockPos pos, IBlockState state,
                                                    EntityPlayer player, EnumHand hand, EnumFacing facing,
                                                    float hitX, float hitY, float hitZ,
                                                    CallbackInfoReturnable<Boolean> cir) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityTFTrophy
                && BossVariant.getVariant(((TileEntityTFTrophy) tileEntity).getSkullType()) == BossVariant.ALPHA_YETI) {
            world.playSound(player, pos, TFSounds.ALPHAYETI_GROWL, SoundCategory.BLOCKS, 1.0F, 16.0F);
        }
    }
}
