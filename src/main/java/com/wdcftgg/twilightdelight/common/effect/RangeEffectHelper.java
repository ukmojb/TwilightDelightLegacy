package com.wdcftgg.twilightdelight.common.effect;

import com.wdcftgg.twilightdelight.common.TwilightDelightConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.Comparator;
import java.util.List;

public final class RangeEffectHelper {

    private RangeEffectHelper() {
    }

    public static List<Entity> getEntitiesInRange(double range, EntityLivingBase centerEntity) {
        AxisAlignedBB area = centerEntity.getEntityBoundingBox().grow(range);
        List<Entity> entities = centerEntity.world.getEntitiesWithinAABB(Entity.class, area, entity -> entity != null && entity != centerEntity);
        entities.sort(Comparator.comparingDouble(centerEntity::getDistanceSq));
        return entities;
    }

    public static List<Entity> getEntitiesInRange(EntityLivingBase centerEntity) {
        return getEntitiesInRange(TwilightDelightConfig.getEffectRange(), centerEntity);
    }

    public static void spawnRingParticles(EntityLivingBase entity, EnumParticleTypes particleType) {
        if (!entity.world.isRemote || TwilightDelightConfig.getEffectRange() <= 0) {
            return;
        }
        double radius = TwilightDelightConfig.getEffectRange() / Math.sqrt(2.0D);
        double centerY = entity.posY + entity.getEyeHeight() * 0.5D + radius;
        for (int i = 0; i < 5; i++) {
            double angle = entity.world.rand.nextDouble() * Math.PI * 2.0D;
            entity.world.spawnParticle(particleType,
                    entity.posX + Math.cos(angle) * radius,
                    centerY,
                    entity.posZ + Math.sin(angle) * radius,
                    0.0D, 0.0D, 0.0D);
        }
    }
}
