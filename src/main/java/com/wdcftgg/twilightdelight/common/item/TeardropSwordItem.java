package com.wdcftgg.twilightdelight.common.item;

import com.wdcftgg.twilightdelight.common.registry.TwilightDelightPotions;
import net.minecraft.client.util.ITooltipFlag;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class TeardropSwordItem extends ItemSword implements FireResistantItemSupport {

    public TeardropSwordItem() {
        super(ToolMaterial.DIAMOND);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean result = super.hitEntity(stack, target, attacker);
        target.setFire(15);
        if (!target.world.isRemote && target.world.rand.nextInt(3) == 0) {
            target.addPotionEffect(new PotionEffect(TwilightDelightPotions.TEMPORAL_SADNESS, 100, 0));
        }
        return result;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> attributes = HashMultimap.create(super.getItemAttributeModifiers(equipmentSlot));
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            attributes.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            attributes.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            attributes.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 8.0D, 0));
            attributes.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4D, 0));
        }
        return attributes;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 1536;
    }

    @Override
    public int getItemEnchantability() {
        return 30;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems.EXPERIMENT_113;
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
