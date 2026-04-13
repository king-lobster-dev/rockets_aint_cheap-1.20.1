package net.lobster.rockets_aint_cheap.world;

import com.mojang.logging.LogUtils;
import net.lobster.rockets_aint_cheap.config.AsteroidConfigLoader;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = "rockets_aint_cheap")
public class AsteroidEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Set<ChunkTask> QUEUE = new HashSet<>();

    private static final int DELAY_TICKS = 40;

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {

        if (!(event.getLevel() instanceof ServerLevel level)) return;

        String dim = level.dimension().location().toString();

        if (!AsteroidConfigLoader.LOADED_CONFIGS.containsKey(dim)) return;

        ChunkPos chunkPos = event.getChunk().getPos();
        String key = getChunkKey(dim, chunkPos);

        AsteroidSavedData data = AsteroidSavedData.get(level);

        // Persistent check
        if (data.isGenerated(key)) return;

        LOGGER.info("[Asteroids] Queuing chunk {}", key);

        QUEUE.add(new ChunkTask(level, chunkPos, DELAY_TICKS, key));
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        Set<ChunkTask> tasks = new HashSet<>(QUEUE);

        for (ChunkTask task : tasks) {
            task.ticks--;

            if (task.ticks <= 0) {

                LOGGER.info("[Asteroids] Generating asteroid in {}", task.key);

                AsteroidGenerator.generateChunkAsteroids(task.level, task.chunkPos);

                // Save permanently
                AsteroidSavedData data = AsteroidSavedData.get(task.level);
                data.markGenerated(task.key);

                QUEUE.remove(task);
            }
        }
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        var server = event.getServer();
        var level = server.overworld();

        AsteroidConfigLoader.load(server.getResourceManager(), level);
    }

    private static String getChunkKey(String dim, ChunkPos pos) {
        return dim + ":" + pos.x + "," + pos.z;
    }

    private static class ChunkTask {
        ServerLevel level;
        ChunkPos chunkPos;
        int ticks;
        String key;

        ChunkTask(ServerLevel level, ChunkPos chunkPos, int ticks, String key) {
            this.level = level;
            this.chunkPos = chunkPos;
            this.ticks = ticks;
            this.key = key;
        }
    }
}