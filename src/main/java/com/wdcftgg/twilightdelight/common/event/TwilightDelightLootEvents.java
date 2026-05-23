package com.wdcftgg.twilightdelight.common.event;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import twilightforest.entity.boss.EntityTFUrGhast;
import twilightforest.item.TFItems;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID)
public final class TwilightDelightLootEvents {

    private TwilightDelightLootEvents() {
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntityLiving() instanceof EntityTFUrGhast)) {
            return;
        }
        int count = 2 + event.getEntityLiving().getRNG().nextInt(7);
        EntityItem entityItem = new EntityItem(event.getEntityLiving().world,
                event.getEntityLiving().posX,
                event.getEntityLiving().posY,
                event.getEntityLiving().posZ,
                new ItemStack(TFItems.experiment_115, count));
        event.getDrops().add(entityItem);
    }
}
