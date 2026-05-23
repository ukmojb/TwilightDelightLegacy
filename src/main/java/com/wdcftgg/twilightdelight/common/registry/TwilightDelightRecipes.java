package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.recipe.CookingPotRecipeApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.CuttingBoardRecipeApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.knife.HuntingDropRecipeApi;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMiniGhast;
import twilightforest.entity.EntityTFTowerGhast;
import twilightforest.entity.passive.EntityTFBoar;
import twilightforest.item.TFItems;


public final class TwilightDelightRecipes {

    private TwilightDelightRecipes() {
    }

    public static void registerForgeRecipes(RegistryEvent.Register<IRecipe> event) {
        registerCraftingRecipes(event);
    }

    public static void registerRuntimeRecipes() {
        registerSmeltingRecipes();
        registerCookingPotRecipes();
        registerCuttingBoardRecipes();
        registerHuntingDropRecipes();
    }

    private static void registerCraftingRecipes(RegistryEvent.Register<IRecipe> event) {
        registerShapeless(event, "aurora_ice_cream", stack(TwilightDelightItems.AURORA_ICE_CREAM),
                Items.BOWL, Items.SNOWBALL, Items.SNOWBALL, Items.SNOWBALL, Blocks.ICE, TFBlocks.aurora_block);
        registerShapeless(event, "berry_stick", stack(TwilightDelightItems.BERRY_STICK),
                Items.APPLE, Items.CHORUS_FRUIT, TFItems.torchberries, Items.STICK);
        registerShapeless(event, "chocolate_wafer", stack(TwilightDelightItems.CHOCOLATE_WAFER),
                TFItems.maze_wafer, new ItemStack(Items.DYE, 1, 3), TFItems.maze_wafer);
        registerShapeless(event, "glowstew", stack(TwilightDelightItems.GLOWSTEW),
                Items.BOWL, Items.GLOWSTONE_DUST, new ItemStack(TFBlocks.twilight_plant, 1, 4), TFItems.torchberries);
        registerShapeless(event, "thousand_plant_stew", stack(TwilightDelightItems.THOUSAND_PLANT_STEW),
                Items.BOWL,
                new ItemStack(TFBlocks.twilight_plant, 1, 1),
                new ItemStack(TFBlocks.twilight_plant, 1, 8),
                new ItemStack(TFBlocks.twilight_plant, 1, 9),
                new ItemStack(TFBlocks.twilight_plant, 1, 0),
                new ItemStack(TFBlocks.root, 1, 1),
                new ItemStack(TFBlocks.twilight_plant, 1, 7),
                Blocks.VINE,
                new ItemStack(TFBlocks.twilight_plant, 1, 3));

        registerShaped(event, "torchberry_cookie", new ItemStack(TwilightDelightItems.TORCHBERRY_COOKIE, 8),
                "WTW",
                'W', Items.WHEAT,
                'T', TFItems.torchberries);
        registerShaped(event, "fiery_knife", stack(TwilightDelightItems.FIERY_KNIFE),
                " F",
                "B ",
                'F', TFItems.fiery_ingot,
                'B', Items.BLAZE_ROD);
        registerShapeless(event, "fiery_knife_from_iron_knife", stack(TwilightDelightItems.FIERY_KNIFE),
                item("farmersdelight:iron_knife"), TFItems.fiery_blood, Items.BLAZE_ROD);
        registerShapeless(event, "fiery_knife_from_tears", stack(TwilightDelightItems.FIERY_KNIFE),
                item("farmersdelight:iron_knife"), TFItems.fiery_tears, Items.BLAZE_ROD);
        registerShaped(event, "ironwood_knife", enchantedStack(TwilightDelightItems.IRONWOOD_KNIFE, Enchantments.KNOCKBACK, 1),
                " # ",
                "X  ",
                "   ",
                '#', "ingotIronwood",
                'X', "stickWood");
        registerShaped(event, "steeleaf_knife", enchantedStack(TwilightDelightItems.STEELEAF_KNIFE, Enchantments.LOOTING, 2),
                " # ",
                "X  ",
                "   ",
                '#', "ingotSteeleaf",
                'X', "stickWood");
        registerShaped(event, "knightmetal_knife", stack(TwilightDelightItems.KNIGHTMETAL_KNIFE),
                " K",
                "S ",
                'K', TFItems.knightmetal_ingot,
                'S', Items.STICK);
        registerShaped(event, "lily_chicken_block", stack(TwilightDelightBlocks.LILY_CHICKEN_BLOCK),
                "L",
                "C",
                "W",
                'L', TFBlocks.huge_lilypad,
                'C', item("farmersdelight:roast_chicken_block"),
                'W', TFBlocks.huge_waterlily);
        registerShapeless(event, "fiery_snakes_block", stack(TwilightDelightBlocks.FIERY_SNAKES_BLOCK),
                Items.BOWL, TFItems.hydra_chop, TFItems.hydra_chop, TFItems.naga_scale, TFItems.fiery_blood);
        registerShaped(event, "maze_stove", stack(TwilightDelightBlocks.MAZE_STOVE),
                "KKK",
                "MTM",
                "MFM",
                'K', TFItems.knightmetal_ingot,
                'M', new ItemStack(TFBlocks.maze_stone, 1, 1),
                'T', TFItems.torchberries,
                'F', Blocks.FURNACE);
        registerShapeless(event, "teardrop_sword", stack(TwilightDelightItems.TEARDROP_SWORD),
                TFItems.fiery_sword, TwilightDelightItems.EXPERIMENT_110);
    }

