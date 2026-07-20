package com.wdcftgg.twilightdelight.common;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber(modid = TwilightDelightLegacy.MOD_ID)
public final class TwilightDelightConfig {

    public static int effectRange = 6;
    public static int auroraRange = 24;
    public static int auroraPeriod = 30;

    private static int syncedEffectRange = effectRange;
    private static int syncedAuroraRange = auroraRange;

    private static Configuration configuration;

    private TwilightDelightConfig() {
    }

    public static void load(File file) {
        configuration = new Configuration(file);
        sync();
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (TwilightDelightLegacy.MOD_ID.equals(event.getModID())) {
            sync();
        }
    }

    private static void sync() {
        if (configuration == null) {
            return;
        }
        effectRange = configuration.getInt("effectRange", "effects", 6, 0, 128,
                "Range for hostile fire, frost, and poison effects.");
        auroraRange = configuration.getInt("auroraRange", "effects", 24, 0, 128,
                "Maximum distance at which Aurora Glowing reveals entities.");
        auroraPeriod = configuration.getInt("auroraPeriod", "client", 30, 1, 10000,
                "Ticks used for one full Aurora Glowing color cycle.");
        syncedEffectRange = effectRange;
        syncedAuroraRange = auroraRange;
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public static int getEffectRange() {
        return syncedEffectRange;
    }

    public static int getAuroraRange() {
        return syncedAuroraRange;
    }

    public static void applyServerRanges(int serverEffectRange, int serverAuroraRange) {
        syncedEffectRange = Math.max(0, Math.min(128, serverEffectRange));
        syncedAuroraRange = Math.max(0, Math.min(128, serverAuroraRange));
    }
}
