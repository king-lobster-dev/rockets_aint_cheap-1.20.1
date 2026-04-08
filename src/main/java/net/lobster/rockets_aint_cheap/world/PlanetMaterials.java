package net.lobster.rockets_aint_cheap.world;

import net.lobster.rockets_aint_cheap.config.PlanetConfig;
import net.lobster.rockets_aint_cheap.config.PlanetConfigs;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;

public class PlanetMaterials {

    public static Block getOuterShell(String planet, ServerLevel level) {
        PlanetConfig config = PlanetConfigs.PLANETS.get(planet);
        if (config == null) return null;
        return getBlockFromRegistry(config.outerShellBlock, level);
    }

    public static Block getInnerShell(String planet, ServerLevel level) {
        PlanetConfig config = PlanetConfigs.PLANETS.get(planet);
        if (config == null) return null;
        return getBlockFromRegistry(config.innerShellBlock, level);
    }

    public static Block getOuterOre(String planet, ServerLevel level) {
        PlanetConfig config = PlanetConfigs.PLANETS.get(planet);
        if (config == null) return null;
        return getBlockFromRegistry(config.outerShellOre, level);
    }

    public static Block getInnerOre(String planet, ServerLevel level) {
        PlanetConfig config = PlanetConfigs.PLANETS.get(planet);
        if (config == null) return null;
        return getBlockFromRegistry(config.innerShellOre, level);
    }

    private static Block getBlockFromRegistry(String name, ServerLevel level) {
        return level.registryAccess()
                .registryOrThrow(Registries.BLOCK)
                .get(new ResourceLocation(name)); // deprecated but works
    }
}