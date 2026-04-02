package net.lobster.rockets_aint_cheap.blocks;

import java.util.function.Supplier;
import net.lobster.rockets_aint_cheap.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "rockets_aint_cheap");

    public static final RegistryObject<Block> ASTROSTEEL_BLOCK = registerBlock("astrosteel_block",
            () -> new Block(astrosteelProperties()));
    public static final RegistryObject<Block> ASTROSTEEL_FACTORY_BLOCK = registerBlock("astrosteel_factory_block",
            () -> new Block(astrosteelProperties()));
    public static final RegistryObject<Block> ENCASED_ASTROSTEEL_BLOCK = registerBlock("encased_astrosteel_block",
            () -> new Block(astrosteelProperties()));
    public static final RegistryObject<Block> ASTROSTEEL_PLATEBLOCK = registerBlock("astrosteel_plateblock",
            () -> new Block(astrosteelProperties()));
    public static final RegistryObject<Block> ASTROSTEEL_PANEL = registerBlock("astrosteel_panel",
            () -> new Block(astrosteelProperties()));
    public static final RegistryObject<Block> ASTROSTEEL_PLATING = registerBlock("astrosteel_plating",
            () -> new Block(astrosteelProperties()));

    public static final RegistryObject<Block> ASTROSTEEL_PILLAR = registerBlock("astrosteel_pillar",
            () -> new RotatedPillarBlock(astrosteelProperties()));
    public static final RegistryObject<Block> GLOWING_ASTROSTEEL_PILLAR = registerBlock("glowing_astrosteel_pillar",
            () -> new RotatedPillarBlock(astrosteelProperties().lightLevel((state) -> 15)));

    public static final RegistryObject<Block> ASTROSTEEL_PLATING_STAIRS = registerBlock("astrosteel_plating_stairs",
            () -> new StairBlock(() -> ASTROSTEEL_PLATING.get().defaultBlockState(), astrosteelProperties()));
    public static final RegistryObject<Block> ASTROSTEEL_PLATING_SLAB = registerBlock("astrosteel_plating_slab",
            () -> new SlabBlock(astrosteelProperties()));

    public static final RegistryObject<Block> ASTROSTEEL_PLATING_BUTTON = registerBlock("astrosteel_plating_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.STONE).explosionResistance(1200), BlockSetType.IRON, 10, false));
    public static final RegistryObject<Block> ASTROSTEEL_PLATING_PRESSURE_PLATE = registerBlock("astrosteel_plating_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE).sound(SoundType.STONE).explosionResistance(1200), BlockSetType.IRON));

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

    private static BlockBehaviour.Properties astrosteelProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
                .sound(SoundType.COPPER);
    }
}
