package com.wdcftgg.twilightdelight.common.block;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityFeast;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TwilightFeastBlock extends FoodItemApi.PlaceableFoodBlock {

    private final ResourceLocation servingItemId;
    private final boolean dropsBoneMealWithBowl;

    public TwilightFeastBlock(FoodItemApi.PlaceableFoodSettings settings, ResourceLocation servingItemId, boolean dropsBoneMealWithBowl) {
        super(settings);
        this.servingItemId = servingItemId;
        this.dropsBoneMealWithBowl = dropsBoneMealWithBowl;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hand == EnumHand.MAIN_HAND && getCurrentServings(worldIn, pos, state) <= 0) {
            if (!worldIn.isRemote) {
                spawnLeftoverDrop(worldIn, pos, new ItemStack(Items.BOWL));
                if (this.dropsBoneMealWithBowl) {
                    spawnLeftoverDrop(worldIn, pos, new ItemStack(Items.DYE, 1, 15));
                }
                worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);
                worldIn.destroyBlock(pos, true);
            }
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    private int getCurrentServings(World world, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityFeast) {
            int servings = ((TileEntityFeast) tileEntity).getServings();
            return servings < 0 ? this.getMaxServings() : this.clampServings(servings);
        }
        return state.getValue(this.getServingsProperty());
    }

    private static void spawnLeftoverDrop(World world, BlockPos pos, ItemStack itemStack) {
        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        world.spawnEntity(entityItem);
    }

    @Override
    protected ItemStack getServingStackForServings(int servings) {
        Item servingItem = ForgeRegistries.ITEMS.getValue(this.servingItemId);
        if (servingItem == null || servings <= 0) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(servingItem);
    }
}
