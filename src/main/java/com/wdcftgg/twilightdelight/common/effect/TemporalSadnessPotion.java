package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.EnumParticleTypes;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TemporalSadnessPotion extends TwilightDelightPotion {

    public TemporalSadnessPotion() {
        super(true, 0xFFFFFF, "temporal_sadness");
        setPotionName("effect.twilightdelight.temporal_sadness");
        this.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE,
                uuid("temporal_sadness_atk"), -10.0D, 0);
        this.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
                uuid("temporal_sadness_speed"), -0.5D, 1);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.setSprinting(false);
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

    private static String uuid(String path) {
        return UUID.nameUUIDFromBytes(("twilightdelight:" + path).getBytes(StandardCharsets.UTF_8)).toString();
    }
}
