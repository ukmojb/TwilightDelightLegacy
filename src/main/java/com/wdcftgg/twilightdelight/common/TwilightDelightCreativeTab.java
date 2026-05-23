package com.wdcftgg.twilightdelight.common;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class TwilightDelightCreativeTab {

    public static final CreativeTabs TAB = new CreativeTabs(TwilightDelightLegacy.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TwilightDelightItems.HYDRA_PIECE);
        }
    };

    private TwilightDelightCreativeTab() {
    }
}
