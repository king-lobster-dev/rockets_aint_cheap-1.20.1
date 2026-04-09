package net.lobster.rockets_aint_cheap.config;

import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbitConfig {

    public static final Map<String, AsteroidConfig> ASTEROID_CONFIGS = new HashMap<>();

    static {


        // EARTH ORBIT
        AsteroidConfig earth = new AsteroidConfig();
        earth.asteroidCount = 800;
        earth.minAsteroidSize = 4;
        earth.maxAsteroidSize = 14;

        earth.asteroidTypes = List.of(

                new AsteroidConfig.AsteroidType(
                        "iron",
                        Blocks.STONE, Blocks.IRON_ORE, 0.1,
                        Blocks.DEEPSLATE, Blocks.DEEPSLATE_IRON_ORE, 0.25,
                        Blocks.IRON_BLOCK, 0.05,
                        0.4
                ),

                new AsteroidConfig.AsteroidType(
                        "copper",
                        Blocks.STONE, Blocks.COPPER_ORE, 0.1,
                        Blocks.DEEPSLATE, Blocks.COPPER_ORE, 0.25,
                        Blocks.COPPER_BLOCK, 0.05,
                        0.3
                ),

                new AsteroidConfig.AsteroidType(
                        "gold",
                        Blocks.STONE, Blocks.GOLD_ORE, 0.1,
                        Blocks.DEEPSLATE, Blocks.DEEPSLATE_GOLD_ORE, 0.25,
                        Blocks.GOLD_BLOCK, 0.05,
                        0.2
                ),

                new AsteroidConfig.AsteroidType(
                        "coal_diamond",
                        Blocks.STONE, Blocks.COAL_ORE, 0.15,
                        Blocks.DEEPSLATE, Blocks.DEEPSLATE_DIAMOND_ORE, 0.3,
                        Blocks.DIAMOND_BLOCK, 0.03,
                        0.1
                )
        );

        ASTEROID_CONFIGS.put("ad_astra:earth_orbit", earth);


        // MARS ORBIT
        AsteroidConfig mars = new AsteroidConfig();
        mars.asteroidCount = 400;
        mars.minAsteroidSize = 6;
        mars.maxAsteroidSize = 16;

        mars.asteroidTypes = List.of(

                new AsteroidConfig.AsteroidType(
                        "mars_iron",
                        Blocks.RED_SANDSTONE, Blocks.IRON_ORE, 0.15,
                        Blocks.DEEPSLATE, Blocks.DEEPSLATE_IRON_ORE, 0.3,
                        Blocks.IRON_BLOCK, 0.05,
                        0.5
                ),

                new AsteroidConfig.AsteroidType(
                        "mars_gold",
                        Blocks.RED_SANDSTONE, Blocks.GOLD_ORE, 0.15,
                        Blocks.DEEPSLATE, Blocks.DEEPSLATE_GOLD_ORE, 0.3,
                        Blocks.GOLD_BLOCK, 0.05,
                        0.3
                ),

                new AsteroidConfig.AsteroidType(
                        "mars_copper",
                        Blocks.RED_SANDSTONE, Blocks.COPPER_ORE, 0.15,
                        Blocks.DEEPSLATE, Blocks.COPPER_ORE, 0.3,
                        Blocks.COPPER_BLOCK, 0.05,
                        0.2
                )
        );

        ASTEROID_CONFIGS.put("ad_astra:mars_orbit", mars);
    }
}