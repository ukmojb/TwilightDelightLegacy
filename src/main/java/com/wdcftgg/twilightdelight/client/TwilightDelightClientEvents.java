package com.wdcftgg.twilightdelight.client;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID, value = Side.CLIENT)
public final class TwilightDelightClientEvents {

    private TwilightDelightClientEvents() {
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Item item : TwilightDelightItems.ITEMS.values()) {
            registerModel(item);
        }
        for (Block block : TwilightDelightBlocks.BLOCKS.values()) {
            registerModel(Item.getItemFromBlock(block));
        }
    }

    private static void registerModel(Item item) {
        ResourceLocation registryName = item.getRegistryName();
        if (registryName != null) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(registryName, "inventory"));
        }
    }
}
