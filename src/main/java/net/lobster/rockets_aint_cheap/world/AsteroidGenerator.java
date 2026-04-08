package net.lobster.rockets_aint_cheap.world;

import net.lobster.rockets_aint_cheap.config.PlanetConfig;
import net.lobster.rockets_aint_cheap.config.PlanetConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AsteroidGenerator {

    private static final int STATION_SAFE_RADIUS = 80;
    private static final int MIN_Y = -60;
    private static final int MAX_Y = 200;
    private static final int RING_RADIUS = 1000;
    private static final int RING_WIDTH = 400;

    public static void generateAsteroids(ServerLevel level, BlockPos stationPos) {
        String dim = level.dimension().location().getPath();
        PlanetConfig config = PlanetConfigs.PLANETS.get(dim);
        if (config == null) return;

        RandomSource random = level.getRandom();

        // Clear old asteroids
        clearOldAsteroids(level, stationPos, config);

        for (int i = 0; i < config.asteroidCount; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = RING_RADIUS + (random.nextDouble() - 0.5) * RING_WIDTH;

            int x = (int) (Math.cos(angle) * radius);
            int z = (int) (Math.sin(angle) * radius);
            int y = MIN_Y + random.nextInt(MAX_Y - MIN_Y + 1);

            BlockPos center = new BlockPos(x, y, z);
            if (center.closerThan(stationPos, STATION_SAFE_RADIUS)) continue;

            generateSingleAsteroid(level, center, random, config);
        }
    }

    private static void generateSingleAsteroid(ServerLevel level, BlockPos center, RandomSource random, PlanetConfig config) {
        int radius = config.minAsteroidSize + random.nextInt(config.maxAsteroidSize - config.minAsteroidSize + 1);

        String dim = level.dimension().location().getPath(); // get dimension path from ServerLevel
        BlockState outerBase = PlanetMaterials.getOuterShell(dim, level).defaultBlockState();
        BlockState innerBase = PlanetMaterials.getInnerShell(dim, level).defaultBlockState();
        BlockState outerOre = PlanetMaterials.getOuterOre(dim, level).defaultBlockState();
        BlockState innerOre = PlanetMaterials.getInnerOre(dim, level).defaultBlockState();

        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radius, -radius, -radius),
                center.offset(radius, radius, radius))) {
            double dist = pos.distSqr(center);
            if (dist > radius * radius) continue;

            double ratio = Math.sqrt(dist) / radius;
            if (ratio > 0.6) {
                if (random.nextDouble() < config.outerShellOreChance) {
                    level.setBlock(pos, outerOre, 2);
                } else {
                    level.setBlock(pos, outerBase, 2);
                }
            } else {
                if (random.nextDouble() < config.innerShellOreChance) {
                    level.setBlock(pos, innerOre, 2);
                } else {
                    level.setBlock(pos, innerBase, 2);
                }
            }
        }
    }

    private static void clearOldAsteroids(ServerLevel level, BlockPos stationPos, PlanetConfig config) {
        // clear all old asteroid blocks outside station-safe radius
        // (implementation similar to previous generator)
    }
}