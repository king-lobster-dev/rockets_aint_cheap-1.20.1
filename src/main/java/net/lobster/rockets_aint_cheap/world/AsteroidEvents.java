package net.lobster.rockets_aint_cheap.world;

import com.mojang.logging.LogUtils;
import net.lobster.rockets_aint_cheap.config.OrbitConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = "rockets_aint_cheap")
public class AsteroidEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {

        // Debug: event fired
        LOGGER.info("[Asteroids] ChunkEvent.Load fired");

        if (!(event.getLevel() instanceof ServerLevel level)) {
            LOGGER.info("[Asteroids] Not a ServerLevel, skipping");
            return;
        }

        String dim = level.dimension().location().toString();

        LOGGER.info("[Asteroids] Dimension: {}", dim);

        // Only run in orbit dimensions
        if (!OrbitConfig.ASTEROID_CONFIGS.containsKey(dim)) {
            LOGGER.info("[Asteroids] Not an orbit dimension, skipping");
            return;
        }

        ChunkPos chunkPos = event.getChunk().getPos();

        LOGGER.info("[Asteroids] Generating in chunk: {}", chunkPos);

        AsteroidGenerator.generateChunkAsteroids(level, chunkPos);
    }
}