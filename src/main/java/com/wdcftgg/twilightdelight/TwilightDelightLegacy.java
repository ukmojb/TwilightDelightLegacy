package com.wdcftgg.twilightdelight;

import com.wdcftgg.farmersdelightlegacy.api.heat.HeatSourceApi;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightBlocks;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightOreDictionary;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightRecipes;
import com.wdcftgg.twilightdelight.common.registry.TwilightDelightTileEntities;
import com.wdcftgg.twilightdelight.Tags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = TwilightDelightLegacy.MOD_ID,
        name = TwilightDelightLegacy.MOD_NAME,
        version = TwilightDelightLegacy.VERSION,
        dependencies = "required-after:twilightforest;required-after:farmersdelight",
        acceptedMinecraftVersions = "[1.12.2]"
)
public class TwilightDelightLegacy {

    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static TwilightDelightLegacy INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TwilightDelightTileEntities.registerAll();
        LOGGER.info("{} preInit completed.", MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        TwilightDelightOreDictionary.registerAll();
        TwilightDelightRecipes.registerRuntimeRecipes();
        registerHeatSources();
        LOGGER.info("{} init completed.", MOD_NAME);
    }

    private static void registerHeatSources() {
        HeatSourceApi.registerDirectHeatSourcePredicate("twilightdelight:maze_stove",
                (world, pos, state) -> state.getBlock() == TwilightDelightBlocks.MAZE_STOVE);
        HeatSourceApi.registerDirectHeatSourcePredicate("twilightdelight:twilightforest_fiery_block",
                (world, pos, state) -> ForgeRegistries.BLOCKS.getKey(state.getBlock()) != null
                        && ForgeRegistries.BLOCKS.getKey(state.getBlock()).equals(new ResourceLocation("twilightforest", "fiery_block")));
    }
}
