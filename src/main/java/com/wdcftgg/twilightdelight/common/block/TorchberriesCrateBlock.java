package com.wdcftgg.twilightdelight.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TorchberriesCrateBlock extends Block {

    public TorchberriesCrateBlock() {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setResistance(3.0F);
        this.setSoundType(SoundType.WOOD);
        this.setLightLevel(1.0F);
    }
}
