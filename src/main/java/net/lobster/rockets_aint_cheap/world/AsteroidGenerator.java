package net.lobster.rockets_aint_cheap.world;

import net.lobster.rockets_aint_cheap.config.AsteroidConfig;
import net.lobster.rockets_aint_cheap.config.OrbitConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;

public class AsteroidGenerator {

    private static final int SAFE_RADIUS = 20;
    private static final BlockPos CENTER = new BlockPos(0, 120, 0);

    private static final int MIN_Y = -100;
    private static final int MAX_Y = 200;

    //Called for EACH chunk load
    public static void generateChunkAsteroids(ServerLevel level, ChunkPos chunkPos) {
        String dim = level.dimension().location().toString();
        AsteroidConfig config = OrbitConfig.ASTEROID_CONFIGS.get(dim);
        if (config == null) return;

        RandomSource random = RandomSource.create(
                chunkPos.x * 341873128712L + chunkPos.z * 132897987541L
        );

        // Chance per chunk (controls density)
        if (random.nextFloat() > 0.15f) return;

        int x = chunkPos.getMinBlockX() + random.nextInt(16);
        int z = chunkPos.getMinBlockZ() + random.nextInt(16);
        int y = MIN_Y + random.nextInt(MAX_Y - MIN_Y + 1);

        BlockPos center = new BlockPos(x, y, z);

        // Safe zone around origin
        if (center.closerThan(CENTER, SAFE_RADIUS)) return;

        AsteroidConfig.AsteroidType type = config.pickRandomType(random);

        generateSingleAsteroid(level, center, random, config, type);
    }

    private static void generateSingleAsteroid(ServerLevel level,
                                               BlockPos center,
                                               RandomSource random,
                                               AsteroidConfig config,
                                               AsteroidConfig.AsteroidType type) {

        int radius = config.minAsteroidSize +
                random.nextInt(config.maxAsteroidSize - config.minAsteroidSize + 1);

        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-radius, -radius, -radius),
                center.offset(radius, radius, radius))) {

            double dist = pos.distSqr(center);
            if (dist > radius * radius) continue;

            double ratio = Math.sqrt(dist) / radius;

            BlockState block;

            // Outer shell
            if (ratio > 0.6) {
                block = random.nextDouble() < type.outerShellOreChance
                        ? type.outerShellOre.defaultBlockState()
                        : type.outerShellBlock.defaultBlockState();
            }
            // Inner shell
            else {
                block = random.nextDouble() < type.innerShellOreChance
                        ? type.innerShellOre.defaultBlockState()
                        : type.innerShellBlock.defaultBlockState();
            }

            // Rich core
            if (ratio < 0.25 && random.nextDouble() < type.coreOreChance) {
                block = type.coreOre.defaultBlockState();
            }

            level.setBlock(pos, block, 2);
        }
    }
}