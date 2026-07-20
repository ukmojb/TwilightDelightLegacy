package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public class PoisonRangePotion extends TwilightDelightPotion {

    public PoisonRangePotion() {
        super(false, 0x007700, "poison_range");
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.removePotionEffect(MobEffects.POISON);
        RangeEffectHelper.spawnRingParticles(entityLivingBase, EnumParticleTypes.VILLAGER_HAPPY);
        if (entityLivingBase.world.isRemote || entityLivingBase.ticksExisted % 10 != 0) {
            return;
        }
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(entityLivingBase)) {
            if (entity instanceof EntityLivingBase && entity instanceof IMob) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 26));
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
