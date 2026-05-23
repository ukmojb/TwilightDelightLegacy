package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PoisonRangePotion extends Potion {

    public PoisonRangePotion() {
        super(false, 0x007700);
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if (entityLivingBase.world.isRemote) {
            return;
        }
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(6.0D, entityLivingBase)) {
            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 20, 5));
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }
}