    private static void registerSmeltingRecipes() {
        GameRegistry.addSmelting(TwilightDelightItems.RAW_VENISON_RIB, stack(TwilightDelightItems.COOKED_VENISON_RIB), 1.0F);
        GameRegistry.addSmelting(TwilightDelightItems.RAW_MEEF_SLICE, stack(TwilightDelightItems.COOKED_MEEF_SLICE), 1.0F);
        GameRegistry.addSmelting(TwilightDelightItems.RAW_INSECT, stack(TwilightDelightItems.COOKED_INSECT), 1.0F);
    }

    private static void registerCookingPotRecipes() {
        Item buildersTea = ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", "builders_tea"));
        if (buildersTea != null) {
            registerCooking("builders_tea", new String[] {"twilightforest:leaves:3", "minecraft:sugar"},
                    new ItemStack(buildersTea), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        }
        registerCooking("fiery_snakes", new String[] {"ore:listAllhydra", "twilightforest:fiery_blood", "twilightforest:naga_scale", "farmersdelight:tomato_sauce", "twilightforest:torchberries"},
                stack(TwilightDelightBlocks.FIERY_SNAKES_BLOCK), new ItemStack(Items.BOWL), 400, 0.5F);
        registerCooking("fried_insect", new String[] {"twilightdelight:raw_insect", "farmersdelight:onion", "minecraft:carrot"},
                stack(TwilightDelightItems.FRIED_INSECT), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("glacier_ice_tea", new String[] {"twilightforest:ice_bomb", "minecraft:ice", "twilightforest:arctic_fur", "minecraft:sugar"},
                stack(TwilightDelightItems.GLACIER_ICE_TEA), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("glowstew", new String[] {"minecraft:glowstone_dust", "twilightforest:twilight_plant@4", "twilightforest:torchberries"},
                stack(TwilightDelightItems.GLOWSTEW), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("glow_venison_rib_with_pasta", new String[] {"twilightdelight:glowstew", "ore:listAllvenisoncooked", "farmersdelight:raw_pasta", "twilightforest:liveroot", "minecraft:beetroot"},
                stack(TwilightDelightItems.GLOW_VENISON_RIB_WITH_PASTA), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("grilled_ghast", new String[] {"farmersdelight:tomato", "minecraft:beetroot", "twilightforest:fiery_tears", "twilightforest:experiment_115", "twilightforest:experiment_115"},
                stack(TwilightDelightItems.GRILLED_GHAST), new ItemStack(Items.BOWL), 400, 0.35F);
        registerCooking("meef_stroganoff", new String[] {"minecraft:mushroom_stew", "ore:listAllmeefcooked", "twilightforest:torchberries", "twilightforest:liveroot", "farmersdelight:onion"},
                new ItemStack(TFItems.meef_stroganoff), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("phytochemical_juice", new String[] {"twilightforest:liveroot", "twilightforest:steeleaf_ingot", "minecraft:sugar"},
                stack(TwilightDelightItems.PHYTOCHEMICAL_JUICE), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("tear_drink", new String[] {"twilightforest:fiery_tears", "minecraft:ghast_tear"},
                stack(TwilightDelightItems.TEAR_DRINK), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("thorn_rose_tea", new String[] {"twilightforest:thorn_rose", "minecraft:sugar"},
                stack(TwilightDelightItems.THORN_ROSE_TEA), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("torchberry_juice", new String[] {"twilightforest:torchberries", "minecraft:sugar"},
                stack(TwilightDelightItems.TORCHBERRY_JUICE), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("twilight_spring", new String[] {"twilightforest:ironwood_raw", "minecraft:ice"},
                stack(TwilightDelightItems.TWILIGHT_SPRING), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
    }

    private static void registerCuttingBoardRecipes() {
        registerKnifeCutting("cooked_meef", itemStack("twilightforest:cooked_meef"), stack(TwilightDelightItems.COOKED_MEEF_SLICE), 2, 1.0F);
        registerKnifeCutting("cooked_venison", itemStack("twilightforest:cooked_venison"), stack(TwilightDelightItems.COOKED_VENISON_RIB), 2, 1.0F);
        registerKnifeCutting("hydra_chop", itemStack("twilightforest:hydra_chop"), stack(TwilightDelightItems.HYDRA_PIECE), 2, 1.0F);
        registerKnifeCutting("insect", "ore:listAllinsectTarget", stack(TwilightDelightItems.RAW_INSECT), 2, 1.0F);
        registerKnifeCutting("raw_meef", itemStack("twilightforest:raw_meef"), stack(TwilightDelightItems.RAW_MEEF_SLICE), 2, 1.0F);
        registerKnifeCutting("raw_venison", itemStack("twilightforest:raw_venison"), stack(TwilightDelightItems.RAW_VENISON_RIB), 2, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/ur_ghast"),
                new String[] {"twilightforest:trophy@3"},
                null,
                new String[] {"twilightforest:experiment_115", "twilightdelight:experiment_113", "twilightdelight:experiment_110"},
                new int[] {4, 9, 1},
                new float[] {1.0F, 1.0F, 0.1F});
    }

    private static void registerHuntingDropRecipes() {
        HuntingDropRecipeApi.registerRecipe("twilightdelight:scavenging_115_from_ghast", target -> target.getClass() == EntityGhast.class,
                new ItemStack(TFItems.experiment_115), false, 0.3F, 0.1F, false, entityId("minecraft", "ghast"));
        HuntingDropRecipeApi.registerRecipe("twilightdelight:scavenging_115_from_carminite_ghastguard", target -> target instanceof EntityTFTowerGhast,
                new ItemStack(TFItems.experiment_115), false, 0.5F, 0.1F, false, entityId("twilightforest", "tower_ghast"));
        HuntingDropRecipeApi.registerRecipe("twilightdelight:scavenging_115_from_carminite_ghastling", target -> target instanceof EntityTFMiniGhast,
                new ItemStack(TFItems.experiment_115), false, 0.01F, 0.1F, false, entityId("twilightforest", "mini_ghast"));
        HuntingDropRecipeApi.registerRecipe("twilightdelight:scavenging_ham_from_wild_boar", target -> target instanceof EntityTFBoar && !target.isBurning(),
                itemStack("farmersdelight:ham"), false, 0.67F, 0.1F, false, entityId("twilightforest", "wild_boar"));
    }

    private static void registerCooking(String name, String[] ingredients, ItemStack result, ItemStack container, int cookingTime, float experience) {
        CookingPotRecipeApi.registerRecipe(recipeKey("cooking/" + name), ingredients, result, container, cookingTime, experience);
    }

    private static void registerKnifeCutting(String name, ItemStack inputStack, ItemStack outputStack, int count, float chance) {
        registerKnifeCutting(name, itemToken(inputStack), outputStack, count, chance);
    }

    private static void registerKnifeCutting(String name, String inputToken, ItemStack outputStack, int count, float chance) {
        String normalOutputToken = itemToken(outputStack);
        String fieryOutputToken = itemToken(getFieryCuttingResult(outputStack));
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/fiery_" + name), inputToken, "twilightdelight:fiery_knife", fieryOutputToken, count, chance);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/" + name), inputToken, null, normalOutputToken, count, chance);
    }

    private static ItemStack getFieryCuttingResult(ItemStack outputStack) {
        ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(outputStack);
        return smeltingResult.isEmpty() ? outputStack : smeltingResult.copy();
    }

    private static String itemToken(ItemStack itemStack) {
        ResourceLocation registryName = itemStack.getItem().getRegistryName();
        String token = registryName.toString();
        if (itemStack.getMetadata() != 0) {
            token += "@" + itemStack.getMetadata();
        }
        return token;
    }

    private static void registerShaped(RegistryEvent.Register<IRecipe> event, String name, ItemStack output, Object... recipe) {
        ShapedOreRecipe shapedOreRecipe = new ShapedOreRecipe(location(name), output, recipe);
        registerRecipe(event, name, shapedOreRecipe);
    }

    private static void registerShapeless(RegistryEvent.Register<IRecipe> event, String name, ItemStack output, Object... ingredients) {
        ShapelessOreRecipe shapelessOreRecipe = new ShapelessOreRecipe(location(name), output, ingredients);
        registerRecipe(event, name, shapelessOreRecipe);
    }

    private static void registerRecipe(RegistryEvent.Register<IRecipe> event, String name, IRecipe recipe) {
        recipe.setRegistryName(location(name));
        event.getRegistry().register(recipe);
    }

    private static ItemStack stack(Item item) {
        return new ItemStack(item);
    }

    private static ItemStack stack(Block block) {
        return new ItemStack(block);
    }

    private static ItemStack enchantedStack(Item item, net.minecraft.enchantment.Enchantment enchantment, int level) {
        ItemStack itemStack = stack(item);
        EnchantmentHelper.setEnchantments(new java.util.LinkedHashMap<net.minecraft.enchantment.Enchantment, Integer>() {{
            put(enchantment, level);
        }}, itemStack);
        return itemStack;
    }

    private static ItemStack itemStack(String registryName) {
        Item item = item(registryName);
        return item == Items.AIR ? ItemStack.EMPTY : new ItemStack(item);
    }

    private static Item item(String registryName) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(registryName));
        return item == null ? Items.AIR : item;
    }

    private static ResourceLocation location(String path) {
        return new ResourceLocation(TwilightDelightLegacy.MOD_ID, path);
    }

    private static String recipeKey(String path) {
        return TwilightDelightLegacy.MOD_ID + ":" + path;
    }

    private static ResourceLocation entityId(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }
}
