package com.wdcftgg.twilightdelight.common.event;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightHistoricalFoodRecipes;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightPotions;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID)
public final class TwilightDelightRegistryEvents {

    private TwilightDelightRegistryEvents() {
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        TwilightDelightBlocks.registerAll(event);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        TwilightDelightBlocks.registerItemBlocks(event);
        TwilightDelightItems.registerAll(event);
    }


    @SubscribeEvent
    public static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
        TwilightDelightRecipes.registerForgeRecipes(event);
        TwilightDelightHistoricalFoodRecipes.registerAll(event);
    }

    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
        TwilightDelightPotions.registerAll(event);
    }
}
