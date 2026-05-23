package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.farmersdelightlegacy.api.knife.ItemKnifeBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.item.TFItems;

import javax.annotation.Nullable;
import java.util.List;

public class KnightmetalKnifeItem extends ItemKnifeBase implements FireResistantItemSupport {

    public KnightmetalKnifeItem() {
        super(TFItems.TOOL_KNIGHTLY, 3.5D, -2.0D);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(2, attacker);
        IAttributeInstance armorAttribute = target.getEntityAttribute(SharedMonsterAttributes.ARMOR);
        if (armorAttribute != null && armorAttribute.getAttributeValue() > 0.0D) {
            target.attackEntityFrom(net.minecraft.util.DamageSource.causeMobDamage(attacker), 4.0F);
        }
        return true;
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
