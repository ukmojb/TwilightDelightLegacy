package com.wdcftgg.twilightdelight.common.effect;

import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenItemParticleHelper;
import com.wdcftgg.twilightdelight.common.recipe.frozen.FrozenRecipeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import twilightforest.potions.TFPotions;

public class FrozenRangePotion extends TwilightDelightPotion {

    private static final String FREEZE_TICKS_KEY = "TwilightDelightFreezeTicks";
    private static final String LAST_FREEZE_TICK_KEY = "TwilightDelightLastFreezeTick";
    private static final int TICKS_REQUIRED_TO_FREEZE = 140;

    public FrozenRangePotion() {
        super(false, 0x00CCEC, "frozen_range");
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.removePotionEffect(TFPotions.frosty);
        RangeEffectHelper.spawnRingParticles(entityLivingBase, EnumParticleTypes.SNOW_SHOVEL);
        if (entityLivingBase.ticksExisted % 10 != 0) {
            return;
        }
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(entityLivingBase)) {
            if (entityLivingBase.world.isRemote) {
                if (entity instanceof EntityItem) {
                    FrozenItemParticleHelper.spawnFreezingParticles((EntityItem) entity);
                }
                continue;
            }
            if (entity instanceof EntityLivingBase && entity instanceof IMob) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TFPotions.frosty, 21, 4));
            } else if (entity instanceof EntityItem) {
                freezeItem((EntityItem) entity);
            }
        }
    }

    private static void freezeItem(EntityItem entityItem) {
        ItemStack result = getFrozenResult(entityItem.getItem());
        if (result.isEmpty()) {
            return;
        }

        NBTTagCompound data = entityItem.getEntityData();
        long now = entityItem.world.getTotalWorldTime();
        long lastFreezeTick = data.getLong(LAST_FREEZE_TICK_KEY);
        int freezeTicks = data.getInteger(FREEZE_TICKS_KEY);
        if (lastFreezeTick > 0L && now - lastFreezeTick > 10L) {
            freezeTicks = Math.max(0, freezeTicks - (int) Math.min(Integer.MAX_VALUE, (now - lastFreezeTick - 10L) * 2L));
        }

        freezeTicks += 10;
        if (freezeTicks >= TICKS_REQUIRED_TO_FREEZE) {
            entityItem.setItem(result);
            data.removeTag(FREEZE_TICKS_KEY);
            data.removeTag(LAST_FREEZE_TICK_KEY);
            return;
        }

        data.setInteger(FREEZE_TICKS_KEY, freezeTicks);
        data.setLong(LAST_FREEZE_TICK_KEY, now);
    }

    private static ItemStack getFrozenResult(ItemStack input) {
        return FrozenRecipeManager.getFrozenResult(input);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
