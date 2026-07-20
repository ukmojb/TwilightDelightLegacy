package com.wdcftgg.twilightdelight.common;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public final class TwilightDelightCreativeTab {

    public static final CreativeTabs TAB = new CreativeTabs(TwilightDelightLegacy.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TwilightDelightBlocks.MAZE_STOVE);
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> items) {
            addBlock(items, TwilightDelightBlocks.MAZE_STOVE);
            addBlock(items, TwilightDelightBlocks.FIERY_COOKING_POT);
            addBlock(items, TwilightDelightBlocks.FIERY_SNAKES_BLOCK);
            addBlock(items, TwilightDelightBlocks.LILY_CHICKEN_BLOCK);
            addBlock(items, TwilightDelightBlocks.MEEF_WELLINGTON_BLOCK);
            addBlock(items, TwilightDelightBlocks.TORCHBERRIES_CRATE);
            addBlock(items, TwilightDelightBlocks.MUSHGLOOM_COLONY);
            addBlock(items, TwilightDelightBlocks.TWILIGHT_OAK_CABINET);
            addBlock(items, TwilightDelightBlocks.CANOPY_CABINET);
            addBlock(items, TwilightDelightBlocks.DARK_CABINET);
            addBlock(items, TwilightDelightBlocks.MANGROVE_CABINET);
            addBlock(items, TwilightDelightBlocks.MINING_CABINET);
            addBlock(items, TwilightDelightBlocks.SORTING_CABINET);
            addBlock(items, TwilightDelightBlocks.TIME_CABINET);
            addBlock(items, TwilightDelightBlocks.TRANSFORMATION_CABINET);
            addBlock(items, TwilightDelightBlocks.AURORA_CAKE);
            addBlock(items, TwilightDelightBlocks.TORCHBERRY_CAKE);
            addBlock(items, TwilightDelightBlocks.PHYTOCHEMICAL_CAKE);
            addBlock(items, TwilightDelightBlocks.GLACIER_CAKE);
            addBlock(items, TwilightDelightBlocks.AURORA_ICE_CREAM_BLOCK);
            addBlock(items, TwilightDelightBlocks.TORCHBERRY_ICE_CREAM_BLOCK);
            addBlock(items, TwilightDelightBlocks.PHYTOCHEMICAL_ICE_CREAM_BLOCK);
            addBlock(items, TwilightDelightBlocks.GLACIER_ICE_CREAM_BLOCK);

            addItem(items, TwilightDelightItems.IRONWOOD_KNIFE);
            addItem(items, TwilightDelightItems.STEELEAF_KNIFE);
            addItem(items, TwilightDelightItems.KNIGHTMETAL_KNIFE);
            addItem(items, TwilightDelightItems.FIERY_KNIFE);
            addItem(items, TwilightDelightItems.TEARDROP_SWORD);

            addItem(items, TwilightDelightItems.HYDRA_PIECE);
            addItem(items, TwilightDelightItems.RAW_VENISON_RIB);
            addItem(items, TwilightDelightItems.COOKED_VENISON_RIB);
            addItem(items, TwilightDelightItems.RAW_MEEF_SLICE);
            addItem(items, TwilightDelightItems.COOKED_MEEF_SLICE);
            addItem(items, TwilightDelightItems.RAW_TOMAHAWK_SMEAK);
            addItem(items, TwilightDelightItems.COOKED_TOMAHAWK_SMEAK);
            addItem(items, TwilightDelightItems.RAW_INSECT);
            addItem(items, TwilightDelightItems.COOKED_INSECT);
            addItem(items, TwilightDelightItems.TORCHBERRY_COOKIE);
            addItem(items, TwilightDelightItems.NAGA_CHIP);
            addItem(items, TwilightDelightItems.CHOCOLATE_WAFER);
            addItem(items, TwilightDelightItems.EXPERIMENT_113);
            addItem(items, TwilightDelightItems.CHOCOLATE_113);
            addItem(items, TwilightDelightItems.MILKY_113);
            addItem(items, TwilightDelightItems.GLOW_113);
            addItem(items, TwilightDelightItems.HONEY_113);
            addItem(items, TwilightDelightItems.EXPERIMENT_110);
            addItem(items, TwilightDelightItems.MEEF_WRAP);
            addItem(items, TwilightDelightItems.GHAST_BURGER);
            addItem(items, TwilightDelightItems.HYDRA_BURGER);
            addItem(items, TwilightDelightItems.BERRY_STICK);
            addItem(items, TwilightDelightItems.GLOWSTEW);
            addItem(items, TwilightDelightItems.MUSHGLOOM_SAUCE);
            addItem(items, TwilightDelightItems.GLOW_VENISON_RIB_WITH_PASTA);
            addItem(items, TwilightDelightItems.MUSHGLOOM_MEEF_PASTA);
            addItem(items, TwilightDelightItems.LIVEROOT_PORK_FRIED_RICE);
            addItem(items, TwilightDelightItems.LIVEROOT_VENISON_NOODLE_SOUP);
            addItem(items, TwilightDelightItems.TORCHBERRY_VENISON_SANDWICH);
            addItem(items, TwilightDelightItems.FRIED_INSECT);
            addItem(items, TwilightDelightItems.THOUSAND_PLANT_STEW);
            addItem(items, TwilightDelightItems.GRILLED_GHAST);
            addItem(items, TwilightDelightItems.GRILLED_TOMAHAWK_SMEAK);
            addItem(items, TwilightDelightItems.BORER_TEAR_SOUP);
            addItem(items, TwilightDelightItems.GHAST_BRAIN_SALAD);
            addItem(items, TwilightDelightItems.LILY_CHICKEN);
            addItem(items, TwilightDelightItems.FIERY_SNAKES);
            addItem(items, TwilightDelightItems.PLATE_OF_MEEF_WELLINGTON);
            addItem(items, TwilightDelightItems.AURORA_CAKE_SLICE);
            addItem(items, TwilightDelightItems.TORCHBERRY_CAKE_SLICE);
            addItem(items, TwilightDelightItems.PHYTOCHEMICAL_CAKE_SLICE);
            addItem(items, TwilightDelightItems.GLACIER_CAKE_SLICE);
            addItem(items, TwilightDelightItems.AURORA_ICE_CREAM);
            addItem(items, TwilightDelightItems.TORCHBERRY_ICE_CREAM);
            addItem(items, TwilightDelightItems.PHYTOCHEMICAL_ICE_CREAM);
            addItem(items, TwilightDelightItems.GLACIER_ICE_CREAM);
            addItem(items, TwilightDelightItems.AURORA_MILKSHAKE);
            addItem(items, TwilightDelightItems.TORCHBERRY_MILKSHAKE);
            addItem(items, TwilightDelightItems.PHYTOCHEMICAL_MILKSHAKE);
            addItem(items, TwilightDelightItems.GLACIER_MILKSHAKE);
            addItem(items, TwilightDelightItems.THORN_ROSE_TEA);
            addItem(items, TwilightDelightItems.TORCHBERRY_JUICE);
            addItem(items, TwilightDelightItems.PHYTOCHEMICAL_JUICE);
            addItem(items, TwilightDelightItems.GLACIER_ICE_TEA);
            addItem(items, TwilightDelightItems.TWILIGHT_SPRING);
            addItem(items, TwilightDelightItems.TEAR_DRINK);

            addBlock(items, TwilightDelightBlocks.AURORA_PIE);
            addItem(items, TwilightDelightItems.AURORA_PIE_SLICE);
            addBlock(items, TwilightDelightBlocks.TORCHBERRY_PIE);
            addItem(items, TwilightDelightItems.TORCHBERRY_PIE_SLICE);
        }

        private void addBlock(NonNullList<ItemStack> items, net.minecraft.block.Block block) {
            addItem(items, Item.getItemFromBlock(block));
        }

        private void addItem(NonNullList<ItemStack> items, Item item) {
            if (item != null) {
                item.getSubItems(this, items);
            }
        }
    };

    private TwilightDelightCreativeTab() {
    }
}
