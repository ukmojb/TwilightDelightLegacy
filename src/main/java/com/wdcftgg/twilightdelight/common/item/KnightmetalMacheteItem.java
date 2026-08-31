package com.wdcftgg.twilightdelight.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.item.TFItems;

import javax.annotation.Nullable;
import java.util.List;

public class KnightmetalMacheteItem extends TwilightMacheteItem {

    public KnightmetalMacheteItem() {
        super(TFItems.TOOL_KNIGHTLY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(TextFormatting.GRAY + new TextComponentTranslation(this.getTranslationKey() + ".tooltip").getFormattedText());
    }
}
