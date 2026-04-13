package net.lobster.rockets_aint_cheap.config;

import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class OrbitConfig {

    public static final Map<String, AsteroidConfig> ASTEROID_CONFIGS = new HashMap<>();

    static {

        // ===== EARTH ORBIT =====
        AsteroidConfig earth = new AsteroidConfig();

        earth.chunkSpawnChance = 0.07f;
        earth.minY = -40;
        earth.maxY = 200;

        earth.safeRadius = 32;
        earth.center = new BlockPos(0, 120, 0);

        // Size distribution
        earth.sizeWeights = new double[]{0.6, 0.2, 0.1, 0.1};
        earth.sizeRanges = new int[][]{
                {2, 3},
                {4, 5},
                {6, 8},
                {9, 14}
        };

        // Blob behavior
        earth.minBlobCount = 5;
        earth.maxBlobCount = 7;

        earth.blobSizeMin = 0.5;
        earth.blobSizeMax = 1.0;

        earth.blobOffsetMin = -0.6;
        earth.blobOffsetMax = 0.6;

        // TODO: add asteroidTypes here
        // earth.asteroidTypes = List.of(...);

        ASTEROID_CONFIGS.put("ad_astra:earth_orbit", earth);

        // ===== ADD MORE ORBITS HERE =====
    }
}