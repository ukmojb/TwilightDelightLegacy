package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
}
