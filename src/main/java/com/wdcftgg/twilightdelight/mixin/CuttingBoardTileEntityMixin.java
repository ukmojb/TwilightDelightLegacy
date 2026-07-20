package com.wdcftgg.twilightdelight.mixin;

import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityCuttingBoard;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(TileEntityCuttingBoard.class)
public abstract class CuttingBoardTileEntityMixin extends TileEntity {

    @Inject(method = "processStoredItem", at = @At("RETURN"), cancellable = true, remap = false)
    private void twilightdelight$cookFieryKnifeResults(ItemStack toolStack,
                                                       CallbackInfoReturnable<List<ItemStack>> cir) {
        if (toolStack.getItem() != TwilightDelightItems.FIERY_KNIFE || cir.getReturnValue().isEmpty()) {
            return;
        }
        List<ItemStack> transformed = new ArrayList<>();
        int cookedCount = 0;
        for (ItemStack stack : cir.getReturnValue()) {
            ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(stack);
            if (smelted.isEmpty()) {
                transformed.add(stack);
                continue;
            }
            ItemStack result = smelted.copy();
            result.setCount(stack.getCount());
            transformed.add(result);
            cookedCount += stack.getCount();
        }
        if (cookedCount > 0 && this.world != null) {
            this.world.playSound(null, this.pos, SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (this.world instanceof WorldServer) {
                ((WorldServer) this.world).spawnParticle(EnumParticleTypes.FLAME,
                        this.pos.getX() + 0.5D, this.pos.getY() + 0.2D, this.pos.getZ() + 0.5D,
                        cookedCount, 0.1D, 0.05D, 0.1D, 0.0D);
            }
        }
        cir.setReturnValue(transformed);
    }
}
