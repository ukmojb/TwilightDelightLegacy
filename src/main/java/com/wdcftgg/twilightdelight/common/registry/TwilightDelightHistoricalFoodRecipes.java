package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.recipe.CuttingBoardRecipeApi;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public final class TwilightDelightHistoricalFoodRecipes {

    private TwilightDelightHistoricalFoodRecipes() {
    }

    public static void registerAll(RegistryEvent.Register<IRecipe> event) {
        registerFamily(event, "aurora", TFBlocks.aurora_block,
                TwilightDelightBlocks.AURORA_CAKE, TwilightDelightItems.AURORA_CAKE_SLICE,
                TwilightDelightItems.AURORA_ICE_CREAM, TwilightDelightItems.AURORA_MILKSHAKE,
                TwilightDelightBlocks.AURORA_ICE_CREAM_BLOCK);
        registerFamily(event, "torchberry", TFItems.torchberries,
                TwilightDelightBlocks.TORCHBERRY_CAKE, TwilightDelightItems.TORCHBERRY_CAKE_SLICE,
                TwilightDelightItems.TORCHBERRY_ICE_CREAM, TwilightDelightItems.TORCHBERRY_MILKSHAKE,
                TwilightDelightBlocks.TORCHBERRY_ICE_CREAM_BLOCK);
        registerFamily(event, "phytochemical", TFItems.steeleaf_ingot,
                TwilightDelightBlocks.PHYTOCHEMICAL_CAKE, TwilightDelightItems.PHYTOCHEMICAL_CAKE_SLICE,
                TwilightDelightItems.PHYTOCHEMICAL_ICE_CREAM, TwilightDelightItems.PHYTOCHEMICAL_MILKSHAKE,
                TwilightDelightBlocks.PHYTOCHEMICAL_ICE_CREAM_BLOCK);
        registerFamily(event, "glacier", TFItems.ice_bomb,
                TwilightDelightBlocks.GLACIER_CAKE, TwilightDelightItems.GLACIER_CAKE_SLICE,
                TwilightDelightItems.GLACIER_ICE_CREAM, TwilightDelightItems.GLACIER_MILKSHAKE,
                TwilightDelightBlocks.GLACIER_ICE_CREAM_BLOCK);
        registerAuroraPie(event);
        registerCuttingBoardRecipes();
    }

    private static void registerFamily(RegistryEvent.Register<IRecipe> event, String flavor, Object ingredient,
                                       Block cake, Item cakeSlice, Item iceCream, Item milkshake,
                                       Block iceCreamBlock) {
        registerShaped(event, flavor + "_cake", stack(cake),
                "MXM",
                "SES",
                "WXW",
                'M', "listAllmilk",
                'X', ingredient,
                'S', Items.SUGAR,
                'E', Items.EGG,
                'W', Items.WHEAT);
        registerShaped(event, flavor + "_cake_from_slices", stack(cake),
                "SS",
                "SS",
                'S', cakeSlice);
        registerShapeless(event, flavor + "_ice_cream", stack(iceCream),
                Items.BOWL, "listAllmilk", Items.SNOWBALL, Items.SUGAR, ingredient);
        registerShapeless(event, flavor + "_milkshake", new ItemStack(milkshake, 3),
                Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, "listAllmilk", iceCream);
        registerShaped(event, flavor + "_ice_cream_block", new ItemStack(iceCreamBlock, 8),
                "SSS",
                "SIS",
                "SSS",
                'S', Blocks.SNOW,
                'I', iceCream);

        CuttingBoardRecipeApi.registerRecipe(cuttingId(flavor + "_cake"),
                itemId(cake), null, itemId(cakeSlice), 4, 1.0F);
    }

    private static void registerAuroraPie(RegistryEvent.Register<IRecipe> event) {
        registerShaped(event, "aurora_pie", stack(TwilightDelightBlocks.AURORA_PIE),
                "WWW",
                "AAA",
                "SPS",
                'W', Items.WHEAT,
                'A', TFBlocks.aurora_block,
                'S', Items.SUGAR,
                'P', item("farmersdelight:pie_crust"));
        registerShaped(event, "aurora_pie_from_slices", stack(TwilightDelightBlocks.AURORA_PIE),
                "SS",
                "SS",
                'S', TwilightDelightItems.AURORA_PIE_SLICE);
    }

    private static void registerCuttingBoardRecipes() {
        CuttingBoardRecipeApi.registerRecipe(cuttingId("aurora_pie"),
                itemId(TwilightDelightBlocks.AURORA_PIE), null,
                itemId(TwilightDelightItems.AURORA_PIE_SLICE), 4, 1.0F);
    }

    private static void registerShaped(RegistryEvent.Register<IRecipe> event, String name,
                                       ItemStack output, Object... recipe) {
        register(event, name, new ShapedOreRecipe(location(name), output, recipe));
    }

    private static void registerShapeless(RegistryEvent.Register<IRecipe> event, String name,
                                          ItemStack output, Object... ingredients) {
        register(event, name, new ShapelessOreRecipe(location(name), output, ingredients));
    }

    private static void register(RegistryEvent.Register<IRecipe> event, String name, IRecipe recipe) {
        recipe.setRegistryName(location(name));
        event.getRegistry().register(recipe);
    }

    private static ResourceLocation location(String path) {
        return new ResourceLocation(TwilightDelightLegacy.MOD_ID, path);
    }

    private static String cuttingId(String path) {
        return location("cutting/" + path).toString();
    }

    private static String itemId(Item item) {
        return item.getRegistryName().toString();
    }

    private static String itemId(Block block) {
        return block.getRegistryName().toString();
    }

    private static Item item(String registryName) {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(registryName));
        return item == null ? Items.AIR : item;
    }

    private static ItemStack stack(Item item) {
        return new ItemStack(item);
    }

    private static ItemStack stack(Block block) {
        return new ItemStack(block);
    }
}
