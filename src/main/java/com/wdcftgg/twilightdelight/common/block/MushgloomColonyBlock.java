package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockMushroomColony;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

import javax.annotation.Nullable;

public class MushgloomColonyBlock extends BlockMushroomColony {

    public MushgloomColonyBlock() {
        super("twilightforest:twilight_plant");
        this.setLightLevel(0.2F);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int age = state.getValue(AGE);
        ItemStack held = player.getHeldItem(hand);
        if (age <= 0 || held.getItem() != Items.SHEARS) {
            return false;
        }
        if (!world.isRemote) {
            spawnAsEntity(world, pos, mushgloomStack(1));
            world.setBlockState(pos, state.withProperty(AGE, age - 1), 2);
            held.damageItem(1, player);
        }
        world.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state,
                             @Nullable TileEntity tileEntity, ItemStack tool) {
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.005F);
        if (world.isRemote || player.capabilities.isCreativeMode) {
            return;
        }
        NonNullList<ItemStack> drops = NonNullList.create();
        addDrops(drops, state.getValue(AGE), tool.getItem() == Items.SHEARS);
        for (ItemStack drop : drops) {
            spawnAsEntity(world, pos, drop);
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        addDrops(drops, state.getValue(AGE), false);
    }

    private void addDrops(NonNullList<ItemStack> drops, int age, boolean usingShears) {
        if (usingShears && age == this.getMaxAge()) {
            drops.add(new ItemStack(this));
        } else {
            drops.add(mushgloomStack(age + 2));
        }
    }

    private static ItemStack mushgloomStack(int count) {
        return new ItemStack(TFBlocks.twilight_plant, count, 4);
    }
}
