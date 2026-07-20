package com.wdcftgg.twilightdelight.common.registry;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.effect.FireRangePotion;
import com.wdcftgg.twilightdelight.common.effect.FrozenRangePotion;
import com.wdcftgg.twilightdelight.common.effect.AuroraGlowingPotion;
import com.wdcftgg.twilightdelight.common.effect.PoisonRangePotion;
import com.wdcftgg.twilightdelight.common.effect.TemporalSadnessPotion;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public final class TwilightDelightPotions {

    public static final Map<String, Potion> POTIONS = new LinkedHashMap<>();

    public static final Potion FIRE_RANGE = register("fire_range", new FireRangePotion());
    public static final Potion POISON_RANGE = register("poison_range", new PoisonRangePotion());
    public static final Potion FROZEN_RANGE = register("frozen_range", new FrozenRangePotion());
    public static final Potion TEMPORAL_SADNESS = register("temporal_sadness", new TemporalSadnessPotion());
    public static final Potion AURORA_GLOWING = register("aurora_glowing", new AuroraGlowingPotion());

    private TwilightDelightPotions() {
    }

    public static void registerAll(RegistryEvent.Register<Potion> event) {
        for (Potion potion : POTIONS.values()) {
            event.getRegistry().register(potion);
        }
    }

    private static Potion register(String path, Potion potion) {
        potion.setRegistryName(new ResourceLocation(TwilightDelightLegacy.MOD_ID, path));
        potion.setPotionName("effect." + TwilightDelightLegacy.MOD_ID + "." + path);
        POTIONS.put(path, potion);
        return potion;
    }
}
