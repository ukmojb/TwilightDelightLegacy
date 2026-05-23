package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonDrinkItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;

public class ThornRoseTeaItem extends AddonDrinkItem {

    private static final DamageSource THORN_ROSE_TEA = new DamageSource("twilightdelight.thorn_rose_tea");

    public ThornRoseTeaItem(FoodItemApi.DrinkItemSettings settings) {
        super(settings);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        entityLiving.attackEntityFrom(THORN_ROSE_TEA, 4.0F);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
