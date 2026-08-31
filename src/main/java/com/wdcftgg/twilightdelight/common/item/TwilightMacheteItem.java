package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.api.knife.ItemKnifeBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TwilightMacheteItem extends ItemKnifeBase {

    private static final double ATTACK_SPEED_MODIFIER = -2.6D;

    public TwilightMacheteItem(Item.ToolMaterial material) {
        super(material, material.getAttackDamage() + 2.0D, ATTACK_SPEED_MODIFIER);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.FORTUNE || super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
