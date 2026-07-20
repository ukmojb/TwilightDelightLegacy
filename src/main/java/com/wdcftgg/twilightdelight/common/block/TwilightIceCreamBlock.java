package com.wdcftgg.twilightdelight.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TwilightIceCreamBlock extends Block {

    public TwilightIceCreamBlock() {
        super(Material.SNOW);
        this.setHardness(0.2F);
        this.setResistance(0.2F);
        this.setSoundType(SoundType.SNOW);
    }
}
