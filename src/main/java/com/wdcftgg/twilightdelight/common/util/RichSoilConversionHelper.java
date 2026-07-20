package com.wdcftgg.twilightdelight.common.util;

import com.wdcftgg.farmersdelightlegacy.common.Configuration;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.block.BlockTFHugeLilyPad;
import twilightforest.block.BlockTFRoots;
import twilightforest.block.BlockTFSapling;
import twilightforest.block.TFBlocks;
import twilightforest.enums.HugeLilypadPiece;
import twilightforest.enums.RootVariant;
import twilightforest.enums.SaplingVariant;

import java.util.List;
import java.util.Random;

public final class RichSoilConversionHelper {

    private RichSoilConversionHelper() {
    }

    public static void convert(World world, BlockPos richSoilPos, Random random) {
        convertLiveRoot(world, richSoilPos, random);
        convertLilyPad(world, richSoilPos, random);
        convertIronwood(world, richSoilPos, random);
    }

    private static void convertLiveRoot(World world, BlockPos pos, Random random) {
        BlockPos rootPos = pos.offset(EnumFacing.values()[random.nextInt(EnumFacing.values().length)]);
        IBlockState rootState = world.getBlockState(rootPos);
        if (!isRoot(rootState, RootVariant.ROOT)) {
            return;
        }
        BlockPos neighborPos = rootPos.offset(EnumFacing.values()[random.nextInt(EnumFacing.values().length)]);
        if (isRoot(world.getBlockState(neighborPos), RootVariant.LIVEROOT)) {
            world.setBlockState(rootPos, rootState.withProperty(BlockTFRoots.VARIANT, RootVariant.LIVEROOT), 3);
        }
    }

    private static boolean isRoot(IBlockState state, RootVariant variant) {
        return state.getBlock() == TFBlocks.root && state.getValue(BlockTFRoots.VARIANT) == variant;
    }

    private static void convertLilyPad(World world, BlockPos pos, Random random) {
        BlockPos target = pos.up(2);
        IBlockState state = world.getBlockState(target);
        if (state.getBlock() == Blocks.WATERLILY) {
            EnumFacing neighborDirection = EnumFacing.HORIZONTALS[random.nextInt(EnumFacing.HORIZONTALS.length)];
            if (random.nextBoolean() && world.getBlockState(target.offset(neighborDirection)).getBlock() == TFBlocks.huge_lilypad) {
                world.setBlockState(target, TFBlocks.huge_waterlily.getDefaultState(), 3);
            } else {
                convertToHugeLilyPad(world, target, random);
            }
        } else if (state.getBlock() == TFBlocks.huge_waterlily) {
            spreadLilyPad(world, target, random);
        }
    }

    private static void spreadLilyPad(World world, BlockPos source, Random random) {
        BlockPos target = source.offset(EnumFacing.HORIZONTALS[random.nextInt(EnumFacing.HORIZONTALS.length)]);
        IBlockState targetState = world.getBlockState(target);
        if ((!world.isAirBlock(target) && targetState.getBlock() != Blocks.WATERLILY) || !isSourceWater(world.getBlockState(target.down()))) {
            return;
        }
        world.setBlockState(target, Blocks.WATERLILY.getDefaultState(), 3);
    }

    private static void convertToHugeLilyPad(World world, BlockPos target, Random random) {
        BlockTFHugeLilyPad block = (BlockTFHugeLilyPad) TFBlocks.huge_lilypad;
        EnumFacing facing = EnumFacing.HORIZONTALS[random.nextInt(EnumFacing.HORIZONTALS.length)];
        HugeLilypadPiece piece = HugeLilypadPiece.values()[random.nextInt(HugeLilypadPiece.values().length)];
        IBlockState selected = block.getDefaultState()
                .withProperty(BlockTFHugeLilyPad.FACING, facing)
                .withProperty(BlockTFHugeLilyPad.PIECE, piece);
        List<BlockPos> positions = block.getAllMyBlocks(target, selected);
        for (BlockPos position : positions) {
            IBlockState state = world.getBlockState(position);
            if ((!world.isAirBlock(position) && state.getBlock() != Blocks.WATERLILY)
                    || !isSourceWater(world.getBlockState(position.down()))) {
                return;
            }
        }

        BlockPos northWest = target;
        if (piece == HugeLilypadPiece.NE) {
            northWest = target.west();
        } else if (piece == HugeLilypadPiece.SE) {
            northWest = target.north().west();
        } else if (piece == HugeLilypadPiece.SW) {
            northWest = target.north();
        }
        world.setBlockState(northWest, selected.withProperty(BlockTFHugeLilyPad.PIECE, HugeLilypadPiece.NW), 10);
        world.setBlockState(northWest.east(), selected.withProperty(BlockTFHugeLilyPad.PIECE, HugeLilypadPiece.NE), 10);
        world.setBlockState(northWest.south(), selected.withProperty(BlockTFHugeLilyPad.PIECE, HugeLilypadPiece.SW), 10);
        world.setBlockState(northWest.south().east(), selected.withProperty(BlockTFHugeLilyPad.PIECE, HugeLilypadPiece.SE), 11);
    }

    private static boolean isSourceWater(IBlockState state) {
        return (state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.FLOWING_WATER)
                && state.getValue(BlockLiquid.LEVEL) == 0;
    }

    private static void convertIronwood(World world, BlockPos pos, Random random) {
        BlockPos saplingPos = pos.up();
        IBlockState state = world.getBlockState(saplingPos);
        if (state.getBlock() != TFBlocks.twilight_sapling
                || state.getValue(BlockTFSapling.TF_TYPE) != SaplingVariant.DARKWOOD
                || Configuration.richSoilBoostChance <= 0.0D
                || random.nextFloat() > Configuration.richSoilBoostChance) {
            return;
        }
        int liverootCount = 0;
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (isRoot(world.getBlockState(pos.add(x, -1, z)), RootVariant.LIVEROOT)) {
                    liverootCount++;
                }
            }
        }
        if (random.nextFloat() * 10.0F < liverootCount) {
            world.setBlockState(saplingPos, TwilightDelightBlocks.IRONWOOD_SAPLING.getDefaultState(), 3);
        }
    }
}
