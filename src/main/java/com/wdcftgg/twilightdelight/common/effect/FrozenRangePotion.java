package com.wdcftgg.twilightdelight.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import twilightforest.potions.TFPotions;

public class FrozenRangePotion extends Potion {

    public FrozenRangePotion() {
        super(false, 0x00CCEC);
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if (entityLivingBase.world.isRemote) {
            return;
        }
        for (Entity entity : RangeEffectHelper.getEntitiesInRange(6.0D, entityLivingBase)) {
            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TFPotions.frosty, 20, 4));
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
