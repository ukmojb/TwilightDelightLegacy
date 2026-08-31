package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.recipe.CookingPotRecipeApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.CuttingBoardRecipeApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.knife.HuntingDropRecipeApi;
import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.recipe.FieryFoodServingRecipe;
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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMinotaur;
import twilightforest.entity.EntityTFMiniGhast;
import twilightforest.entity.EntityTFTowerGhast;
import twilightforest.entity.boss.EntityTFMinoshroom;
import twilightforest.entity.passive.EntityTFBoar;
import twilightforest.item.TFItems;


public final class TwilightDelightRecipes {

    private TwilightDelightRecipes() {
    }

    public static void registerForgeRecipes(RegistryEvent.Register<IRecipe> event) {
        registerRecipe(event, "fiery_food_serving", new FieryFoodServingRecipe());
        registerCraftingRecipes(event);
    }

    public static void registerRuntimeRecipes() {
        registerSmeltingRecipes();
        registerCookingPotRecipes();
        registerCuttingBoardRecipes();
        registerHuntingDropRecipes();
    }

    private static void registerCraftingRecipes(RegistryEvent.Register<IRecipe> event) {
        registerShapeless(event, "berry_stick", stack(TwilightDelightItems.BERRY_STICK),
                Items.APPLE, Items.CHORUS_FRUIT, TFItems.torchberries, Items.STICK);
        registerShapeless(event, "borer_tear_soup", stack(TwilightDelightItems.BORER_TEAR_SOUP),
                Items.BOWL, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, TFItems.borer_essence);
        registerShapeless(event, "chocolate_113", stack(TwilightDelightItems.CHOCOLATE_113),
                TwilightDelightItems.EXPERIMENT_113, "listAllmilk", Items.SUGAR, new ItemStack(Items.DYE, 1, 3));
        registerShapeless(event, "chocolate_wafer", stack(TwilightDelightItems.CHOCOLATE_WAFER),
                TFItems.maze_wafer, new ItemStack(Items.DYE, 1, 3), TFItems.maze_wafer);
        registerShapeless(event, "ghast_brain_salad", stack(TwilightDelightItems.GHAST_BRAIN_SALAD),
                Items.BOWL, "cropCabbage", "cropOnion", "cropTomato", TwilightDelightItems.EXPERIMENT_110,
                TFItems.borer_essence, TFItems.transformation_powder);
        registerShapeless(event, "ghast_burger", stack(TwilightDelightItems.GHAST_BURGER),
                Items.BREAD, TFItems.experiment_115, Items.BEETROOT, "cropTomato", "cropOnion");
        registerShapeless(event, "glow_113", stack(TwilightDelightItems.GLOW_113),
                TwilightDelightItems.EXPERIMENT_113, TwilightDelightItems.GLOWSTEW);
        if (Loader.isModLoaded("futuremc") && item("futuremc:honey_bottle") != Items.AIR) {
            registerShapeless(event, "honey_113", stack(TwilightDelightItems.HONEY_113),
                    TwilightDelightItems.EXPERIMENT_113, item("futuremc:honey_bottle"));
        } else {
            registerShapeless(event, "honey_113", stack(TwilightDelightItems.HONEY_113),
                    TwilightDelightItems.EXPERIMENT_113, Items.SUGAR, new ItemStack(Items.DYE, 1, 11));
        }
        registerShapeless(event, "hydra_burger", stack(TwilightDelightItems.HYDRA_BURGER),
                Items.BREAD, "listAllhydra", "cropCabbage", "cropTomato", "cropOnion");
        registerShapeless(event, "meef_wrap", stack(TwilightDelightItems.MEEF_WRAP),
                Items.BREAD, "listAllmeefcooked", "cropCabbage", "cropOnion");
        registerShapeless(event, "milky_113", stack(TwilightDelightItems.MILKY_113),
                TwilightDelightItems.EXPERIMENT_113, "listAllmilk", Items.SUGAR);
        registerShapeless(event, "torchberry_venison_sandwich", stack(TwilightDelightItems.TORCHBERRY_VENISON_SANDWICH),
                Items.BREAD, "listAllvenisoncooked", "cropCabbage", TFItems.torchberries);
        registerShapeless(event, "torchberries_from_crate", new ItemStack(TFItems.torchberries, 9),
                TwilightDelightBlocks.TORCHBERRIES_CRATE);
        registerShapeless(event, "glacier_ice_tea", stack(TwilightDelightItems.GLACIER_ICE_TEA),
                Items.GLASS_BOTTLE, TFItems.ice_bomb, Blocks.ICE, TFItems.arctic_fur, Items.SUGAR);
        registerShapeless(event, "raw_ironwood", new ItemStack(TFItems.ironwood_raw),
                TFItems.liveroot, Items.IRON_INGOT, Items.GOLD_NUGGET);
        registerShapeless(event, "twilight_spring", stack(TwilightDelightItems.TWILIGHT_SPRING),
                Items.GLASS_BOTTLE, TFItems.ironwood_raw, Blocks.ICE);
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
        registerShaped(event, "maze_wafer", new ItemStack(TFItems.maze_wafer, 12),
                "WWW",
                "MLM",
                "WWW",
                'W', Items.WHEAT,
                'M', "listAllmilk",
                'L', TFItems.liveroot);
        registerShaped(event, "torchberry_pie", stack(TwilightDelightBlocks.TORCHBERRY_PIE),
                "WWW",
                "TTT",
                "SPS",
                'W', Items.WHEAT,
                'T', TFItems.torchberries,
                'S', Items.SUGAR,
                'P', item("farmersdelight:pie_crust"));
        registerShaped(event, "torchberry_pie_from_slices", stack(TwilightDelightBlocks.TORCHBERRY_PIE),
                "SS",
                "SS",
                'S', TwilightDelightItems.TORCHBERRY_PIE_SLICE);
        registerShaped(event, "torchberries_crate", stack(TwilightDelightBlocks.TORCHBERRIES_CRATE),
                "TTT",
                "TTT",
                "TTT",
                'T', TFItems.torchberries);
        registerShaped(event, "meef_wellington_block", stack(TwilightDelightBlocks.MEEF_WELLINGTON_BLOCK),
                "BAB",
                "DCD",
                "FEF",
                'A', item("farmersdelight:pie_crust"),
                'B', "listAllEgg",
                'C', TwilightDelightItems.MUSHGLOOM_SAUCE,
                'D', "listAllmeefcooked",
                'E', Items.BOWL,
                'F', item("farmersdelight:bacon"));
        registerShaped(event, "twilight_oak_cabinet", stack(TwilightDelightBlocks.TWILIGHT_OAK_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.twilight_oak_slab,
                'D', item("twilightforest:twilight_oak_trapdoor"));
        registerShaped(event, "canopy_cabinet", stack(TwilightDelightBlocks.CANOPY_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.canopy_slab,
                'D', item("twilightforest:canopy_trapdoor"));
        registerShaped(event, "dark_cabinet", stack(TwilightDelightBlocks.DARK_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.dark_slab,
                'D', item("twilightforest:dark_trapdoor"));
        registerShaped(event, "mangrove_cabinet", stack(TwilightDelightBlocks.MANGROVE_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.mangrove_slab,
                'D', item("twilightforest:mangrove_trapdoor"));
        registerShaped(event, "mining_cabinet", stack(TwilightDelightBlocks.MINING_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.mine_slab,
                'D', item("twilightforest:mine_trapdoor"));
        registerShaped(event, "sorting_cabinet", stack(TwilightDelightBlocks.SORTING_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.sort_slab,
                'D', item("twilightforest:sort_trapdoor"));
        registerShaped(event, "time_cabinet", stack(TwilightDelightBlocks.TIME_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.time_slab,
                'D', item("twilightforest:time_trapdoor"));
        registerShaped(event, "transformation_cabinet", stack(TwilightDelightBlocks.TRANSFORMATION_CABINET),
                "SSS",
                "D D",
                "SSS",
                'S', TFBlocks.trans_slab,
                'D', item("twilightforest:trans_trapdoor"));
        registerShaped(event, "fiery_knife", stack(TwilightDelightItems.FIERY_KNIFE),
                "F",
                "B",
                'F', TFItems.fiery_ingot,
                'B', Items.BLAZE_ROD);
        registerShapeless(event, "fiery_knife_from_iron_knife", stack(TwilightDelightItems.FIERY_KNIFE),
                item("farmersdelight:iron_knife"), TFItems.fiery_blood, Items.BLAZE_ROD);
        registerShapeless(event, "fiery_knife_from_tears", stack(TwilightDelightItems.FIERY_KNIFE),
                item("farmersdelight:iron_knife"), TFItems.fiery_tears, Items.BLAZE_ROD);
        registerShaped(event, "ironwood_knife", enchantedStack(TwilightDelightItems.IRONWOOD_KNIFE,
                        Enchantments.KNOCKBACK, 1, Enchantments.UNBREAKING, 1),
                "#",
                "X",
                '#', "ingotIronwood",
                'X', "stickWood");
        registerShaped(event, "steeleaf_knife", enchantedStack(TwilightDelightItems.STEELEAF_KNIFE,
                        Enchantments.LOOTING, 2, Enchantments.FORTUNE, 2),
                "#",
                "X",
                '#', "ingotSteeleaf",
                'X', "stickWood");
        registerShaped(event, "knightmetal_knife", stack(TwilightDelightItems.KNIGHTMETAL_KNIFE),
                "K",
                "S",
                'K', TFItems.knightmetal_ingot,
                'S', Items.STICK);
        if (TwilightDelightItems.hasNetherDelightMachetes()) {
            registerMacheteRecipes(event);
        }
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
        registerShaped(event, "fiery_cooking_pot", stack(TwilightDelightBlocks.FIERY_COOKING_POT),
                "BSB",
                "FWF",
                "FFF",
                'B', new ItemStack(TFBlocks.maze_stone, 1, 1),
                'S', TFItems.ironwood_shovel,
                'F', TFItems.fiery_ingot,
                'W', Items.WATER_BUCKET);
        registerShapeless(event, "teardrop_sword", stack(TwilightDelightItems.TEARDROP_SWORD),
                TFItems.fiery_sword, TwilightDelightItems.EXPERIMENT_110);
    }

    private static void registerSmeltingRecipes() {
        GameRegistry.addSmelting(TwilightDelightItems.RAW_VENISON_RIB, stack(TwilightDelightItems.COOKED_VENISON_RIB), 1.0F);
        GameRegistry.addSmelting(TwilightDelightItems.RAW_MEEF_SLICE, stack(TwilightDelightItems.COOKED_MEEF_SLICE), 1.0F);
        GameRegistry.addSmelting(TwilightDelightItems.RAW_INSECT, stack(TwilightDelightItems.COOKED_INSECT), 1.0F);
        GameRegistry.addSmelting(TwilightDelightItems.RAW_TOMAHAWK_SMEAK, stack(TwilightDelightItems.COOKED_TOMAHAWK_SMEAK), 1.0F);
    }

    private static void registerCookingPotRecipes() {
        Item buildersTea = ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", "builders_tea"));
        if (buildersTea != null) {
            registerCooking("builders_tea", new String[] {"twilightforest:leaves:3", "minecraft:sugar"},
                    new ItemStack(buildersTea), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        }
        registerCooking("fiery_snakes", new String[] {"ore:listAllhydra", "ore:fieryVial", "twilightforest:naga_scale", "farmersdelight:tomato_sauce", "twilightforest:torchberries"},
                stack(TwilightDelightBlocks.FIERY_SNAKES_BLOCK), new ItemStack(Items.BOWL), 800, 0.5F);
        registerCooking("fried_insect", new String[] {"twilightdelight:raw_insect", "farmersdelight:onion", "minecraft:carrot"},
                stack(TwilightDelightItems.FRIED_INSECT), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("glacier_ice_tea", new String[] {"twilightforest:ice_bomb", "minecraft:ice", "twilightforest:arctic_fur", "minecraft:sugar"},
                stack(TwilightDelightItems.GLACIER_ICE_TEA), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("glowstew", new String[] {"minecraft:glowstone_dust", "twilightforest:twilight_plant@4", "twilightforest:torchberries"},
                stack(TwilightDelightItems.GLOWSTEW), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("glow_venison_rib_with_pasta", new String[] {"twilightdelight:glowstew", "ore:listAllvenisonraw", "farmersdelight:raw_pasta", "twilightforest:liveroot", "minecraft:beetroot"},
                stack(TwilightDelightItems.GLOW_VENISON_RIB_WITH_PASTA), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("grilled_ghast", new String[] {"farmersdelight:tomato", "minecraft:beetroot", "ore:fieryVial", "twilightforest:experiment_115", "twilightforest:experiment_115"},
                stack(TwilightDelightItems.GRILLED_GHAST), new ItemStack(Items.BOWL), 800, 0.35F);
        registerCooking("grilled_tomahawk_smeak", new String[] {"twilightdelight:raw_tomahawk_smeak", "twilightdelight:mushgloom_sauce", "ore:listAllmilk"},
                stack(TwilightDelightItems.GRILLED_TOMAHAWK_SMEAK), new ItemStack(Items.BOWL), 1600, 0.35F);
        registerCooking("liveroot_pork_fried_rice", new String[] {"ore:cropRice", "ore:listAllporkraw", "ore:listAllporkraw", "ore:listAllEgg", "twilightforest:liveroot"},
                stack(TwilightDelightItems.LIVEROOT_PORK_FRIED_RICE), new ItemStack(Items.BOWL), 200, 0.5F);
        registerCooking("liveroot_venison_noodle_soup", new String[] {"ore:foodPasta", "ore:listAllvenisonraw", "farmersdelight:chicken_soup", "twilightforest:liveroot"},
                stack(TwilightDelightItems.LIVEROOT_VENISON_NOODLE_SOUP), new ItemStack(Items.BOWL), 200, 0.5F);
        registerCooking("lily_chicken_block", new String[] {"twilightforest:huge_lilypad", "farmersdelight:roast_chicken_block", "twilightforest:huge_waterlily"},
                stack(TwilightDelightBlocks.LILY_CHICKEN_BLOCK), ItemStack.EMPTY, 400, 0.35F);
        registerCooking("meef_stroganoff", new String[] {"minecraft:mushroom_stew", "twilightdelight:raw_tomahawk_smeak", "twilightforest:liveroot", "twilightforest:torchberries", "farmersdelight:onion"},
                new ItemStack(TFItems.meef_stroganoff), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("mushgloom_meef_pasta", new String[] {"twilightdelight:mushgloom_sauce", "ore:listAllmeefraw", "farmersdelight:raw_pasta"},
                stack(TwilightDelightItems.MUSHGLOOM_MEEF_PASTA), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("mushgloom_sauce", new String[] {"minecraft:brown_mushroom", "ore:mushgloom", "farmersdelight:onion"},
                stack(TwilightDelightItems.MUSHGLOOM_SAUCE), new ItemStack(Items.BOWL), 200, 0.35F);
        registerCooking("phytochemical_juice", new String[] {"twilightforest:liveroot", "twilightforest:steeleaf_ingot", "minecraft:sugar"},
                stack(TwilightDelightItems.PHYTOCHEMICAL_JUICE), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("tear_drink", new String[] {"twilightforest:fiery_tears", "minecraft:ghast_tear"},
                stack(TwilightDelightItems.TEAR_DRINK), new ItemStack(Items.GLASS_BOTTLE), 800, 0.35F);
        registerCooking("thorn_rose_tea", new String[] {"twilightforest:thorn_rose", "minecraft:sugar"},
                stack(TwilightDelightItems.THORN_ROSE_TEA), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("thousand_plant_stew", new String[] {"ore:plantRootStrand", "ore:plantFallenLeaves", "twilightforest:liveroot", "ore:plantTorchberry", "minecraft:vine", "ore:plantFiddlehead"},
                stack(TwilightDelightItems.THOUSAND_PLANT_STEW), new ItemStack(Items.BOWL), 400, 0.35F);
        registerCooking("torchberry_juice", new String[] {"twilightforest:torchberries", "minecraft:sugar"},
                stack(TwilightDelightItems.TORCHBERRY_JUICE), new ItemStack(Items.GLASS_BOTTLE), 200, 0.35F);
        registerCooking("transformation_powder", new String[] {"minecraft:nether_wart", "minecraft:fermented_spider_eye", "minecraft:fish@3", "minecraft:poisonous_potato", "farmersdelight:rotten_tomato", "twilightforest:borer_essence"},
                new ItemStack(TFItems.transformation_powder), itemStack("farmersdelight:canvas"), 800, 0.5F);
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
        registerKnifeCutting("raw_tomahawk_smeak", stack(TwilightDelightItems.RAW_TOMAHAWK_SMEAK), stack(TwilightDelightItems.RAW_MEEF_SLICE), 4, 1.0F);
        registerKnifeCutting("cooked_tomahawk_smeak", stack(TwilightDelightItems.COOKED_TOMAHAWK_SMEAK), stack(TwilightDelightItems.COOKED_MEEF_SLICE), 4, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/torchberry_pie"),
                itemToken(stack(TwilightDelightBlocks.TORCHBERRY_PIE)), null,
                itemToken(new ItemStack(TwilightDelightItems.TORCHBERRY_PIE_SLICE, 4)), 4, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/naga_scale"),
                itemToken(new ItemStack(TFItems.naga_scale)), itemToken(new ItemStack(TFItems.minotaur_axe)),
                itemToken(new ItemStack(TwilightDelightItems.NAGA_CHIP)), 4, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/mushgloom_colony"),
                itemToken(stack(TwilightDelightBlocks.MUSHGLOOM_COLONY)), null,
                itemToken(new ItemStack(TFBlocks.twilight_plant, 1, 4)), 5, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/naga_trophy"),
                "twilightforest:trophy@0", null, "twilightforest:naga_scale", 9, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/lich_trophy"),
                new String[] {"twilightforest:trophy@1"},
                null,
                new String[] {"minecraft:skull@0", "twilightforest:zombie_scepter", "twilightforest:lifedrain_scepter", "twilightforest:twilight_scepter"},
                new int[] {1, 1, 1, 1},
                new float[] {1.0F, 0.2F, 0.2F, 0.2F});
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/hydra_trophy"),
                "twilightforest:trophy@2", null, "twilightforest:hydra_chop", 4, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/ur_ghast_trophy"),
                new String[] {"twilightforest:trophy@3"},
                null,
                new String[] {"twilightforest:experiment_115", "twilightdelight:experiment_113", "twilightdelight:experiment_110"},
                new int[] {4, 9, 1},
                new float[] {1.0F, 1.0F, 0.1F});
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/knight_phantom_trophy"),
                "twilightforest:trophy@4", null, "twilightforest:phantom_helmet", 1, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/snow_queen_trophy"),
                new String[] {"twilightforest:trophy@5"},
                null,
                new String[] {"twilightforest:ice_bomb", "twilightforest:ice_sword", "twilightforest:glass_sword", "twilightforest:ice_bow"},
                new int[] {9, 1, 1, 1},
                new float[] {1.0F, 0.2F, 0.1F, 0.2F});
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/fiery_minoshroom_trophy"),
                new String[] {"twilightforest:trophy@6"},
                new String[] {"ore:toolFieryKnife"},
                new String[] {"twilightforest:cooked_meef", "minecraft:red_mushroom"},
                new int[] {9, 8},
                new float[] {1.0F, 0.5F});
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/minoshroom_trophy"),
                new String[] {"twilightforest:trophy@6"},
                null,
                new String[] {"twilightforest:raw_meef", "minecraft:red_mushroom"},
                new int[] {9, 8},
                new float[] {1.0F, 0.5F});
        CuttingBoardRecipeApi.registerRecipe(
                recipeKey("cutting/alpha_yeti_trophy"),
                new String[] {"twilightforest:trophy@7"},
                null,
                new String[] {"twilightforest:alpha_fur", "twilightforest:ice_bomb"},
                new int[] {9, 4},
                new float[] {1.0F, 0.5F});
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/quest_ram_trophy"),
                "twilightforest:trophy@8", null, "minecraft:mutton", 9, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/phantom_helmet"),
                "twilightforest:phantom_helmet", "ore:toolPickaxe", "twilightforest:armor_shard_cluster", 3, 1.0F);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/phantom_chestplate"),
                "twilightforest:phantom_chestplate", "ore:toolPickaxe", "twilightforest:armor_shard_cluster", 5, 1.0F);
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
        HuntingDropRecipeApi.registerRecipeJei("twilightdelight:scavenging_raw_tomahawk_smeak_from_minotaur",
                target -> target instanceof EntityTFMinotaur, new ItemStack(TwilightDelightItems.RAW_TOMAHAWK_SMEAK),
                false, 0.3F, 0.0F, false, entityId("twilightforest", "minotaur"));
        HuntingDropRecipeApi.registerRecipeJei("twilightdelight:scavenging_cooked_tomahawk_smeak_from_minotaur",
                target -> target instanceof EntityTFMinotaur, new ItemStack(TwilightDelightItems.COOKED_TOMAHAWK_SMEAK),
                false, 0.3F, 0.0F, true, entityId("twilightforest", "minotaur"));
        HuntingDropRecipeApi.registerRecipeJei("twilightdelight:scavenging_raw_tomahawk_smeak_from_minoshroom",
                target -> target instanceof EntityTFMinoshroom, new ItemStack(TwilightDelightItems.RAW_TOMAHAWK_SMEAK),
                false, 1.0F, 0.0F, false, entityId("twilightforest", "minoshroom"));
        HuntingDropRecipeApi.registerRecipeJei("twilightdelight:scavenging_cooked_tomahawk_smeak_from_minoshroom",
                target -> target instanceof EntityTFMinoshroom, new ItemStack(TwilightDelightItems.COOKED_TOMAHAWK_SMEAK),
                false, 1.0F, 0.0F, true, entityId("twilightforest", "minoshroom"));
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
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/fiery_" + name), inputToken, "ore:toolFieryKnife", fieryOutputToken, count, chance);
        CuttingBoardRecipeApi.registerRecipe(recipeKey("cutting/" + name), inputToken, null, normalOutputToken, count, chance);
    }

    private static void registerMacheteRecipes(RegistryEvent.Register<IRecipe> event) {
        registerShaped(event, "fiery_machete", stack(TwilightDelightItems.FIERY_MACHETE),
                "  F",
                " F ",
                "B  ",
                'F', TFItems.fiery_ingot,
                'B', Items.BLAZE_ROD);
        registerShaped(event, "ironwood_machete", enchantedStack(TwilightDelightItems.IRONWOOD_MACHETE,
                        Enchantments.KNOCKBACK, 1, Enchantments.UNBREAKING, 1),
                "  I",
                " I ",
                "S  ",
                'I', "ingotIronwood",
                'S', "stickWood");
        registerShaped(event, "steeleaf_machete", enchantedStack(TwilightDelightItems.STEELEAF_MACHETE,
                        Enchantments.LOOTING, 2, Enchantments.FORTUNE, 2),
                "  L",
                " L ",
                "S  ",
                'L', "ingotSteeleaf",
                'S', "stickWood");
        registerShaped(event, "knightmetal_machete", stack(TwilightDelightItems.KNIGHTMETAL_MACHETE),
                "  K",
                " K ",
                "S  ",
                'K', TFItems.knightmetal_ingot,
                'S', Items.STICK);

        Item ironMachete = item("nethers_delight_legacy:iron_machete");
        if (ironMachete != Items.AIR) {
            registerShapeless(event, "fiery_machete_from_iron_machete", stack(TwilightDelightItems.FIERY_MACHETE),
                    ironMachete, TFItems.fiery_blood, Items.BLAZE_ROD);
            registerShapeless(event, "fiery_machete_from_tears", stack(TwilightDelightItems.FIERY_MACHETE),
                    ironMachete, TFItems.fiery_tears, Items.BLAZE_ROD);
        }
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

    private static ItemStack enchantedStack(Item item,
                                            net.minecraft.enchantment.Enchantment first, int firstLevel,
                                            net.minecraft.enchantment.Enchantment second, int secondLevel) {
        ItemStack itemStack = stack(item);
        java.util.LinkedHashMap<net.minecraft.enchantment.Enchantment, Integer> enchantments = new java.util.LinkedHashMap<>();
        enchantments.put(first, firstLevel);
        enchantments.put(second, secondLevel);
        EnchantmentHelper.setEnchantments(enchantments, itemStack);
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
