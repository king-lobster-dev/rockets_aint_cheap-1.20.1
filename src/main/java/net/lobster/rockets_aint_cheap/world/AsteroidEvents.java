package net.lobster.rockets_aint_cheap.world;

import com.mojang.logging.LogUtils;
import net.lobster.rockets_aint_cheap.config.OrbitConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = "rockets_aint_cheap")
public class AsteroidEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    // Queue of chunks waiting for delayed generation
    private static final Set<ChunkTask> QUEUE = new HashSet<>();

    // Delay before generating (ticks)
    private static final int DELAY_TICKS = 40; // ~2 seconds

    // Fires when a chunk loads
    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {

        if (!(event.getLevel() instanceof ServerLevel level)) return;

        String dim = level.dimension().location().toString();

        LOGGER.info("[Asteroids] ChunkEvent.Load fired in {}", dim);

        // Only run in configured orbit dimensions
        if (!OrbitConfig.ASTEROID_CONFIGS.containsKey(dim)) return;

        ChunkPos chunkPos = event.getChunk().getPos();

        LOGGER.info("[Asteroids] Queuing chunk {}", chunkPos);

        // Add to queue with delay
        QUEUE.add(new ChunkTask(level, chunkPos, DELAY_TICKS));
    }

    // Processes queued asteroid generation safely
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        // Iterate over COPY to avoid ConcurrentModificationException
        Set<ChunkTask> tasks = new HashSet<>(QUEUE);

        for (ChunkTask task : tasks) {
            task.ticks--;

            if (task.ticks <= 0) {
                LOGGER.info("[Asteroids] Generating asteroid in {}", task.chunkPos);

                AsteroidGenerator.generateChunkAsteroids(task.level, task.chunkPos);

                QUEUE.remove(task); // safe because we're iterating copy
            }
        }
    }

    // Internal task for delayed generation
    private static class ChunkTask {
        ServerLevel level;
        ChunkPos chunkPos;
        int ticks;

        ChunkTask(ServerLevel level, ChunkPos chunkPos, int ticks) {
            this.level = level;
            this.chunkPos = chunkPos;
            this.ticks = ticks;
        }
    }
}