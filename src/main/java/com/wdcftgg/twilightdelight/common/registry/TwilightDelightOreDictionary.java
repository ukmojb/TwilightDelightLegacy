package com.wdcftgg.twilightdelight.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public final class TwilightDelightOreDictionary {

    private TwilightDelightOreDictionary() {
    }

    public static void registerAll() {
        register("listAllvenisonraw", TFItems.raw_venison);
        register("listAllvenisonraw", TwilightDelightItems.RAW_VENISON_RIB);
        register("listAllvenisoncooked", TFItems.cooked_venison);
        register("listAllvenisoncooked", TwilightDelightItems.COOKED_VENISON_RIB);
        register("listAllmeefraw", TFItems.raw_meef);
        register("listAllmeefraw", TwilightDelightItems.RAW_MEEF_SLICE);
        register("listAllmeefcooked", TFItems.cooked_meef);
        register("listAllmeefcooked", TwilightDelightItems.COOKED_MEEF_SLICE);
        register("listAllhydra", TFItems.hydra_chop);
        register("listAllhydra", TwilightDelightItems.HYDRA_PIECE);
        register("fieryVial", TFItems.fiery_blood);
        register("fieryVial", TFItems.fiery_tears);
        register("listAllinsect", TwilightDelightItems.RAW_INSECT);
        register("mushgloom", new ItemStack(TFBlocks.twilight_plant, 1, 4));
        register("listAllinsectTarget", TFBlocks.firefly);
        register("listAllinsectTarget", TFBlocks.cicada);
        register("listAllinsectTarget", TFBlocks.moonworm);
        register("plantFiddlehead", new ItemStack(TFBlocks.twilight_plant, 1, 3));
        register("plantTorchberry", new ItemStack(TFBlocks.twilight_plant, 1, 7));
        register("plantRootStrand", new ItemStack(TFBlocks.twilight_plant, 1, 8));
        register("plantFallenLeaves", new ItemStack(TFBlocks.twilight_plant, 1, 9));
        register("ingotIronwood", TFItems.ironwood_ingot);
        register("ingotSteeleaf", TFItems.steeleaf_ingot);
        register("treeSapling", TwilightDelightBlocks.IRONWOOD_SAPLING);
        register("logWood", TwilightDelightBlocks.IRONWOOD_LOG);
        register("treeLeaves", TwilightDelightBlocks.IRONWOOD_LEAVES);
        register("toolKnife", TwilightDelightItems.FIERY_KNIFE);
        register("toolKnife", TwilightDelightItems.IRONWOOD_KNIFE);
        register("toolKnife", TwilightDelightItems.STEELEAF_KNIFE);
        register("toolKnife", TwilightDelightItems.KNIGHTMETAL_KNIFE);
        register("toolAxe", TFItems.ironwood_axe);
        register("toolAxe", TFItems.steeleaf_axe);
        register("toolAxe", TFItems.knightmetal_axe);
        register("toolPickaxe", TFItems.ironwood_pickaxe);
        register("toolPickaxe", TFItems.steeleaf_pickaxe);
        register("toolPickaxe", TFItems.knightmetal_pickaxe);
        register("toolPickaxe", TFItems.fiery_pickaxe);
        register("toolShovel", TFItems.ironwood_shovel);
        register("toolShovel", TFItems.steeleaf_shovel);
    }

    private static void register(String oreName, Item item) {
        OreDictionary.registerOre(oreName, new ItemStack(item));
    }

    private static void register(String oreName, Block block) {
        OreDictionary.registerOre(oreName, new ItemStack(block));
    }

    private static void register(String oreName, ItemStack itemStack) {
        OreDictionary.registerOre(oreName, itemStack);
    }
}
