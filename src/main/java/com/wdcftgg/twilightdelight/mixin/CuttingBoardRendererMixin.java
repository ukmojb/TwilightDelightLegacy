package com.wdcftgg.twilightdelight.mixin;

import com.wdcftgg.farmersdelightlegacy.client.render.TileEntityCuttingBoardRenderer;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityCuttingBoard;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.item.TFItems;

@Mixin(TileEntityCuttingBoardRenderer.class)
public abstract class CuttingBoardRendererMixin {

    @Inject(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"))
    private void twilightdelight$translateTrophy(TileEntityCuttingBoard tileEntity, double x, double y, double z,
                                                float partialTicks, int destroyStage, float alpha, CallbackInfo ci) {
        ItemStack stack = tileEntity.getStoredItem();
        if (stack.getItem() != TFItems.trophy) {
            return;
        }
        if (stack.getMetadata() == 3) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.7F, 0.0F);
        } else {
            GlStateManager.translate(0.0F, 0.12F, 0.0F);
        }
    }
}
