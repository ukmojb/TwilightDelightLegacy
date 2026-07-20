package com.wdcftgg.twilightdelight.mixin;

import com.wdcftgg.twilightdelight.client.AuroraRenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Render.class)
public abstract class RenderAuroraMixin<T extends Entity> {

    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    private void twilightdelight$renderAuroraColor(T entity, CallbackInfoReturnable<Integer> cir) {
        if (AuroraRenderHelper.shouldRender(entity)) {
            cir.setReturnValue(AuroraRenderHelper.getColor(entity));
        }
    }
}
