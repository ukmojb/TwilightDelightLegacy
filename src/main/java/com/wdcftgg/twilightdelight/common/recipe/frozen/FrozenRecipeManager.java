package com.wdcftgg.twilightdelight.common.recipe.frozen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class FrozenRecipeManager {

    private static final Logger LOGGER = LogManager.getLogger("Twilight Delight Frozen Recipes");
    private static final ResourceLocation DEFAULT_INDEX = new ResourceLocation("twilightdelight", "frozen_recipes/_index.json");
    private static final Map<ResourceLocation, FrozenRecipe> RECIPES = new LinkedHashMap<>();
    private static final Set<ResourceLocation> EXTRA_INDEXES = new LinkedHashSet<>();
    private static boolean initialized;

    private FrozenRecipeManager() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        RECIPES.clear();
        Set<ResourceLocation> indexes = new LinkedHashSet<>();
        indexes.add(DEFAULT_INDEX);
        for (ModContainer mod : Loader.instance().getActiveModList()) {
            indexes.add(new ResourceLocation(mod.getModId(), "frozen_recipes/_index.json"));
        }
        indexes.addAll(EXTRA_INDEXES);

        for (ResourceLocation index : indexes) {
            loadIndex(index);
        }
        initialized = true;
        LOGGER.info("Loaded {} frozen recipes", RECIPES.size());
    }

    public static synchronized void reload() {
        initialized = false;
        initialize();
    }

    public static synchronized void registerIndex(ResourceLocation index) {
        if (index == null) {
            throw new IllegalArgumentException("Frozen recipe index cannot be null");
        }
        EXTRA_INDEXES.add(index);
        if (initialized) {
            loadIndex(index);
        }
    }

    public static synchronized void register(FrozenRecipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Frozen recipe cannot be null");
        }
        RECIPES.put(recipe.getId(), recipe);
    }

    @Nullable
    public static synchronized FrozenRecipe findRecipe(ItemStack input) {
        initialize();
        for (FrozenRecipe recipe : RECIPES.values()) {
            if (recipe.matches(input)) {
                return recipe;
            }
        }
        return null;
    }

    public static ItemStack getFrozenResult(ItemStack input) {
        FrozenRecipe recipe = findRecipe(input);
        return recipe == null ? ItemStack.EMPTY : recipe.getResult();
    }

    public static synchronized List<FrozenRecipe> getRecipes() {
        initialize();
        return Collections.unmodifiableList(new ArrayList<>(RECIPES.values()));
    }

    private static void loadIndex(ResourceLocation index) {
        String assetPath = toAssetPath(index);
        try {
            Enumeration<URL> resources = FrozenRecipeManager.class.getClassLoader().getResources(assetPath);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                try (InputStream stream = resource.openStream();
                     Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    parseIndex(index, new JsonParser().parse(reader));
                } catch (RuntimeException | IOException exception) {
                    LOGGER.error("Failed to load frozen recipe index {} from {}", index, resource, exception);
                }
            }
        } catch (IOException exception) {
            LOGGER.error("Failed to discover frozen recipe index {}", index, exception);
        }
    }

    private static void parseIndex(ResourceLocation index, JsonElement json) {
        JsonArray entries = json.isJsonArray() ? json.getAsJsonArray() : json.getAsJsonObject().getAsJsonArray("recipes");
        if (entries == null) {
            throw new IllegalArgumentException("Frozen recipe index " + index + " has no 'recipes' array");
        }

        for (JsonElement entry : entries) {
            ResourceLocation recipeResource = resolveRecipeResource(index, entry.getAsString());
            loadRecipe(recipeResource);
        }
    }

    private static void loadRecipe(ResourceLocation recipeResource) {
        String assetPath = toAssetPath(recipeResource);
        try {
            Enumeration<URL> resources = FrozenRecipeManager.class.getClassLoader().getResources(assetPath);
            if (!resources.hasMoreElements()) {
                LOGGER.warn("Frozen recipe resource {} was listed but not found", recipeResource);
                return;
            }
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                try (InputStream stream = resource.openStream();
                     Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    register(parseRecipe(recipeResource, new JsonParser().parse(reader).getAsJsonObject()));
                } catch (SkippedRecipeException ignored) {
                    LOGGER.debug("Skipping frozen recipe {}: {}", recipeResource, ignored.getMessage());
                } catch (RuntimeException | IOException exception) {
                    LOGGER.error("Failed to load frozen recipe {} from {}", recipeResource, resource, exception);
                }
            }
        } catch (IOException exception) {
            LOGGER.error("Failed to discover frozen recipe {}", recipeResource, exception);
        }
    }

    private static FrozenRecipe parseRecipe(ResourceLocation resource, JsonObject json) {
        if (json.has("required_mod") && !Loader.isModLoaded(json.get("required_mod").getAsString())) {
            throw new SkippedRecipeException("Required mod is not loaded");
        }
        if ((!json.has("input") && !json.has("inputs")) || !json.has("result")) {
            throw new IllegalArgumentException("Frozen recipe must define 'input' and 'result'");
        }

        List<FrozenIngredient> inputs = new ArrayList<>();
        if (json.has("inputs")) {
            JsonElement inputsJson = json.get("inputs");
            if (!inputsJson.isJsonArray()) {
                throw new IllegalArgumentException("Frozen recipe 'inputs' must be an array");
            }
            for (JsonElement inputJson : inputsJson.getAsJsonArray()) {
                FrozenIngredient input = FrozenIngredient.tryFromJson(inputJson);
                if (input == null) {
                    LOGGER.debug("Skipping unavailable input {} in frozen recipe {}", inputJson, resource);
                } else {
                    inputs.add(input);
                }
            }
        } else {
            FrozenIngredient input = FrozenIngredient.tryFromJson(json.get("input"));
            if (input != null) {
                inputs.add(input);
            }
        }
        if (inputs.isEmpty()) {
            throw new SkippedRecipeException("No registered input items");
        }

        ItemStack result = parseResult(json.get("result"));
        String path = resource.getPath();
        int prefix = path.indexOf("frozen_recipes/");
        if (prefix >= 0) {
            path = path.substring(prefix + "frozen_recipes/".length());
        }
        if (path.endsWith(".json")) {
            path = path.substring(0, path.length() - ".json".length());
        }
        return new FrozenRecipe(new ResourceLocation(resource.getNamespace(), path), inputs, result);
    }

    private static ItemStack parseResult(JsonElement json) {
        String itemToken;
        int count = 1;
        int metadata = 0;
        if (json.isJsonPrimitive()) {
            itemToken = json.getAsString();
        } else {
            JsonObject object = json.getAsJsonObject();
            itemToken = object.get("item").getAsString();
            count = object.has("count") ? object.get("count").getAsInt() : 1;
            metadata = object.has("meta") ? object.get("meta").getAsInt() : 0;
        }

        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemToken));
        if (item == null) {
            throw new IllegalArgumentException("Unknown frozen recipe result item '" + itemToken + "'");
        }
        if (count < 1 || count > item.getItemStackLimit()) {
            throw new IllegalArgumentException("Invalid frozen recipe result count " + count);
        }
        return new ItemStack(item, count, metadata);
    }

    private static ResourceLocation resolveRecipeResource(ResourceLocation index, String entry) {
        String path = entry.trim();
        if (path.indexOf(':') >= 0) {
            ResourceLocation explicit = new ResourceLocation(path);
            return ensureJsonExtension(explicit);
        }

        String indexPath = index.getPath();
        int separator = indexPath.lastIndexOf('/');
        String parent = separator < 0 ? "" : indexPath.substring(0, separator + 1);
        return ensureJsonExtension(new ResourceLocation(index.getNamespace(), parent + path));
    }

    private static ResourceLocation ensureJsonExtension(ResourceLocation resource) {
        return resource.getPath().endsWith(".json")
                ? resource
                : new ResourceLocation(resource.getNamespace(), resource.getPath() + ".json");
    }

    private static String toAssetPath(ResourceLocation resource) {
        return "assets/" + resource.getNamespace() + "/" + resource.getPath();
    }

    private static final class SkippedRecipeException extends IllegalArgumentException {
        private SkippedRecipeException(String message) {
            super(message);
        }
    }
}
