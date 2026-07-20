package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.EnumParticleTypes;

public class FireRangePotion extends TwilightDelightPotion {

    public FireRangePotion() {
        super(false, 0xFF6600, "fire_range");
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.extinguish();
        RangeEffectHelper.spawnRingParticles(entityLivingBase, EnumParticleTypes.FLAME);
        if (entityLivingBase.world.isRemote || entityLivingBase.ticksExisted % 10 != 0) {
            return;
        }
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(entityLivingBase)) {
            if (entity instanceof EntityLivingBase && entity instanceof IMob) {
                entity.setFire(amplifier + 2);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
