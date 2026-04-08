package net.lobster.rockets_aint_cheap.world;

import net.lobster.rockets_aint_cheap.config.PlanetConfigs;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

@Mod.EventBusSubscriber
public class AsteroidEvents {

    // You can set the default space station position for simplicity.
    // In practice, you might store/load the actual station coordinates per dimension.
    private static final BlockPos DEFAULT_STATION_POS = new BlockPos(0, 150, 0);

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        String dim = level.dimension().location().getPath();

        // Only trigger for configured orbit dimensions
        if (!PlanetConfigs.PLANETS.containsKey(dim)) return;

        // Generate asteroids for this dimension
        AsteroidGenerator.generateAsteroids(level, DEFAULT_STATION_POS);
    }
}