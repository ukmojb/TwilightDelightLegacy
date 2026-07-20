package com.wdcftgg.twilightdelight.core;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

public final class TwilightDelightLateMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.twilightdelight.farmersdelight.json");
    }
}
