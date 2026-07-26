package com.wdcftgg.twilightdelight.mixin;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.TFConfig;
import twilightforest.TwilightForestMod;
import twilightforest.client.TFClientEvents;
import twilightforest.client.model.entity.ModelTFYetiAlpha;
import twilightforest.client.renderer.tileentity.TileEntityTFTrophyRenderer;
import twilightforest.enums.BossVariant;
import twilightforest.tileentity.TileEntityTFTrophy;

@Mixin(TileEntityTFTrophyRenderer.class)
public abstract class AlphaYetiTrophyRendererMixin extends TileEntitySpecialRenderer<TileEntityTFTrophy> {

    @Shadow(remap = false)
    private ItemStack stack;

    @Shadow(remap = false)
    private ItemCameraTransforms.TransformType transform;

    @Unique
    private static final ResourceLocation twilightdelight$ALPHA_YETI_TEXTURE =
            TwilightForestMod.getModelTexture("yetialpha.png");

    @Unique
    private final ModelTFYetiAlpha twilightdelight$alphaYetiModel = new ModelTFYetiAlpha();

    @Inject(method = "render(Ltwilightforest/tileentity/TileEntityTFTrophy;DDDFIF)V",
            at = @At(value = "INVOKE",
            target = "Ltwilightforest/enums/BossVariant;getVariant(I)Ltwilightforest/enums/BossVariant;",
            ordinal = 2), remap = false)
    private void twilightdelight$renderAlphaYetiTrophy(TileEntityTFTrophy trophy, double x, double y, double z,
                                                       float partialTicks, int destroyStage, float alpha,
                                                       CallbackInfo ci) {
        int metadata = trophy != null ? trophy.getSkullType() : this.stack.getMetadata();
        if (BossVariant.getVariant(metadata) != BossVariant.ALPHA_YETI) {
            return;
        }

        float rotation = trophy != null ? trophy.getSkullRotation() * 360.0F / 16.0F : 0.0F;
        boolean onGround = true;
        if (trophy != null && trophy.getBlockMetadata() != 1) {
            onGround = false;
            switch (trophy.getBlockMetadata() & 7) {
                case 3:
                    rotation = 180.0F;
                    break;
                case 4:
                    rotation = 270.0F;
                    break;
                case 5:
                default:
                    rotation = 90.0F;
                    break;
            }
        } else if (trophy == null && this.transform == ItemCameraTransforms.TransformType.GUI) {
            rotation = TFConfig.rotateTrophyHeadsGui ? TFClientEvents.rotationTicker : 135.0F;
        }

        GlStateManager.pushMatrix();
        GlStateManager.scale(1.0F / 6.0F, 1.0F / 6.0F, 1.0F / 6.0F);
        this.bindTexture(twilightdelight$ALPHA_YETI_TEXTURE);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, onGround ? 2.0F : 1.75F, onGround ? 0.0F : 0.75F);

        boolean rightArmVisible = this.twilightdelight$alphaYetiModel.bipedRightArm.showModel;
        boolean leftArmVisible = this.twilightdelight$alphaYetiModel.bipedLeftArm.showModel;
        this.twilightdelight$alphaYetiModel.bipedRightArm.showModel = false;
        this.twilightdelight$alphaYetiModel.bipedLeftArm.showModel = false;
        this.twilightdelight$alphaYetiModel.bipedBody.render(0.0625F);
        this.twilightdelight$alphaYetiModel.bipedRightArm.showModel = rightArmVisible;
        this.twilightdelight$alphaYetiModel.bipedLeftArm.showModel = leftArmVisible;
        GlStateManager.popMatrix();
    }
}
