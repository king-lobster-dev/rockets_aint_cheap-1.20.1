package net.lobster.rockets_aint_cheap.config;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import java.util.List;

public class AsteroidConfig {

    public int asteroidCount;
    public int minAsteroidSize;
    public int maxAsteroidSize;

    public List<AsteroidType> asteroidTypes;

    public AsteroidType pickRandomType(RandomSource random) {
        double totalWeight = asteroidTypes.stream().mapToDouble(t -> t.spawnChance).sum();
        double r = random.nextDouble() * totalWeight;

        double cumulative = 0.0;
        for (AsteroidType type : asteroidTypes) {
            cumulative += type.spawnChance;
            if (r <= cumulative) return type;
        }

        return asteroidTypes.get(0);
    }

    public static class AsteroidType {
        public String name;

        public Block outerShellBlock;
        public Block outerShellOre;
        public double outerShellOreChance;

        public Block innerShellBlock;
        public Block innerShellOre;
        public double innerShellOreChance;

        public Block coreOre;
        public double coreOreChance;

        public double spawnChance;

        public AsteroidType(String name,
                            Block outerShellBlock, Block outerShellOre, double outerShellOreChance,
                            Block innerShellBlock, Block innerShellOre, double innerShellOreChance,
                            Block coreOre, double coreOreChance,
                            double spawnChance) {

            this.name = name;

            this.outerShellBlock = outerShellBlock;
            this.outerShellOre = outerShellOre;
            this.outerShellOreChance = outerShellOreChance;

            this.innerShellBlock = innerShellBlock;
            this.innerShellOre = innerShellOre;
            this.innerShellOreChance = innerShellOreChance;

            this.coreOre = coreOre;
            this.coreOreChance = coreOreChance;

            this.spawnChance = spawnChance;
        }
    }
}