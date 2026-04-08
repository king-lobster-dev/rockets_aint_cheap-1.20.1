package net.lobster.rockets_aint_cheap.world;

public class AsteroidType {

    public String name;

    // Blocks used for this asteroid type
    public String outerShellMaterial;
    public String outerShellOre;
    public double outerShellOreChance; // 0.0 - 1.0

    public String innerShellMaterial;
    public String innerShellOre;
    public double innerShellOreChance; // 0.0 - 1.0

    public String coreOre;  // rich core material
    public double coreOreChance; // chance for a block in the inner core

    // Chance to spawn this asteroid type (relative to others)
    public double spawnChance;

    public AsteroidType(String name, String outerShellMaterial, String outerShellOre, double outerShellOreChance,
                        String innerShellMaterial, String innerShellOre, double innerShellOreChance,
                        String coreOre, double coreOreChance, double spawnChance) {
        this.name = name;
        this.outerShellMaterial = outerShellMaterial;
        this.outerShellOre = outerShellOre;
        this.outerShellOreChance = outerShellOreChance;
        this.innerShellMaterial = innerShellMaterial;
        this.innerShellOre = innerShellOre;
        this.innerShellOreChance = innerShellOreChance;
        this.coreOre = coreOre;
        this.coreOreChance = coreOreChance;
        this.spawnChance = spawnChance;
    }
}