package com.wdcftgg.twilightdelight.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface FireResistantItemSupport {

    default boolean hasFireResistantEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    default Entity createFireResistantEntity(World world, Entity location, ItemStack itemStack) {
        EntityItem entityItem = new FireResistantEntityItem(world, location.posX, location.posY, location.posZ, itemStack.copy());
        entityItem.motionX = location.motionX;
        entityItem.motionY = location.motionY;
        entityItem.motionZ = location.motionZ;
        if (location instanceof EntityItem) {
            EntityItem originalItem = (EntityItem) location;
            entityItem.setPickupDelay(originalItem.writeToNBT(new NBTTagCompound()).getShort("PickupDelay"));
            entityItem.setOwner(originalItem.getOwner());
            entityItem.setThrower(originalItem.getThrower());
        }
        return entityItem;
    }

    class FireResistantEntityItem extends EntityItem {

        public FireResistantEntityItem(World world, double x, double y, double z, ItemStack stack) {
            super(world, x, y, z, stack);
            this.isImmuneToFire = true;
        }

        @Override
        public boolean attackEntityFrom(DamageSource source, float amount) {
            if (source != null && source.isFireDamage()) {
                return false;
            }
            return super.attackEntityFrom(source, amount);
        }

        @Override
        protected void dealFireDamage(int amount) {
        }
    }
}
