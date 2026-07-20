package com.wdcftgg.twilightdelight.common.block;

import com.google.common.base.Predicate;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemFoodTooltip;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemKnife;
import com.wdcftgg.farmersdelightlegacy.common.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public class TwilightPieBlock extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", (Predicate<EnumFacing>) EnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 3);
    private static final AxisAlignedBB PIE_SHAPE = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);

    private final ResourceLocation sliceItemId;

    public TwilightPieBlock(ResourceLocation sliceItemId) {
        super(Material.CAKE);
        this.sliceItemId = sliceItemId;
        this.setHardness(0.5F);
        this.setResistance(0.5F);
        this.setSoundType(SoundType.CLOTH);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BITES, 0));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PIE_SHAPE;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return super.canPlaceBlockAt(world, pos) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (fromPos.equals(pos.down()) && !this.canPlaceBlockAt(world, pos)) {
            world.setBlockToAir(pos);
            return;
        }
        super.neighborChanged(state, world, pos, block, fromPos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (ItemKnife.isKnife(heldStack)) {
            if (!world.isRemote) {
                cutSlice(world, pos, state, player, heldStack);
            }
            return true;
        }
        return consumeBite(world, pos, state, player);
    }

    private boolean consumeBite(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!player.canEat(false)) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        Item sliceItem = ForgeRegistries.ITEMS.getValue(this.sliceItemId);
        if (!(sliceItem instanceof ItemFood)) {
            return false;
        }
        ItemStack sliceStack = new ItemStack(sliceItem);
        player.getFoodStats().addStats((ItemFood) sliceItem, sliceStack);
        if (sliceItem instanceof ItemFoodTooltip) {
            ((ItemFoodTooltip) sliceItem).onFoodEaten(sliceStack, world, player);
        }
        removeSlice(world, pos, state);
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 0.8F, 0.8F);
        spawnPieParticles(world, pos);
        return true;
    }

    private void cutSlice(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack knifeStack) {
        Item sliceItem = ForgeRegistries.ITEMS.getValue(this.sliceItemId);
        if (sliceItem == null) {
            return;
        }
        removeSlice(world, pos, state);
        EnumFacing dropFacing = player.getHorizontalFacing().getOpposite();
        EntityItem sliceEntity = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, new ItemStack(sliceItem));
        sliceEntity.motionX = dropFacing.getXOffset() * 0.15D;
        sliceEntity.motionY = 0.05D;
        sliceEntity.motionZ = dropFacing.getZOffset() * 0.15D;
        sliceEntity.setDefaultPickupDelay();
        world.spawnEntity(sliceEntity);
        world.playSound(null, pos, ModSounds.foodSlice, SoundCategory.PLAYERS, 0.8F, 0.8F);
        spawnPieParticles(world, pos);
        knifeStack.damageItem(1, player);
    }

    private void removeSlice(World world, BlockPos pos, IBlockState state) {
        int bites = state.getValue(BITES);
        if (bites >= 3) {
            world.setBlockToAir(pos);
        } else {
            world.setBlockState(pos, state.withProperty(BITES, bites + 1), 3);
        }
    }

    private void spawnPieParticles(World world, BlockPos pos) {
        for (int i = 0; i < 3; i++) {
            world.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
                    pos.getX() + 0.5D + (world.rand.nextDouble() - 0.5D) * 0.2D,
                    pos.getY() + 0.3D + (world.rand.nextDouble() - 0.5D) * 0.2D,
                    pos.getZ() + 0.5D + (world.rand.nextDouble() - 0.5D) * 0.2D,
                    0.0D, 0.0D, 0.0D, Block.getStateId(this.getDefaultState()));
        }
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        return 4 - state.getValue(BITES);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BITES);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex() | state.getValue(BITES) << 2;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3))
                .withProperty(BITES, meta >> 2 & 3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        Item sliceItem = ForgeRegistries.ITEMS.getValue(this.sliceItemId);
        return sliceItem == null ? Items.AIR : sliceItem;
    }
}
