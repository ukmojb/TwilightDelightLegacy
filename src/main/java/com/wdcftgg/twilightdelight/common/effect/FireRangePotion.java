package com.wdcftgg.twilightdelight.common.effect;

import com.wdcftgg.twilightdelight.common.registry.TwilightDelightPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FireRangePotion extends Potion {

    public FireRangePotion() {
        super(false, 0xFF6600);
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(6.0D, entityLivingBase)) {
            int fireSeconds = 5;
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) entity;
                PotionEffect activeEffect = target.getActivePotionEffect(TwilightDelightPotions.FIRE_RANGE);
                fireSeconds = activeEffect == null ? 5 : activeEffect.getAmplifier() + 1;
            }

            entity.setFire(fireSeconds);
            entityLivingBase.extinguish();
            if (entity instanceof EntityItem) {
                entity.extinguish();
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
