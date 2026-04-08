package net.lobster.rockets_aint_cheap.config;

import java.util.HashMap;
import java.util.Map;

public class PlanetConfigs {

    public static final Map<String, PlanetConfig> PLANETS = new HashMap<>();

    static {
        // Earth
        PlanetConfig earth = new PlanetConfig();
        earth.asteroidCount = 30;
        earth.minAsteroidSize = 8;
        earth.maxAsteroidSize = 20;
        earth.outerShellBlock = "minecraft:stone";
        earth.outerShellOre = "minecraft:iron_ore";
        earth.outerShellOreChance = 0.1;
        earth.innerShellBlock = "minecraft:deepslate";
        earth.innerShellOre = "minecraft:coal_ore";
        earth.innerShellOreChance = 0.25;
        earth.richCoreChance = 0.05;
        earth.asteroidTypePercentages = Map.of(
                "copper", 0.3,
                "iron", 0.4,
                "gold", 0.2,
                "coal_diamond", 0.05,
                "desh", 0.05
        );
        PLANETS.put("earth_orbit", earth);

        // Mars
        PlanetConfig mars = new PlanetConfig();
        mars.asteroidCount = 20;
        mars.minAsteroidSize = 6;
        mars.maxAsteroidSize = 15;
        mars.outerShellBlock = "ad_astra:mars_stone";
        mars.outerShellOre = "ad_astra:iron_ore";
        mars.outerShellOreChance = 0.1;
        mars.innerShellBlock = "minecraft:deepslate";
        mars.innerShellOre = "ad_astra:copper_ore";
        mars.innerShellOreChance = 0.3;
        mars.richCoreChance = 0.05;
        mars.asteroidTypePercentages = Map.of(
                "iron", 0.5,
                "copper", 0.3,
                "gold", 0.2
        );
        PLANETS.put("mars_orbit", mars);

        // Repeat for Moon, Mercury, Venus, Glacio...
    }
}