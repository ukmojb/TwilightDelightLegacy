package com.wdcftgg.twilightdelight.common.effect;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TwilightDelightPotion extends Potion {

    private final ResourceLocation iconTexture;

    protected TwilightDelightPotion(boolean isBadEffect, int liquidColor, String iconName) {
        super(isBadEffect, liquidColor);
        this.iconTexture = new ResourceLocation(TwilightDelightLegacy.MOD_ID, "textures/mob_effect/" + iconName + ".png");
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft minecraft) {
        renderIcon(minecraft, x + 6, y + 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft minecraft, float alpha) {
        renderIcon(minecraft, x + 3, y + 3);
    }

    @SideOnly(Side.CLIENT)
    private void renderIcon(Minecraft minecraft, int x, int y) {
        minecraft.getTextureManager().bindTexture(this.iconTexture);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 18, 18, 18.0F, 18.0F);
    }
}
