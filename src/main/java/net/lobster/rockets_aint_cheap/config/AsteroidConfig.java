package net.lobster.rockets_aint_cheap.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class AsteroidConfig {

    // ===== SPAWNING =====
    public float chunkSpawnChance;
    public int minY;
    public int maxY;

    public int safeRadius;
    public BlockPos center;

    // ===== SIZE DISTRIBUTION =====
    public double[] sizeWeights;   // must sum ~1.0
    public int[][] sizeRanges;     // { {min,max}, ... }

    // ===== BLOB SHAPE =====
    public int minBlobCount;
    public int maxBlobCount;

    public double blobSizeMin;
    public double blobSizeMax;

    public double blobOffsetMin;
    public double blobOffsetMax;

    // ===== ASTEROID TYPES =====
    public List<AsteroidType> asteroidTypes;

    // ===== TYPE PICKING =====
    public AsteroidType pickRandomType(RandomSource random) {
        double totalWeight = asteroidTypes.stream().mapToDouble(t -> t.spawnChance).sum();
        double r = random.nextDouble() * totalWeight;

        double cumulative = 0.0;
        for (AsteroidType type : asteroidTypes) {
            cumulative += type.spawnChance;
            if (r <= cumulative) return type;
        }

        return asteroidTypes.get(0);
    }

    // ===== SIZE PICKING =====
    public int getWeightedRadius(RandomSource random) {
        double roll = random.nextDouble();
        double cumulative = 0;

        for (int i = 0; i < sizeWeights.length; i++) {
            cumulative += sizeWeights[i];
            if (roll <= cumulative) {
                int min = sizeRanges[i][0];
                int max = sizeRanges[i][1];
                return min + random.nextInt(max - min + 1);
            }
        }

        return sizeRanges[0][0];
    }

    // ===== INNER CLASS =====
    public static class AsteroidType {
        public Block outerShellBlock;
        public Block outerShellOre;
        public Block outerShellSecondaryBlock;

        public double outerShellOreChance;
        public double outerShellSecondaryBlockChance;

        public Block innerShellBlock;
        public Block innerShellOre;

        public double innerShellOreChance;

        public Block coreOre;
        public double coreOreChance;

        public double spawnChance;
    }
}