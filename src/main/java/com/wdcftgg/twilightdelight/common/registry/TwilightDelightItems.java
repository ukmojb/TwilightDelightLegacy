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

    public static final Item HYDRA_PIECE = registerFood("hydra_piece", food(9, 2.0F).effect(potionId(MobEffects.REGENERATION), 20, 0, 1.0F));
    public static final Item RAW_VENISON_RIB = registerFood("raw_venison_rib", food(2, 0.25F));
    public static final Item COOKED_VENISON_RIB = registerFood("cooked_venison_rib", food(4, 0.875F));
    public static final Item RAW_MEEF_SLICE = registerFood("raw_meef_slice", food(1, 0.7F));
    public static final Item COOKED_MEEF_SLICE = registerFood("cooked_meef_slice", food(3, 0.6F));
    public static final Item RAW_INSECT = registerFood("raw_insect", food(2, 0.2F));
    public static final Item COOKED_INSECT = registerFood("cooked_insect", food(6, 0.6F));
    public static final Item TORCHBERRY_COOKIE = registerFood("torchberry_cookie", food(2, 0.2F));
    public static final Item CHOCOLATE_WAFER = registerFood("chocolate_wafer", food(9, 0.6F));
    public static final Item EXPERIMENT_113 = registerFood("experiment_113", food(6, 0.2F).effect(potionId(MobEffects.WEAKNESS), 100, 0, 0.33F));
    public static final Item EXPERIMENT_110 = registerFood("experiment_110", food(12, 0.3F)
            .effect(potionId(MobEffects.NAUSEA), 1200, 0, 1.0F)
            .effect(potionId(MobEffects.HEALTH_BOOST), 2400, 4, 1.0F)
            .effect(potionId(MobEffects.NIGHT_VISION), 2400, 0, 1.0F));
    public static final Item BERRY_STICK = registerFood("berry_stick", food(4, 0.2F));

    public static final Item GLOWSTEW = registerBowlFood("glowstew", food(7, 0.675F)
            .effect(potionId(MobEffects.GLOWING), 200, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 1200, 1, 1.0F)
            .maxStackSize(1));
    public static final Item GLOW_VENISON_RIB_WITH_PASTA = registerBowlFood("glow_venison_rib_with_pasta", food(12, 0.7F)
            .effect(potionId(MobEffects.GLOWING), 200, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 1, 1.0F)
            .maxStackSize(16));
    public static final Item FRIED_INSECT = registerBowlFood("fried_insect", food(10, 0.61F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16));
    public static final Item THOUSAND_PLANT_STEW = registerBowlFood("thousand_plant_stew", food(10, 0.61F)
            .effect(potionId(MobEffects.HEALTH_BOOST), 600, 1, 1.0F)
            .effect(potionId(MobEffects.NAUSEA), 300, 1, 0.1F)
            .maxStackSize(16));
    public static final Item GRILLED_GHAST = registerBowlFood("grilled_ghast", food(10, 0.72F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 1, 1.0F)
            .maxStackSize(16));
    public static final Item LILY_CHICKEN = registerBowlFood("lily_chicken", food(16, 0.875F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 6000, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 1, 1.0F)
            .maxStackSize(16));
    public static final Item FIERY_SNAKES = registerBowlFood("fiery_snakes", food(20, 1.9F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 12000, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 6000, 1, 1.0F)
            .effect(potionId(MobEffects.STRENGTH), 6000, 1, 1.0F)
            .effect(potionId(MobEffects.REGENERATION), 400, 1, 1.0F)
            .maxStackSize(16));
    public static final Item AURORA_ICE_CREAM = registerBowlFood("aurora_ice_cream", food(5, 0.2F)
            .effect(potionId(MobEffects.SPEED), 1200, 2, 1.0F)
            .effect(potionId(MobEffects.SLOWNESS), 600, 0, 1.0F)
            .maxStackSize(16));

    public static final Item THORN_ROSE_TEA = register("thorn_rose_tea", new ThornRoseTeaItem(drink(4, 0.25F)
            .effect(potionId(MobEffects.REGENERATION), 100, 1, 1.0F)
            .alwaysEdible()
            .maxStackSize(16)
            .build()));
    public static final Item TORCHBERRY_JUICE = registerDrink("torchberry_juice", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.FIRE_RANGE), 3600, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item PHYTOCHEMICAL_JUICE = registerDrink("phytochemical_juice", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.POISON_RANGE), 3600, 2, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item GLACIER_ICE_TEA = registerDrink("glacier_ice_tea", drink(4, 0.25F)
            .effect(potionId(TwilightDelightPotions.FROZEN_RANGE), 7200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item TWILIGHT_SPRING = registerDrink("twilight_spring", drink(0, 0.0F)
            .effect(potionId(MobEffects.RESISTANCE), 600, 1, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));
    public static final Item TEAR_DRINK = registerDrink("tear_drink", drink(1, 0.0F)
            .effect(potionId(MobEffects.FIRE_RESISTANCE), 24000, 0, 1.0F)
            .effect(potionId(TwilightDelightPotions.TEMPORAL_SADNESS), 1200, 0, 1.0F)
            .alwaysEdible()
            .maxStackSize(16));

    public static final Item FIERY_KNIFE = register("fiery_knife", new FieryKnifeItem());
    public static final Item IRONWOOD_KNIFE = register("ironwood_knife", new EnchantedKnifeItem(TFItems.TOOL_IRONWOOD, 2.5D, enchantments(net.minecraft.init.Enchantments.KNOCKBACK, 1)));
    public static final Item STEELEAF_KNIFE = register("steeleaf_knife", new EnchantedKnifeItem(TFItems.TOOL_STEELEAF, 3.5D, enchantments(net.minecraft.init.Enchantments.LOOTING, 2)));
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
        return register(path, FoodItemApi.createFood(builder.build()));
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

    private static Map<Enchantment, Integer> enchantments(Enchantment enchantment, int level) {
        Map<Enchantment, Integer> enchantments = new LinkedHashMap<>();
        enchantments.put(enchantment, level);
        return enchantments;
    }

    public static class EnchantedKnifeItem extends ItemKnifeBase implements FireResistantItemSupport {
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
            stack.damageItem(2, attacker);
            return true;
        }

        @Override
        public boolean hasCustomEntity(ItemStack stack) {
            return this.hasFireResistantEntity(stack);
        }

        @Override
        @Nullable
        public Entity createEntity(World world, Entity location, ItemStack itemStack) {
            return this.createFireResistantEntity(world, location, itemStack);
        }
    }
}

