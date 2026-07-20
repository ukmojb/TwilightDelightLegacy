package com.wdcftgg.twilightdelight.client;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
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
        ModelLoader.setCustomStateMapper(TwilightDelightBlocks.IRONWOOD_LEAVES,
                new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
    }

    @SubscribeEvent
    public static void onBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return ColorizerFoliage.getFoliageColorBasic();
            }
            return BiomeColorHelper.getFoliageColorAtPos(world, pos);
        }, TwilightDelightBlocks.IRONWOOD_LEAVES);
    }

    @SubscribeEvent
    public static void onItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().registerItemColorHandler(
                (stack, tintIndex) -> ColorizerFoliage.getFoliageColorBasic(),
                Item.getItemFromBlock(TwilightDelightBlocks.IRONWOOD_LEAVES));
    }

    private static void registerModel(Item item) {
        ResourceLocation registryName = item.getRegistryName();
        if (registryName != null) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(registryName, "inventory"));
        }
    }
}
