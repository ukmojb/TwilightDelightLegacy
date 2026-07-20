package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.api.knife.ItemKnifeBase;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemDrinkableTooltip;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemFoodTooltip;
import com.wdcftgg.farmersdelightlegacy.common.registry.ModEffects;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.TwilightDelightCreativeTab;
import com.wdcftgg.twilightdelight.common.item.FieryKnifeItem;
import com.wdcftgg.twilightdelight.common.item.FireResistantItemSupport;
import com.wdcftgg.twilightdelight.common.item.KnightmetalKnifeItem;
import com.wdcftgg.twilightdelight.common.item.TeardropSwordItem;
import com.wdcftgg.twilightdelight.common.item.ThornRoseTeaItem;
import com.wdcftgg.twilightdelight.common.item.TwilightFoodItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import twilightforest.item.TFItems;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import java.util.Map;

public final class TwilightDelightItems {

    public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    public static final Item HYDRA_PIECE = registerFireproofFastFood("hydra_piece", food(9, 2.0F).effect(potionId(MobEffects.REGENERATION), 20, 0, 1.0F));
    public static final Item RAW_VENISON_RIB = registerFastFood("raw_venison_rib", food(2, 0.7F));
    public static final Item COOKED_VENISON_RIB = registerFastFood("cooked_venison_rib", food(4, 0.8F));
    public static final Item RAW_MEEF_SLICE = registerFastFood("raw_meef_slice", food(1, 0.7F));
    public static final Item COOKED_MEEF_SLICE = registerFastFood("cooked_meef_slice", food(3, 0.8F));
    public static final Item RAW_TOMAHAWK_SMEAK = registerFood("raw_tomahawk_smeak", food(6, 0.7F));
    public static final Item COOKED_TOMAHAWK_SMEAK = registerFood("cooked_tomahawk_smeak", food(12, 0.8F));
    public static final Item RAW_INSECT = registerFood("raw_insect", food(2, 0.2F));
    public static final Item COOKED_INSECT = registerFood("cooked_insect", food(6, 0.6F));
    public static final Item TORCHBERRY_COOKIE = registerFastFood("torchberry_cookie", food(2, 0.2F)
            .effect(potionId(MobEffects.GLOWING), 200, 0, 1.0F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 200, 0, 0.3F));
    public static final Item NAGA_CHIP = registerFastFood("naga_chip", food(2, 0.8F)
            .effect(potionId(MobEffects.RESISTANCE), 200, 2, 1.0F)
            .effect(potionId(MobEffects.SPEED), 200, 2, 1.0F));
    public static final Item CHOCOLATE_WAFER = registerFood("chocolate_wafer", food(9, 0.6F));
    public static final Item EXPERIMENT_113 = registerStickFood("experiment_113", food(6, 1.2F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 100, 0, 0.33F));
    public static final Item CHOCOLATE_113 = registerStickFood("chocolate_113", food(6, 1.2F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 100, 0, 0.33F)
            .effect(potionId(MobEffects.STRENGTH), 600, 4, 1.0F));
    public static final Item MILKY_113 = registerStickFood("milky_113", food(6, 1.2F)
            .effect(potionId(MobEffects.INSTANT_HEALTH), 1, 0, 1.0F));
    public static final Item GLOW_113 = registerStickFood("glow_113", food(6, 1.2F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 100, 0, 0.33F)
            .effect(potionId(MobEffects.NIGHT_VISION), 6000, 0, 1.0F)
            .effect(potionId(MobEffects.GLOWING), 6000, 0, 1.0F));
    public static final Item HONEY_113 = registerStickFood("honey_113", food(6, 1.2F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 100, 0, 0.33F)
            .effect(potionId(MobEffects.SPEED), 3600, 2, 1.0F));
    public static final Item EXPERIMENT_110 = registerFood("experiment_110", food(12, 0.3F)
            .effect(potionId(MobEffects.HEALTH_BOOST), 2400, 4, 1.0F)
            .effect(potionId(MobEffects.NIGHT_VISION), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.NAUSEA), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.POISON), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.BLINDNESS), 2400, 0, 0.5F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 200, 0, 0.5F));
    public static final Item MEEF_WRAP = registerFood("meef_wrap", food(10, 0.8F)
            .effect(potionId(MobEffects.STRENGTH), 1200, 0, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 1200, 0, 1.0F));
    public static final Item GHAST_BURGER = registerFood("ghast_burger", food(11, 0.8F)
            .effect(potionId(MobEffects.REGENERATION), 1200, 1, 1.0F));
    public static final Item HYDRA_BURGER = registerFood("hydra_burger", food(18, 0.8F)
            .effect(potionId(MobEffects.REGENERATION), 6000, 1, 1.0F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 6000, 0, 1.0F));
    public static final Item BERRY_STICK = registerStickFood("berry_stick", food(6, 0.3F)
            .effect(potionId(MobEffects.GLOWING), 200, 0, 1.0F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 200, 0, 1.0F));
    public static final Item TORCHBERRY_PIE_SLICE = registerFastFood("torchberry_pie_slice", food(3, 0.3F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 300, 0, 1.0F));
    public static final Item AURORA_PIE_SLICE = registerFastFood("aurora_pie_slice", food(3, 0.3F)
            .effect(auroraGlowingId(), 300, 0, 1.0F)
            .effect(potionId(MobEffects.SPEED), 300, 2, 1.0F)
            .effect(potionId(MobEffects.JUMP_BOOST), 300, 1, 1.0F));

    public static final Item GLOWSTEW = registerBowlFood("glowstew", food(7, 0.7F)
            .effect(potionId(MobEffects.GLOWING), 3600, 0, 1.0F)
            .effect(potionId(MobEffects.NIGHT_VISION), 3600, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 1200, 0, 1.0F)
            .maxStackSize(16));
    public static final Item MUSHGLOOM_SAUCE = registerBowlFood("mushgloom_sauce", food(5, 0.4F)
            .effect(potionId(MobEffects.GLOWING), 200, 0, 1.0F)
            .maxStackSize(64));
    public static final Item GLOW_VENISON_RIB_WITH_PASTA = registerBowlFood("glow_venison_rib_with_pasta", food(12, 0.8F)
            .effect(potionId(MobEffects.GLOWING), 200, 0, 1.0F)
            .effect(potionId(MobEffects.RESISTANCE), 1800, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item MUSHGLOOM_MEEF_PASTA = registerBowlFood("mushgloom_meef_pasta", food(12, 0.8F)
            .effect(potionId(MobEffects.GLOWING), 100, 0, 1.0F)
            .effect(potionId(MobEffects.STRENGTH), 1200, 1, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 1200, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item LIVEROOT_PORK_FRIED_RICE = registerBowlFood("liveroot_pork_fried_rice", food(12, 0.8F)
            .effect(potionId(MobEffects.RESISTANCE), 3600, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item LIVEROOT_VENISON_NOODLE_SOUP = registerBowlFood("liveroot_venison_noodle_soup", food(10, 0.8F)
            .effect(potionId(MobEffects.RESISTANCE), 1200, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item TORCHBERRY_VENISON_SANDWICH = registerFood("torchberry_venison_sandwich", food(10, 0.8F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 3600, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F));
    public static final Item FRIED_INSECT = registerBowlFood("fried_insect", food(10, 0.8F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item THOUSAND_PLANT_STEW = registerBowlFood("thousand_plant_stew", food(10, 0.6F)
            .effect(potionId(MobEffects.HEALTH_BOOST), 600, 1, 1.0F)
            .effect(potionId(MobEffects.NAUSEA), 300, 0, 0.1F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 1200, 0, 1.0F)
            .maxStackSize(16));
    public static final Item GRILLED_GHAST = registerBowlFood("grilled_ghast", food(10, 0.8F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 6000, 1, 1.0F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 6000, 0, 1.0F)
            .maxStackSize(16));
    public static final Item GRILLED_TOMAHAWK_SMEAK = registerBowlFood("grilled_tomahawk_smeak", food(14, 0.8F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .effect(potionId(MobEffects.STRENGTH), 6000, 1, 1.0F)
            .effect(potionId(MobEffects.SPEED), 6000, 1, 1.0F)
            .maxStackSize(16));
    public static final Item BORER_TEAR_SOUP = registerBowlFood("borer_tear_soup", food(6, 0.6F)
            .effect(potionId(MobEffects.SATURATION), 10, 0, 1.0F)
            .maxStackSize(16));
    public static final Item GHAST_BRAIN_SALAD = registerBowlFood("ghast_brain_salad", food(6, 0.6F)
            .effect(potionId(MobEffects.HEALTH_BOOST), 2400, 4, 1.0F)
            .effect(potionId(MobEffects.NIGHT_VISION), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.STRENGTH), 2400, 4, 1.0F)
            .effect(potionId(MobEffects.RESISTANCE), 2400, 2, 1.0F)
            .effect(potionId(MobEffects.NAUSEA), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.POISON), 2400, 0, 1.0F)
            .effect(potionId(MobEffects.BLINDNESS), 2400, 0, 0.5F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 200, 0, 0.5F)
            .maxStackSize(16));
    public static final Item LILY_CHICKEN = registerBowlFood("lily_chicken", food(16, 0.9F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 6000, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16));
    public static final Item FIERY_SNAKES = registerBowlFood("fiery_snakes", food(20, 1.9F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .effect(potionId(MobEffects.STRENGTH), 6000, 2, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 6000, 1, 1.0F)
            .maxStackSize(16));
    public static final Item PLATE_OF_MEEF_WELLINGTON = registerBowlFood("plate_of_meef_wellington", food(14, 0.8F)
            .effect(potionId(MobEffects.STRENGTH), 3600, 1, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 3600, 1, 1.0F)
            .effect(potionId(MobEffects.GLOWING), 200, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16));
    public static final Item AURORA_CAKE_SLICE = registerFastFood("aurora_cake_slice", food(1, 0.1F)
            .effect(auroraGlowingId(), 300, 0, 1.0F)
            .effect(potionId(MobEffects.SPEED), 300, 2, 1.0F)
            .effect(potionId(MobEffects.JUMP_BOOST), 300, 1, 1.0F));
    public static final Item TORCHBERRY_CAKE_SLICE = registerFastFood("torchberry_cake_slice", food(1, 0.1F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 300, 0, 1.0F));
    public static final Item PHYTOCHEMICAL_CAKE_SLICE = registerFastFood("phytochemical_cake_slice", food(1, 0.1F)
            .effect(potionId(TwilightDelightPotions.POISON_RANGE), 300, 0, 1.0F));
    public static final Item GLACIER_CAKE_SLICE = registerFastFood("glacier_cake_slice", food(1, 0.1F)
            .effect(potionId(TwilightDelightPotions.FROZEN_RANGE), 300, 0, 1.0F));

    public static final Item AURORA_ICE_CREAM = registerBowlFood("aurora_ice_cream", food(6, 0.4F)
            .effect(auroraGlowingId(), 1800, 0, 1.0F)
            .effect(potionId(MobEffects.SPEED), 1800, 2, 1.0F)
            .effect(potionId(MobEffects.JUMP_BOOST), 1800, 1, 1.0F)
            .effect(frostyId(), 600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item TORCHBERRY_ICE_CREAM = registerBowlFood("torchberry_ice_cream", food(6, 0.4F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 1800, 0, 1.0F)
            .effect(frostyId(), 600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item PHYTOCHEMICAL_ICE_CREAM = registerBowlFood("phytochemical_ice_cream", food(6, 0.4F)
            .effect(potionId(TwilightDelightPotions.POISON_RANGE), 1800, 0, 1.0F)
            .effect(frostyId(), 600, 0, 1.0F)
            .maxStackSize(16));
    public static final Item GLACIER_ICE_CREAM = registerBowlFood("glacier_ice_cream", food(6, 0.4F)
            .effect(potionId(TwilightDelightPotions.FROZEN_RANGE), 1800, 0, 1.0F)
            .maxStackSize(16));

    public static final Item AURORA_MILKSHAKE = registerDrink("aurora_milkshake", drink(3, 0.6F)
            .effect(auroraGlowingId(), 600, 0, 1.0F)
            .effect(potionId(MobEffects.SPEED), 600, 2, 1.0F)
            .effect(potionId(MobEffects.JUMP_BOOST), 600, 1, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item TORCHBERRY_MILKSHAKE = registerDrink("torchberry_milkshake", drink(3, 0.6F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 600, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item PHYTOCHEMICAL_MILKSHAKE = registerDrink("phytochemical_milkshake", drink(3, 0.6F)
            .effect(potionId(TwilightDelightPotions.POISON_RANGE), 600, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item GLACIER_MILKSHAKE = registerDrink("glacier_milkshake", drink(3, 0.6F)
            .effect(potionId(TwilightDelightPotions.FROZEN_RANGE), 600, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));

    public static final Item THORN_ROSE_TEA = register("thorn_rose_tea", new ThornRoseTeaItem(drink(4, 0.25F)
            .effect(potionId(MobEffects.REGENERATION), 300, 1, 1.0F)
            .alwaysEdible()
            .maxStackSize(16)
            .build()));
    public static final Item TORCHBERRY_JUICE = registerDrink("torchberry_juice", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 1200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item PHYTOCHEMICAL_JUICE = registerDrink("phytochemical_juice", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.POISON_RANGE), 1200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item GLACIER_ICE_TEA = registerDrink("glacier_ice_tea", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.FROZEN_RANGE), 1200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item TWILIGHT_SPRING = registerDrink("twilight_spring", drink(0, 0.0F)
            .effect(potionId(MobEffects.RESISTANCE), 1200, 1, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item TEAR_DRINK = registerDrink("tear_drink", drink(1, 0.0F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 12000, 0, 1.0F)
            .effect(potionId(MobEffects.RESISTANCE), 12000, 1, 1.0F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 1200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));

    public static final Item FIERY_KNIFE = register("fiery_knife", new FieryKnifeItem());
    public static final Item IRONWOOD_KNIFE = register("ironwood_knife", new EnchantedKnifeItem(TFItems.TOOL_IRONWOOD, 2.5D,
            enchantments(net.minecraft.init.Enchantments.KNOCKBACK, 1, net.minecraft.init.Enchantments.UNBREAKING, 1)));
    public static final Item STEELEAF_KNIFE = register("steeleaf_knife", new EnchantedKnifeItem(TFItems.TOOL_STEELEAF, 3.5D,
            enchantments(net.minecraft.init.Enchantments.LOOTING, 2, net.minecraft.init.Enchantments.FORTUNE, 2)));
    public static final Item KNIGHTMETAL_KNIFE = register("knightmetal_knife", new KnightmetalKnifeItem());
    public static final Item TEARDROP_SWORD = register("teardrop_sword", new TeardropSwordItem());

    private TwilightDelightItems() {
    }

    public static void registerAll(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS.values()) {
            event.getRegistry().register(item);
        }
    }

    private static Item registerFood(String path, FoodItemApi.FoodItemSettings.Builder builder) {
        return register(path, new TwilightFoodItem(builder.build(), false, ItemStack.EMPTY, false));
    }

    private static Item registerFastFood(String path, FoodItemApi.FoodItemSettings.Builder builder) {
        return register(path, new TwilightFoodItem(builder.build(), true, ItemStack.EMPTY, false));
    }

    private static Item registerFireproofFastFood(String path, FoodItemApi.FoodItemSettings.Builder builder) {
        return register(path, new TwilightFoodItem(builder.build(), true, ItemStack.EMPTY, true));
    }

    private static Item registerStickFood(String path, FoodItemApi.FoodItemSettings.Builder builder) {
        return register(path, new TwilightFoodItem(builder.build(), true, new ItemStack(Items.STICK), false));
    }

    private static Item registerBowlFood(String path, FoodItemApi.FoodItemSettings.Builder builder) {
        return register(path, FoodItemApi.createFood(builder.bowlContainer().build()));
    }

    private static Item registerDrink(String path, FoodItemApi.DrinkItemSettings.Builder builder) {
        return register(path, FoodItemApi.createDrink(builder.drinkEffect(ItemDrinkableTooltip.DrinkEffect.NONE).build()));
    }

    private static Item register(String path, Item item) {
        item.setRegistryName(new ResourceLocation(TwilightDelightLegacy.MOD_ID, path));
        item.setTranslationKey(TwilightDelightLegacy.MOD_ID + "." + path);
        item.setCreativeTab(TwilightDelightCreativeTab.TAB);
        ITEMS.put(path, item);
        return item;
    }

    private static FoodItemApi.FoodItemSettings.Builder food(int nutrition, float saturation) {
        return FoodItemApi.FoodItemSettings.builder().nutrition(nutrition).saturation(saturation);
    }

    private static FoodItemApi.DrinkItemSettings.Builder drink(int nutrition, float saturation) {
        return FoodItemApi.DrinkItemSettings.builder().nutrition(nutrition).saturation(saturation).containerItem(new ItemStack(Items.GLASS_BOTTLE));
    }

    private static ResourceLocation potionId(Potion potion) {
        return potion.getRegistryName();
    }

    private static ResourceLocation auroraGlowingId() {
        return new ResourceLocation(TwilightDelightLegacy.MOD_ID, "aurora_glowing");
    }

    private static ResourceLocation frostyId() {
        return new ResourceLocation("twilightforest", "frosty");
    }

    private static Map<Enchantment, Integer> enchantments(Enchantment enchantment, int level) {
        Map<Enchantment, Integer> enchantments = new LinkedHashMap<>();
        enchantments.put(enchantment, level);
        return enchantments;
    }

    private static Map<Enchantment, Integer> enchantments(Enchantment first, int firstLevel, Enchantment second, int secondLevel) {
        Map<Enchantment, Integer> enchantments = enchantments(first, firstLevel);
        enchantments.put(second, secondLevel);
        return enchantments;
    }

    public static class EnchantedKnifeItem extends ItemKnifeBase {
        private final Map<Enchantment, Integer> displayedEnchantments;

        public EnchantedKnifeItem(Item.ToolMaterial material, double attackDamage, Map<Enchantment, Integer> displayedEnchantments) {
            super(material, attackDamage, -2.0D);
            this.displayedEnchantments = displayedEnchantments;
            this.setMaxStackSize(1);
        }

        @Override
        public void getSubItems(net.minecraft.creativetab.CreativeTabs tab, NonNullList<ItemStack> items) {
            if (this.isInCreativeTab(tab)) {
                ItemStack itemStack = new ItemStack(this);
                EnchantmentHelper.setEnchantments(this.displayedEnchantments, itemStack);
                items.add(itemStack);
            }
        }

        @Override
        public boolean hitEntity(ItemStack stack, net.minecraft.entity.EntityLivingBase target, net.minecraft.entity.EntityLivingBase attacker) {
            return super.hitEntity(stack, target, attacker);
        }

        @Override
        public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
            return enchantment == net.minecraft.init.Enchantments.FORTUNE
                    || super.canApplyAtEnchantingTable(stack, enchantment);
        }
    }
}

