package com.wdcftgg.twilightdelight.common.block;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.item.TFItems;

import java.util.Random;

public class IronwoodLogBlock extends BlockRotatedPillar {

    public IronwoodLogBlock() {
        super(Material.WOOD);
        setHardness(10.0F);
        setResistance(10.0F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 3);
    }

    @Override
    public Item getItemDropped(net.minecraft.block.state.IBlockState state, Random random, int fortune) {
        return TFItems.ironwood_ingot;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return 1 + (fortune > 0 ? random.nextInt(fortune + 1) : 0);
    }

    @Override
    protected ItemStack getSilkTouchDrop(net.minecraft.block.state.IBlockState state) {
        return new ItemStack(this, 1, damageDropped(state));
    }

    @Override
    public int getFlammability(net.minecraft.world.IBlockAccess world, net.minecraft.util.math.BlockPos pos,
                               net.minecraft.util.EnumFacing face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(net.minecraft.world.IBlockAccess world, net.minecraft.util.math.BlockPos pos,
                                  net.minecraft.util.EnumFacing face) {
        return 5;
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world,
                                   net.minecraft.util.math.BlockPos pos) {
        return true;
    }

    @Override
    public boolean isWood(net.minecraft.world.IBlockAccess world, net.minecraft.util.math.BlockPos pos) {
        return true;
    }
}
