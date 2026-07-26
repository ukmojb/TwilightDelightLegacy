package com.wdcftgg.twilightdelight.common.event;

import com.wdcftgg.farmersdelightlegacy.common.item.ItemKnife;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import twilightforest.entity.boss.EntityTFYetiAlpha;
import twilightforest.entity.boss.EntityTFUrGhast;
import twilightforest.entity.boss.EntityTFMinoshroom;
import twilightforest.entity.EntityTFMinotaur;
import twilightforest.enums.BossVariant;
import twilightforest.item.TFItems;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID)
public final class TwilightDelightLootEvents {

    private TwilightDelightLootEvents() {
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntityLiving() instanceof EntityTFYetiAlpha) {
            addDrop(event, new ItemStack(TFItems.trophy, 1, BossVariant.ALPHA_YETI.ordinal()));
        }

        if (event.getEntityLiving() instanceof EntityTFUrGhast) {
            int count = 2 + event.getEntityLiving().getRNG().nextInt(7);
            addDrop(event, new ItemStack(TFItems.experiment_115, count));
        }

        if (!(event.getSource().getTrueSource() instanceof EntityPlayer)
                || (!(event.getEntityLiving() instanceof EntityTFMinotaur)
                && !(event.getEntityLiving() instanceof EntityTFMinoshroom))) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
        if (player.getHeldItemMainhand().getItem() != TFItems.minotaur_axe
                && !ItemKnife.isKnife(player.getHeldItemMainhand())) {
            return;
        }
        float chance = event.getEntityLiving() instanceof EntityTFMinoshroom ? 1.0F : 0.3F;
        if (event.getEntityLiving().getRNG().nextFloat() <= chance) {
            addDrop(event, new ItemStack(event.getEntityLiving().isBurning()
                    ? TwilightDelightItems.COOKED_TOMAHAWK_SMEAK
                    : TwilightDelightItems.RAW_TOMAHAWK_SMEAK));
        }
    }

    private static void addDrop(LivingDropsEvent event, ItemStack stack) {
        EntityItem entityItem = new EntityItem(event.getEntityLiving().world,
                event.getEntityLiving().posX,
                event.getEntityLiving().posY,
                event.getEntityLiving().posZ,
                stack);
        event.getDrops().add(entityItem);
    }
}
