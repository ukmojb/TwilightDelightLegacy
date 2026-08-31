package com.wdcftgg.twilightdelight.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.item.TFItems;

import javax.annotation.Nullable;
import java.util.List;

public class FieryMacheteItem extends TwilightMacheteItem implements FireResistantItemSupport {

    public FieryMacheteItem() {
        super(TFItems.TOOL_FIERY);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean result = super.hitEntity(stack, target, attacker);
        if (result) {
            target.setFire(15);
        }
        return result;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.FIRE_ASPECT && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return this.hasFireResistantEntity(stack);
    }

    @Override
    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemStack) {
        return this.createFireResistantEntity(world, location, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(TextFormatting.GRAY + new TextComponentTranslation(this.getTranslationKey() + ".tooltip").getFormattedText());
    }
}
