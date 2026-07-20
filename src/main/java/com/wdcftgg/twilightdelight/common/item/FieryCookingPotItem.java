package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.common.item.ItemCookingPot;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class FieryCookingPotItem extends ItemCookingPot implements FireResistantItemSupport {

    public FieryCookingPotItem(Block block) {
        super(block);
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + new TextComponentTranslation(this.getTranslationKey() + ".tooltip").getFormattedText());
    }
}
