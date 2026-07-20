package com.wdcftgg.twilightdelight.client;

import com.wdcftgg.twilightdelight.common.TwilightDelightConfig;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.math.MathHelper;

public final class AuroraRenderHelper {

    private AuroraRenderHelper() {
    }

    public static boolean shouldRender(Entity entity) {
        if (!(entity instanceof EntityItem
                || entity instanceof IProjectile
                || entity instanceof EntityFireball
                || entity instanceof EntityShulkerBullet
                || entity instanceof EntityFishHook
                || entity instanceof EntityLivingBase
                || entity instanceof MultiPartEntityPart)) {
            return false;
        }

        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null
                && player.isPotionActive(TwilightDelightPotions.AURORA_GLOWING)
                && player.getDistanceSq(entity) <= (double) TwilightDelightConfig.getAuroraRange() * TwilightDelightConfig.getAuroraRange()) {
            return true;
        }
        return entity instanceof EntityLivingBase
                && ((EntityLivingBase) entity).isPotionActive(TwilightDelightPotions.AURORA_GLOWING);
    }

    public static int getColor(Entity entity) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        if (player == null) {
            return 0xFFFFFF;
        }
        float partialTicks = minecraft.getRenderPartialTicks();
        double x = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks
                - (player.prevPosX + (player.posX - player.prevPosX) * partialTicks);
        double z = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks
                - (player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks);
        double angleOffset = Math.atan2(x, z) / Math.PI * 30.0D;
        float tick = entity.world.getTotalWorldTime() + partialTicks + (float) angleOffset;
        float hue = tick / TwilightDelightConfig.auroraPeriod;
        return MathHelper.hsvToRGB(hue - (float) Math.floor(hue), 1.0F, 1.0F);
    }
}
