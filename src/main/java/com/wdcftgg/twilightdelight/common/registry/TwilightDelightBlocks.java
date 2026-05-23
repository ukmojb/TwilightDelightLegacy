package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.common.block.BlockStove;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.TwilightDelightCreativeTab;
import com.wdcftgg.twilightdelight.common.block.TwilightFeastBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public final class TwilightDelightBlocks {

    public static final Map<String, Block> BLOCKS = new LinkedHashMap<>();
    public static final Map<String, ItemBlock> ITEM_BLOCKS = new LinkedHashMap<>();

    public static final Block MAZE_STOVE = register("maze_stove", new BlockStove());
    public static final Block LILY_CHICKEN_BLOCK = registerPlaceableFood("lily_chicken_block", "lily_chicken", true);
    public static final Block FIERY_SNAKES_BLOCK = registerPlaceableFood("fiery_snakes_block", "fiery_snakes", false);

    private TwilightDelightBlocks() {
    }

    public static void registerAll(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS.values()) {
            event.getRegistry().register(block);
        }
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (ItemBlock itemBlock : ITEM_BLOCKS.values()) {
            event.getRegistry().register(itemBlock);
        }
    }

    private static Block register(String path, Block block) {
        return register(path, block, ItemBlock::new);
    }

    private static Block registerPlaceableFood(String path, String servingItemPath, boolean dropsBoneMealWithBowl) {
        FoodItemApi.PlaceableFoodSettings settings = FoodItemApi.PlaceableFoodSettings.builder()
                .maxServings(4)
                .servingItem(servingItemPath)
                .requiredContainer("minecraft:bowl")
                .hasLeftovers(true)
                .strength(0.5F, 0.5F)
                .build();
        Block block = new TwilightFeastBlock(settings, new ResourceLocation(TwilightDelightLegacy.MOD_ID, servingItemPath), dropsBoneMealWithBowl);
        return register(path, block, FoodItemApi::createPlaceableFoodItemBlock);
    }

    private static Block register(String path, Block block, Function<Block, ItemBlock> itemFactory) {
        ResourceLocation registryName = new ResourceLocation(TwilightDelightLegacy.MOD_ID, path);
        block.setRegistryName(registryName);
        block.setTranslationKey(TwilightDelightLegacy.MOD_ID + "." + path);
        block.setCreativeTab(TwilightDelightCreativeTab.TAB);
        BLOCKS.put(path, block);

        ItemBlock itemBlock = itemFactory.apply(block);
        itemBlock.setRegistryName(registryName);
        itemBlock.setTranslationKey(block.getTranslationKey());
        itemBlock.setCreativeTab(TwilightDelightCreativeTab.TAB);
        if ("lily_chicken_block".equals(path) || "fiery_snakes_block".equals(path)) {
            itemBlock.setMaxStackSize(1);
        }
        ITEM_BLOCKS.put(path, itemBlock);
        return block;
    }
}
