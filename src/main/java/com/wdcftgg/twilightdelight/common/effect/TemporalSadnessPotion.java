package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public class TemporalSadnessPotion extends Potion {

    public TemporalSadnessPotion() {
        super(true, 0xFFFFFF);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.setSprinting(false);
        entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30, 1, false, false));
        entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 1, false, false));
        if (!entityLivingBase.world.isRemote) {
            entityLivingBase.world.spawnParticle(EnumParticleTypes.WATER_DROP,
                    entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ,
                    1.0D, 1.0D, 1.0D);
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
