package com.wdcftgg.twilightdelight.common.recipe.frozen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class FrozenRecipeCatalysts {

    private static final Set<String> CATALYST_TOKENS = new LinkedHashSet<>();

    static {
        CATALYST_TOKENS.add("twilightforest:ice_bomb");
        CATALYST_TOKENS.add("twilightdelight:glacier_ice_tea");
        CATALYST_TOKENS.add("twilightdelight:glacier_cake");
        CATALYST_TOKENS.add("twilightdelight:glacier_ice_cream");
        CATALYST_TOKENS.add("twilightdelight:glacier_milkshake");
    }

    private FrozenRecipeCatalysts() {
    }

    public static synchronized void register(String itemOrOreToken) {
        if (itemOrOreToken == null || itemOrOreToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Frozen recipe catalyst token cannot be empty");
        }
        CATALYST_TOKENS.add(itemOrOreToken.trim());
    }

    public static synchronized List<String> getTokens() {
        return Collections.unmodifiableList(new ArrayList<>(CATALYST_TOKENS));
    }
}
