package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.common.block.BlockCabinet;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemMushroomColony;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.TwilightDelightCreativeTab;
import com.wdcftgg.twilightdelight.common.block.MushgloomColonyBlock;
import com.wdcftgg.twilightdelight.common.block.FieryCookingPotBlock;
import com.wdcftgg.twilightdelight.common.block.MazeStoveBlock;
import com.wdcftgg.twilightdelight.common.block.TorchberriesCrateBlock;
import com.wdcftgg.twilightdelight.common.block.TwilightCakeBlock;
import com.wdcftgg.twilightdelight.common.block.TwilightFeastBlock;
import com.wdcftgg.twilightdelight.common.block.TwilightIceCreamBlock;
import com.wdcftgg.twilightdelight.common.block.TwilightPieBlock;
import com.wdcftgg.twilightdelight.common.block.IronwoodLeavesBlock;
import com.wdcftgg.twilightdelight.common.block.IronwoodLogBlock;
import com.wdcftgg.twilightdelight.common.block.IronwoodSaplingBlock;
import com.wdcftgg.twilightdelight.common.item.FieryCookingPotItem;
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

    public static final Block MAZE_STOVE = register("maze_stove", new MazeStoveBlock());
    public static final Block FIERY_COOKING_POT = register("fiery_cooking_pot", new FieryCookingPotBlock(), FieryCookingPotItem::new);
    public static final Block LILY_CHICKEN_BLOCK = registerPlaceableFood("lily_chicken_block", "lily_chicken", true);
    public static final Block FIERY_SNAKES_BLOCK = registerPlaceableFood("fiery_snakes_block", "fiery_snakes", false);
    public static final Block MEEF_WELLINGTON_BLOCK = registerPlaceableFood("meef_wellington_block", "plate_of_meef_wellington", false);
    public static final Block TORCHBERRY_PIE = register("torchberry_pie",
            new TwilightPieBlock(new ResourceLocation(TwilightDelightLegacy.MOD_ID, "torchberry_pie_slice")));
    public static final Block AURORA_PIE = register("aurora_pie",
            new TwilightPieBlock(new ResourceLocation(TwilightDelightLegacy.MOD_ID, "aurora_pie_slice")));
    public static final Block AURORA_CAKE = registerCake("aurora_cake", "aurora_cake_slice");
    public static final Block TORCHBERRY_CAKE = registerCake("torchberry_cake", "torchberry_cake_slice");
    public static final Block PHYTOCHEMICAL_CAKE = registerCake("phytochemical_cake", "phytochemical_cake_slice");
    public static final Block GLACIER_CAKE = registerCake("glacier_cake", "glacier_cake_slice");
    public static final Block AURORA_ICE_CREAM_BLOCK = register("aurora_ice_cream_block", new TwilightIceCreamBlock());
    public static final Block TORCHBERRY_ICE_CREAM_BLOCK = register("torchberry_ice_cream_block", new TwilightIceCreamBlock());
    public static final Block PHYTOCHEMICAL_ICE_CREAM_BLOCK = register("phytochemical_ice_cream_block", new TwilightIceCreamBlock());
    public static final Block GLACIER_ICE_CREAM_BLOCK = register("glacier_ice_cream_block", new TwilightIceCreamBlock());
    public static final Block TORCHBERRIES_CRATE = register("torchberries_crate", new TorchberriesCrateBlock());
    public static final Block MUSHGLOOM_COLONY = register("mushgloom_colony", new MushgloomColonyBlock(), ItemMushroomColony::new);
    public static final Block IRONWOOD_SAPLING = register("ironwood_sapling", new IronwoodSaplingBlock());
    public static final Block IRONWOOD_LOG = register("ironwood_log", new IronwoodLogBlock());
    public static final Block IRONWOOD_LEAVES = register("ironwood_leaves", new IronwoodLeavesBlock());
    public static final Block TWILIGHT_OAK_CABINET = register("twilight_oak_cabinet", new BlockCabinet());
    public static final Block CANOPY_CABINET = register("canopy_cabinet", new BlockCabinet());
    public static final Block DARK_CABINET = register("dark_cabinet", new BlockCabinet());
    public static final Block MANGROVE_CABINET = register("mangrove_cabinet", new BlockCabinet());
    public static final Block MINING_CABINET = register("mining_cabinet", new BlockCabinet());
    public static final Block SORTING_CABINET = register("sorting_cabinet", new BlockCabinet());
    public static final Block TIME_CABINET = register("time_cabinet", new BlockCabinet());
    public static final Block TRANSFORMATION_CABINET = register("transformation_cabinet", new BlockCabinet());

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

    private static Block registerCake(String path, String sliceItemPath) {
        return register(path, new TwilightCakeBlock(new ResourceLocation(TwilightDelightLegacy.MOD_ID, sliceItemPath)));
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
        if ("lily_chicken_block".equals(path) || "fiery_snakes_block".equals(path) || "meef_wellington_block".equals(path)) {
            itemBlock.setMaxStackSize(1);
        }
        ITEM_BLOCKS.put(path, itemBlock);
        return block;
    }
}
