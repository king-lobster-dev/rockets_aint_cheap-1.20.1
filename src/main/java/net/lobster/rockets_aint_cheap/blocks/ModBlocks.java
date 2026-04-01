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

    public static final RegistryObject<Block> AEROSTEEL_BLOCK = registerBlock("aerosteel_block",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_FACTORY_BLOCK = registerBlock("aerosteel_factory_block",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> ENCASED_AEROSTEEL_BLOCK = registerBlock("encased_aerosteel_block",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PLATEBLOCK = registerBlock("aerosteel_plateblock",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PANEL = registerBlock("aerosteel_panel",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PLATING = registerBlock("aerosteel_plating",
            () -> new Block(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PLATING_STAIRS = registerBlock("aerosteel_plating_stairs",
            () -> new StairBlock(() -> AEROSTEEL_PLATING.get().defaultBlockState(), aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PLATING_SLAB = registerBlock("aerosteel_plating_slab",
            () -> new SlabBlock(aerosteelProperties()));

    public static final RegistryObject<Block> AEROSTEEL_PILLAR = registerBlock("aerosteel_pillar",
            () -> new RotatedPillarBlock(aerosteelProperties()));

    public static final RegistryObject<Block> GLOWING_AEROSTEEL_PILLAR = registerBlock("glowing_aerosteel_pillar",
            () -> new RotatedPillarBlock(aerosteelProperties().lightLevel((state) -> 15)));

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

    private static BlockBehaviour.Properties aerosteelProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
                .sound(SoundType.COPPER);
    }
}
