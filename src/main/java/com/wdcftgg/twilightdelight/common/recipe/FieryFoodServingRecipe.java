package com.wdcftgg.twilightdelight.common.recipe;

import com.wdcftgg.farmersdelightlegacy.common.item.ItemCookingPot;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityCookingPot;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class FieryFoodServingRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        ItemStack cookingPotStack = ItemStack.EMPTY;
        ItemStack containerStack = ItemStack.EMPTY;
        Item fieryPot = Item.getItemFromBlock(TwilightDelightBlocks.FIERY_COOKING_POT);
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            ItemStack selected = inv.getStackInSlot(slot);
            if (selected.isEmpty()) {
                continue;
            }
            if (cookingPotStack.isEmpty() && selected.getItem() == fieryPot
                    && !TileEntityCookingPot.getMealFromItem(selected).isEmpty()) {
                cookingPotStack = selected;
                continue;
            }
            if (containerStack.isEmpty()) {
                containerStack = selected;
                continue;
            }
            return false;
        }
        if (cookingPotStack.isEmpty() || containerStack.isEmpty()) {
            return false;
        }
        ItemStack meal = TileEntityCookingPot.getMealFromItem(cookingPotStack);
        ItemStack requiredContainer = ItemCookingPot.inferContainer(cookingPotStack, meal);
        return !requiredContainer.isEmpty() && ItemStack.areItemsEqual(requiredContainer, containerStack);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Item fieryPot = Item.getItemFromBlock(TwilightDelightBlocks.FIERY_COOKING_POT);
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            ItemStack selected = inv.getStackInSlot(slot);
            if (selected.isEmpty() || selected.getItem() != fieryPot) {
                continue;
            }
            ItemStack meal = TileEntityCookingPot.getMealFromItem(selected).copy();
            if (!meal.isEmpty()) {
                meal.setCount(1);
                return meal;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        Item fieryPot = Item.getItemFromBlock(TwilightDelightBlocks.FIERY_COOKING_POT);
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            ItemStack selected = inv.getStackInSlot(slot);
            if (selected.isEmpty()) {
                continue;
            }
            if (selected.getItem().hasContainerItem(selected)) {
                remainders.set(slot, selected.getItem().getContainerItem(selected));
            } else if (selected.getItem() == fieryPot) {
                remainders.set(slot, TileEntityCookingPot.consumeServingFromItem(selected));
            }
        }
        return remainders;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
