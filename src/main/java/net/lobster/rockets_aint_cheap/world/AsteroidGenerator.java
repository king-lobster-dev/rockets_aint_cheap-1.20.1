package net.lobster.rockets_aint_cheap.world;

import net.lobster.rockets_aint_cheap.config.AsteroidConfig;
import net.lobster.rockets_aint_cheap.config.AsteroidConfigLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;

public class AsteroidGenerator {

    public static void generateChunkAsteroids(ServerLevel level, ChunkPos chunkPos) {

        String dim = level.dimension().location().toString();
        AsteroidConfig config = AsteroidConfigLoader.LOADED_CONFIGS.get(dim);
        if (config == null) return;

        RandomSource random = level.getRandom();

        // Spawn chance
        if (random.nextFloat() > config.chunkSpawnChance) return;

        int x = chunkPos.getMinBlockX() + random.nextInt(16);
        int z = chunkPos.getMinBlockZ() + random.nextInt(16);
        int y = config.minY + random.nextInt(config.maxY - config.minY + 1);

        BlockPos center = new BlockPos(x, y, z);

        // Safe zone
        if (center.closerThan(config.center, config.safeRadius)) return;

        AsteroidConfig.AsteroidType type = config.pickRandomType(random);

        generateSingleAsteroid(level, center, random, config, type);
    }

    private static void generateSingleAsteroid(ServerLevel level,
                                               BlockPos center,
                                               RandomSource random,
                                               AsteroidConfig config,
                                               AsteroidConfig.AsteroidType type) {

        int radius = config.getWeightedRadius(random);

        int blobCount = config.minBlobCount +
                random.nextInt(config.maxBlobCount - config.minBlobCount + 1);

        BlockPos[] blobCenters = new BlockPos[blobCount];
        double[] blobRadii = new double[blobCount];

        // Generate blobs
        for (int i = 0; i < blobCount; i++) {

            int offsetX = (int)((random.nextDouble() *
                    (config.blobOffsetMax - config.blobOffsetMin) + config.blobOffsetMin) * radius);

            int offsetY = (int)((random.nextDouble() *
                    (config.blobOffsetMax - config.blobOffsetMin) + config.blobOffsetMin) * radius);

            int offsetZ = (int)((random.nextDouble() *
                    (config.blobOffsetMax - config.blobOffsetMin) + config.blobOffsetMin) * radius);

            blobCenters[i] = center.offset(offsetX, offsetY, offsetZ);

            blobRadii[i] = radius * (
                    config.blobSizeMin +
                            random.nextDouble() * (config.blobSizeMax - config.blobSizeMin)
            );
        }

        int maxBlobRadius = (int)(radius * config.blobSizeMax);
        int maxOffset = (int)(radius * Math.max(Math.abs(config.blobOffsetMin), Math.abs(config.blobOffsetMax)));
        int bound = maxBlobRadius + maxOffset;

        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-bound, -bound, -bound),
                center.offset(bound, bound, bound))) {

            boolean inside = false;
            double minRatio = Double.MAX_VALUE;

            for (int i = 0; i < blobCount; i++) {

                double dx = pos.getX() - blobCenters[i].getX();
                double dy = pos.getY() - blobCenters[i].getY();
                double dz = pos.getZ() - blobCenters[i].getZ();

                double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

                if (dist < blobRadii[i]) {
                    inside = true;

                    double ratio = dist / blobRadii[i];
                    if (ratio < minRatio) minRatio = ratio;
                }
            }

            if (!inside) continue;

            double ratio = minRatio;
            BlockState block;

            // Outer
            if (ratio > 0.6) {

                double roll = random.nextDouble();

                if (roll < type.outerShellOreChance) {
                    block = type.outerShellOre.defaultBlockState();
                }
                else if (roll < type.outerShellOreChance + type.outerShellSecondaryBlockChance) {
                    block = type.outerShellSecondaryBlock.defaultBlockState();
                }
                else {
                    block = type.outerShellBlock.defaultBlockState();
                }
            }
            else {
                block = random.nextDouble() < type.innerShellOreChance
                        ? type.innerShellOre.defaultBlockState()
                        : type.innerShellBlock.defaultBlockState();
            }

            // Core
            if (ratio < 0.25 && random.nextDouble() < type.coreOreChance) {
                block = type.coreOre.defaultBlockState();
            }

            level.setBlock(pos, block, 2);
        }
    }
}