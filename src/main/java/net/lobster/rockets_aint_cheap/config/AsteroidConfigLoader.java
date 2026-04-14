package net.lobster.rockets_aint_cheap.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AsteroidConfigLoader {

    public static final Map<String, AsteroidConfig> LOADED_CONFIGS = new HashMap<>();
    private static final Gson GSON = new Gson();
    private static final Logger LOGGER = LoggerFactory.getLogger("RocketsAintCheap/Asteroids");

    public static void load(ResourceManager resourceManager, ServerLevel level) {
        LOADED_CONFIGS.clear();

        try {
            for (ResourceLocation rl : resourceManager.listResources(
                    "asteroid_configs",
                    rl -> rl.getPath().endsWith(".json")
            ).keySet()) {

                Optional<Resource> optionalResource = resourceManager.getResource(rl);

                if (optionalResource.isEmpty()) {
                    LOGGER.warn("[Asteroids] Missing resource: {}", rl);
                    continue;
                }

                Resource resource = optionalResource.get();

                try (InputStreamReader reader = new InputStreamReader(resource.open())) {

                    JsonObject json = GSON.fromJson(reader, JsonObject.class);

                    if (json == null || !json.has("dimension")) {
                        LOGGER.warn("[Asteroids] Skipping invalid config file: {}", rl);
                        continue;
                    }

                    AsteroidConfig config = parseConfig(json, level);

                    String dimension = json.get("dimension").getAsString();
                    LOADED_CONFIGS.put(dimension, config);

                    LOGGER.info("[Asteroids] Loaded config for {}", dimension);

                } catch (Exception e) {
                    LOGGER.error("[Asteroids] Failed to parse config: {}", rl, e);
                }
            }

        } catch (Exception e) {
            LOGGER.error("[Asteroids] Failed to load asteroid configs", e);
        }
    }

    private static AsteroidConfig parseConfig(JsonObject json, ServerLevel level) {
        AsteroidConfig config = new AsteroidConfig();

        config.chunkSpawnChance = json.get("chunkSpawnChance").getAsFloat();
        config.minY = json.get("minY").getAsInt();
        config.maxY = json.get("maxY").getAsInt();
        config.safeRadius = json.get("safeRadius").getAsInt();

        var centerArr = json.getAsJsonArray("center");
        config.center = new net.minecraft.core.BlockPos(
                centerArr.get(0).getAsInt(),
                centerArr.get(1).getAsInt(),
                centerArr.get(2).getAsInt()
        );

        config.sizeWeights = GSON.fromJson(json.get("sizeWeights"), double[].class);
        config.sizeRanges = GSON.fromJson(json.get("sizeRanges"), int[][].class);

        config.minBlobCount = json.get("minBlobCount").getAsInt();
        config.maxBlobCount = json.get("maxBlobCount").getAsInt();

        config.blobSizeMin = json.get("blobSizeMin").getAsDouble();
        config.blobSizeMax = json.get("blobSizeMax").getAsDouble();

        config.blobOffsetMin = json.get("blobOffsetMin").getAsDouble();
        config.blobOffsetMax = json.get("blobOffsetMax").getAsDouble();

        config.asteroidTypes = json.getAsJsonArray("asteroidTypes")
                .asList()
                .stream()
                .map(e -> {
                    JsonObject obj = e.getAsJsonObject();
                    AsteroidConfig.AsteroidType type = new AsteroidConfig.AsteroidType();

                    type.outerShellBlock = getBlock(obj.get("outerShellBlock").getAsString(), level);
                    type.outerShellOre = getBlock(obj.get("outerShellOre").getAsString(), level);
                    type.outerShellSecondaryBlock = getBlock(obj.get("outerShellSecondaryBlock").getAsString(), level);

                    type.outerShellOreChance = obj.get("outerShellOreChance").getAsDouble();
                    type.outerShellSecondaryBlockChance = obj.get("outerShellSecondaryBlockChance").getAsDouble();

                    type.innerShellBlock = getBlock(obj.get("innerShellBlock").getAsString(), level);
                    type.innerShellOre = getBlock(obj.get("innerShellOre").getAsString(), level);
                    type.innerShellOreChance = obj.get("innerShellOreChance").getAsDouble();

                    type.coreOre = getBlock(obj.get("coreOre").getAsString(), level);
                    type.coreOreChance = obj.get("coreOreChance").getAsDouble();

                    type.spawnChance = obj.get("spawnChance").getAsDouble();

                    return type;
                })
                .toList();

        return config;
    }

    @SuppressWarnings("removal")
    private static Block getBlock(String name, ServerLevel level) {
        return level.registryAccess()
                .registryOrThrow(Registries.BLOCK)
                .get(new ResourceLocation(name));
    }
}