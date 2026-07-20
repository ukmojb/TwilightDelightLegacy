package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.api.knife.ItemKnifeBase;
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

public class FieryKnifeItem extends ItemKnifeBase implements FireResistantItemSupport {

    public FieryKnifeItem() {
        super(TFItems.TOOL_FIERY, 4.5D, -2.0D);
        this.setMaxStackSize(1);
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
        if (enchantment == Enchantments.FIRE_ASPECT) {
            return false;
        }
        return enchantment == Enchantments.FORTUNE || super.canApplyAtEnchantingTable(stack, enchantment);
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
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return this.createFireResistantEntity(world, location, itemstack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + new TextComponentTranslation(this.getTranslationKey() + ".tooltip").getFormattedText());
    }

}
