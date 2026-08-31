package com.wdcftgg.twilightdelight.core;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

public final class TwilightDelightLateMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Arrays.asList(
                "mixins.twilightdelight.twilightforest.json",
                "mixins.twilightdelight.farmersdelight.json"
        );
    }
}
