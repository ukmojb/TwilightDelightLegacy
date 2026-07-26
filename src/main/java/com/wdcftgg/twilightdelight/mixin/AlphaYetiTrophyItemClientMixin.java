package com.wdcftgg.twilightdelight.mixin;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.TwilightForestMod;
import twilightforest.client.renderer.tileentity.TileEntityTFTrophyRenderer;
import twilightforest.enums.BossVariant;
import twilightforest.item.ItemTFTrophy;

@Mixin(ItemTFTrophy.class)
public abstract class AlphaYetiTrophyItemClientMixin {

    @Inject(method = "registerModel", at = @At("RETURN"), remap = false)
    private void twilightdelight$registerAlphaYetiTrophyModel(CallbackInfo ci) {
        Item trophy = (Item) (Object) this;
        int metadata = BossVariant.ALPHA_YETI.ordinal();
        ModelResourceLocation model = new ModelResourceLocation(TwilightForestMod.ID + ":trophy_tesr", "inventory");
        ModelLoader.setCustomModelResourceLocation(trophy, metadata, model);
        ForgeHooksClient.registerTESRItemStack(trophy, metadata, TileEntityTFTrophyRenderer.DummyTile.class);
    }
}
