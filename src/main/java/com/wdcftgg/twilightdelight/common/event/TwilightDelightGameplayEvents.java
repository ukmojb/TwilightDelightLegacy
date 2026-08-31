package com.wdcftgg.twilightdelight.common.event;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockStove;
import com.wdcftgg.farmersdelightlegacy.common.recipe.CookingPotRecipe;
import com.wdcftgg.farmersdelightlegacy.common.recipe.manager.CookingPotRecipeManager;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityCookingPot;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightItems;
import com.wdcftgg.twilightdelight.common.network.TwilightDelightNetwork;
import com.wdcftgg.twilightdelight.common.tile.MazeStoveTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import twilightforest.block.BlockTFExperiment115;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID)
public final class TwilightDelightGameplayEvents {

    private TwilightDelightGameplayEvents() {
    }

    @SubscribeEvent
    public static void onPlayerLogin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            TwilightDelightNetwork.sendConfig((EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onExperiment115Interaction(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != TFBlocks.experiment_115) {
            return;
        }

        ItemStack heldStack = event.getItemStack();
        int bitesTaken = state.getValue(BlockTFExperiment115.NOMS);
        boolean regenerating = state.getValue(BlockTFExperiment115.REGENERATE);
        if (heldStack.getItem() == Items.REDSTONE && !regenerating && bitesTaken > 0) {
            event.setCanceled(true);
            event.setCancellationResult(EnumActionResult.FAIL);
            return;
        }
        if (!isKnife(heldStack)) {
            return;
        }

        if (!world.isRemote) {
            if (bitesTaken < 7) {
                world.setBlockState(pos, state.withProperty(BlockTFExperiment115.NOMS, bitesTaken + 1), 3);
            } else {
                world.setBlockToAir(pos);
            }

            ItemStack result = getExperimentSlice(heldStack);
            EntityPlayer player = event.getEntityPlayer();
            if (!player.inventory.addItemStackToInventory(result)) {
                player.dropItem(result, false);
            }
            heldStack.damageItem(1, player);
        }

        world.playSound(event.getEntityPlayer(), pos, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
        event.setCanceled(true);
        event.setCancellationResult(EnumActionResult.SUCCESS);
    }

    @SubscribeEvent
    public static void onKnightmetalKnifeDamage(LivingHurtEvent event) {
        if (!(event.getSource().getTrueSource() instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
        EntityLivingBase target = event.getEntityLiving();
        if (!TwilightDelightItems.isKnightmetalBlade(attacker.getHeldItemMainhand().getItem())) {
            return;
        }
        int armor = target.getTotalArmorValue();
        if (armor <= 0) {
            return;
        }
        int coveredSlots = 0;
        for (ItemStack armorStack : target.getArmorInventoryList()) {
            if (!armorStack.isEmpty()) {
                coveredSlots++;
            }
        }
        int bonusDamage = (int) (2.0F * Math.min(1.0F, coveredSlots / 4.0F));
        event.setAmount(event.getAmount() + bonusDamage);
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.world.isRemote) {
            return;
        }
        for (TileEntity tileEntity : event.world.loadedTileEntityList) {
            if (!(tileEntity instanceof TileEntityCookingPot)) {
                continue;
            }
            TileEntityCookingPot cookingPot = (TileEntityCookingPot) tileEntity;
            boolean fieryPot = event.world.getBlockState(cookingPot.getPos()).getBlock() == TwilightDelightBlocks.FIERY_COOKING_POT;
            IBlockState below = event.world.getBlockState(cookingPot.getPos().down());
            boolean mazeStove = below.getBlock() == TwilightDelightBlocks.MAZE_STOVE && below.getValue(BlockStove.LIT);
            if ((!fieryPot && !mazeStove) || !cookingPot.isBoiling()) {
                continue;
            }

            List<ItemStack> inputStacks = new ArrayList<>(6);
            for (int slot = 0; slot < 6; slot++) {
                inputStacks.add(cookingPot.getStackInSlot(slot));
            }
            CookingPotRecipe recipe = CookingPotRecipeManager.findRecipe(inputStacks);
            if (recipe == null || !MazeStoveTileEntity.isTwilightResult(recipe.getResultStack().getItem().getRegistryName())) {
                continue;
            }
            int cookTime = recipe.getCookTime();
            int acceleratedTime = fieryPot ? Math.min(200, cookTime) : cookTime;
            if (mazeStove) {
                acceleratedTime /= 2;
            }
            int factor = cookTime / Math.max(1, acceleratedTime);
            if (factor > 1) {
                cookingPot.setField(0, cookingPot.getField(0) + factor - 1);
            }
        }
    }

    private static ItemStack getExperimentSlice(ItemStack knifeStack) {
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, knifeStack) > 0) {
            return new ItemStack(TFItems.experiment_115);
        }
        Item cakeSlice = ForgeRegistries.ITEMS.getValue(new ResourceLocation("farmersdelight", "cake_slice"));
        return new ItemStack(cakeSlice);
    }

    private static boolean isKnife(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (int oreId : OreDictionary.getOreIDs(stack)) {
            if ("toolKnife".equals(OreDictionary.getOreName(oreId))) {
                return true;
            }
        }
        return false;
    }
}
