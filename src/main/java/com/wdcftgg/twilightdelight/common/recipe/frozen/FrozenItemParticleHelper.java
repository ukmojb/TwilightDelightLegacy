package com.wdcftgg.twilightdelight.common.recipe.frozen;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumParticleTypes;

public final class FrozenItemParticleHelper {

    private FrozenItemParticleHelper() {
    }

    public static void spawnFreezingParticles(EntityItem entityItem) {
        if (entityItem == null || !entityItem.world.isRemote || entityItem.getItem().isEmpty()
                || FrozenRecipeManager.findRecipe(entityItem.getItem()) == null
                || !entityItem.world.rand.nextBoolean()) {
            return;
        }

        double x = entityItem.posX + (entityItem.world.rand.nextDouble() - 0.5D) * entityItem.width;
        double y = entityItem.posY + entityItem.world.rand.nextDouble() * entityItem.height;
        double z = entityItem.posZ + (entityItem.world.rand.nextDouble() - 0.5D) * entityItem.width;
        entityItem.world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, x, y, z, 0.0D, 0.01D, 0.0D);
    }
}
