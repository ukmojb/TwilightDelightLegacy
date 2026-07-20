package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemFoodTooltip;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TwilightFoodItem extends ItemFoodTooltip implements FireResistantItemSupport {

    private final boolean fast;
    private final boolean fireResistant;
    private final ItemStack container;

    public TwilightFoodItem(FoodItemApi.FoodItemSettings settings, boolean fast, ItemStack container, boolean fireResistant) {
        super(settings.getNutrition(), settings.getSaturation(), false, settings.getFoodEffects(), settings.getExtraTooltipKeys());
        this.fast = fast;
        this.fireResistant = fireResistant;
        this.container = container == null ? ItemStack.EMPTY : container.copy();
        if (settings.isAlwaysEdible()) {
            this.setAlwaysEdible();
        }
        if (settings.getMaxStackSize() > 0) {
            this.setMaxStackSize(settings.getMaxStackSize());
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return this.fast ? 16 : super.getMaxItemUseDuration(stack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return !this.container.isEmpty();
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return this.container.copy();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        ItemStack result = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (this.container.isEmpty() || !(entityLiving instanceof EntityPlayer)) {
            return result;
        }
        EntityPlayer player = (EntityPlayer) entityLiving;
        if (player.capabilities.isCreativeMode) {
            return result;
        }
        ItemStack returnedContainer = this.container.copy();
        if (result.isEmpty()) {
            return returnedContainer;
        }
        if (!player.inventory.addItemStackToInventory(returnedContainer)) {
            player.dropItem(returnedContainer, false);
        }
        return result;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return this.fireResistant;
    }

    @Override
    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemStack) {
        return this.fireResistant ? this.createFireResistantEntity(world, location, itemStack) : null;
    }
}
