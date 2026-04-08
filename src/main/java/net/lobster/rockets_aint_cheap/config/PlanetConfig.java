package net.lobster.rockets_aint_cheap.config;

import java.util.Map;

public class PlanetConfig {

    // Number and size of asteroids
    public int asteroidCount;
    public int minAsteroidSize;
    public int maxAsteroidSize;

    // Outer shell material and ore chance
    public String outerShellBlock;
    public String outerShellOre;
    public double outerShellOreChance; // 0.0 - 1.0

    // Inner shell material and ore chance
    public String innerShellBlock;
    public String innerShellOre;
    public double innerShellOreChance; // 0.0 - 1.0

    // Chance that asteroid is a rich core type
    public double richCoreChance;

    // Percentage of different asteroid types (name -> chance)
    public Map<String, Double> asteroidTypePercentages;
}