package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.item.TFItems;

import java.util.Random;
import java.util.List;

public class IronwoodLeavesBlock extends BlockLeaves {

    public IronwoodLeavesBlock() {
        setHardness(10.0F);
        setResistance(10.0F);
        setHarvestLevel("hoe", 3);
        setDefaultState(blockState.getBaseState()
                .withProperty(CHECK_DECAY, true)
                .withProperty(DECAYABLE, true));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(DECAYABLE, (meta & 4) == 0)
                .withProperty(CHECK_DECAY, (meta & 8) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (!state.getValue(DECAYABLE)) {
            meta |= 4;
        }
        if (state.getValue(CHECK_DECAY)) {
            meta |= 8;
        }
        return meta;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.OAK;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos))));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(TwilightDelightBlocks.IRONWOOD_SAPLING);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos,
                         IBlockState state, int fortune) {
        Random random = world instanceof World ? ((World) world).rand : new Random();
        if (random.nextFloat() < getSaplingChance(fortune)) {
            drops.add(new ItemStack(TwilightDelightBlocks.IRONWOOD_SAPLING));
            return;
        }

        if (random.nextFloat() < getSteeleafChance(fortune)) {
            drops.add(new ItemStack(TFItems.steeleaf_ingot));
        }
    }

    private static float getSaplingChance(int fortune) {
        switch (Math.min(fortune, 3)) {
            case 1:
                return 0.002F;
            case 2:
                return 0.003003003F;
            case 3:
                return 0.004F;
            default:
                return 0.001F;
        }
    }

    private static float getSteeleafChance(int fortune) {
        switch (Math.min(fortune, 3)) {
            case 1:
                return 0.05F;
            case 2:
                return 0.06666667F;
            case 3:
                return 0.1F;
            default:
                return 0.04F;
        }
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, net.minecraft.util.EnumFacing face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, net.minecraft.util.EnumFacing face) {
        return 30;
    }
}
