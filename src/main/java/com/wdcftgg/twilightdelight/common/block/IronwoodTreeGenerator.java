package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import twilightforest.block.BlockTFRoots;
import twilightforest.block.TFBlocks;
import twilightforest.enums.RootVariant;
import twilightforest.world.feature.TFGenerator;
import twilightforest.world.feature.TFTreeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The ironwood-only counterpart of the continuation mod's branching tree feature.
 */
public class IronwoodTreeGenerator extends WorldGenAbstractTree {

    private static final double TWO_PI = Math.PI * 2.0D;

    private final IBlockState logState = TwilightDelightBlocks.IRONWOOD_LOG.getDefaultState()
            .withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y);
    private final IBlockState leavesState = TwilightDelightBlocks.IRONWOOD_LEAVES.getDefaultState();

    public IronwoodTreeGenerator(boolean notify) {
        super(notify);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        IBlockState soil = world.getBlockState(pos.down());
        if (!soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP,
                (net.minecraftforge.common.IPlantable) TwilightDelightBlocks.IRONWOOD_SAPLING)) {
            return false;
        }

        int height = 9 + random.nextInt(2) + random.nextInt(2);
        if (pos.getY() + height + 10 >= world.getHeight() || !world.isAreaLoaded(pos, 16)) {
            return false;
        }

        for (int y = 0; y <= height; y++) {
            if (!canReplace(world, pos.up(y))) {
                return false;
            }
        }

        for (int y = 0; y <= height; y++) {
            placeLog(world, pos.up(y));
        }

        List<BlockPos> foliageCenters = new ArrayList<>();
        foliageCenters.add(pos.up(height));

        float angleOffset = random.nextFloat();
        for (int branch = 0; branch < 4; branch++) {
            BlockPos source = pos.up(height - 3 + branch);
            BlockPos destination = translate(source, 8.0D, 0.23D * branch + angleOffset, 0.23D);
            if (!world.isAreaLoaded(destination, 6)) {
                continue;
            }

            drawBranch(world, source, destination);
            placeLog(world, destination.east());
            placeLog(world, destination.west());
            placeLog(world, destination.south());
            placeLog(world, destination.north());
            foliageCenters.add(destination);
        }

        for (BlockPos center : foliageCenters) {
            placeSpheroid(world, random, center,
                    4.5F + random.nextInt(2), 2.25F, 0.45F);
        }

        placeRoots(world, random, pos);
        return true;
    }

    private void drawBranch(World world, BlockPos source, BlockPos destination) {
        for (BlockPos point : TFGenerator.getBresehnamArrays(source, destination)) {
            placeLog(world, point);
        }
    }

    private void placeSpheroid(World world, Random random, BlockPos center,
                               float horizontalRadius, float verticalRadius, float verticalBias) {
        float horizontalRadiusSquared = horizontalRadius * horizontalRadius;
        float verticalRadiusSquared = verticalRadius * verticalRadius;
        float spheroidRadiusSquared = horizontalRadiusSquared * verticalRadiusSquared;

        placeLeaf(world, center);
        for (int y = 0; y <= verticalRadius; y++) {
            placeLeaf(world, center.up(y));
            placeLeaf(world, center.down(y));
        }

        for (int x = 0; x <= horizontalRadius; x++) {
            for (int z = 1; z <= horizontalRadius; z++) {
                if (x * x + z * z > horizontalRadiusSquared) {
                    continue;
                }

                placeHorizontalFour(world, center, x, 0, z);
                for (int y = 1; y <= verticalRadius; y++) {
                    float xzSquare = (x * x + z * z) * verticalRadiusSquared;
                    if (xzSquare + (y - verticalBias) * (y - verticalBias) * horizontalRadiusSquared
                            <= spheroidRadiusSquared) {
                        placeHorizontalFour(world, center, x, y, z);
                    }
                    if (xzSquare + (y + verticalBias) * (y + verticalBias) * horizontalRadiusSquared
                            <= spheroidRadiusSquared) {
                        placeHorizontalFour(world, center, x, -y, z);
                    }
                }
            }
        }

        for (int i = 0; i < 36; i++) {
            float yaw = random.nextFloat() * (float) TWO_PI;
            float pitch = random.nextFloat() * 2.0F - 1.0F;
            float horizontalUnit = (float) Math.sqrt(1.0F - pitch * pitch);
            float xOffset = horizontalUnit * (float) Math.cos(yaw) * (horizontalRadius - 1.0F);
            float zOffset = horizontalUnit * (float) Math.sin(yaw) * (horizontalRadius - 1.0F);
            int x = castLikeContinuation(xOffset);
            int y = (int) (pitch * (verticalRadius + 0.25F) + verticalBias);
            int z = castLikeContinuation(zOffset);
            BlockPos cluster = center.add(x, y, z);
            placeLeaf(world, cluster);
            placeLeaf(world, cluster.east());
            placeLeaf(world, cluster.south());
            placeLeaf(world, cluster.east().south());
        }
    }

    private void placeHorizontalFour(World world, BlockPos center, int x, int y, int z) {
        placeLeaf(world, center.add(x, y, z));
        placeLeaf(world, center.add(-x, y, -z));
        placeLeaf(world, center.add(-z, y, x));
        placeLeaf(world, center.add(z, y, -x));
    }

    private void placeRoots(World world, Random random, BlockPos pos) {
        IBlockState rootState = TFBlocks.root.getDefaultState().withProperty(BlockTFRoots.VARIANT, RootVariant.ROOT);
        IBlockState liverootState = TFBlocks.root.getDefaultState().withProperty(BlockTFRoots.VARIANT, RootVariant.LIVEROOT);
        int rootCount = 3 + random.nextInt(2);
        double angleOffset = random.nextDouble();

        for (int root = 0; root < rootCount; root++) {
            BlockPos destination = translate(pos.down(root + 2), 5.0D,
                    0.3D * root + angleOffset, 0.8D);
            for (BlockPos point : TFGenerator.getBresehnamArrays(pos.down(), destination)) {
                if (!TFTreeGenerator.canRootGrowIn(world, point)) {
                    continue;
                }
                setBlockAndNotifyAdequately(world, point,
                        random.nextInt(7) == 0 ? liverootState : rootState);
            }
        }
    }

    private void placeLog(World world, BlockPos pos) {
        if (canReplace(world, pos)) {
            setBlockAndNotifyAdequately(world, pos, logState);
        }
    }

    private void placeLeaf(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (canReplaceByLeaves(world, pos, state)) {
            setBlockAndNotifyAdequately(world, pos, leavesState);
        }
    }

    private boolean canReplace(World world, BlockPos pos) {
        return canReplaceByLeaves(world, pos, world.getBlockState(pos));
    }

    private boolean canReplaceByLeaves(World world, BlockPos pos, IBlockState state) {
        Block block = state.getBlock();
        return block.isAir(state, world, pos) || block.canBeReplacedByLeaves(state, world, pos);
    }

    private static BlockPos translate(BlockPos pos, double distance, double angle, double tilt) {
        double radians = angle * TWO_PI;
        double tiltRadians = tilt * Math.PI;
        return pos.add(
                (int) Math.round(Math.sin(radians) * Math.sin(tiltRadians) * distance),
                (int) Math.round(Math.cos(tiltRadians) * distance),
                (int) Math.round(Math.cos(radians) * Math.sin(tiltRadians) * distance));
    }

    private static int castLikeContinuation(float value) {
        int integer = (int) value;
        return integer + (integer >> 31);
    }
}
