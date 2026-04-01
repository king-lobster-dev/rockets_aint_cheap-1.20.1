package net.lobster.rockets_aint_cheap.blocks;

import java.util.function.Supplier;
import net.lobster.rockets_aint_cheap.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "rockets_aint_cheap");

    public static final RegistryObject<Block> NETHERITE_FACTORY_BLOCK = registerBlock("netherite_factory_block",
            () -> new Block(netheriteProperties()));

    public static final RegistryObject<Block> ENCASED_NETHERITE_BLOCK = registerBlock("encased_netherite_block",
            () -> new Block(netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PLATEBLOCK = registerBlock("netherite_plateblock",
            () -> new Block(netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PANEL = registerBlock("netherite_panel",
            () -> new Block(netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PLATING = registerBlock("netherite_plating",
            () -> new Block(netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PLATING_STAIRS = registerBlock("netherite_plating_stairs",
            () -> new StairBlock(() -> NETHERITE_PLATING.get().defaultBlockState(), netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PLATING_SLAB = registerBlock("netherite_plating_slab",
            () -> new SlabBlock(netheriteProperties()));

    public static final RegistryObject<Block> NETHERITE_PILLAR = registerBlock("netherite_pillar",
            () -> new RotatedPillarBlock(netheriteProperties()));

    public static final RegistryObject<Block> GLOWING_NETHERITE_PILLAR = registerBlock("glowing_netherite_pillar",
            () -> new RotatedPillarBlock(netheriteProperties().lightLevel((state) -> 15)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static BlockBehaviour.Properties netheriteProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
                .sound(SoundType.COPPER);
    }
}
