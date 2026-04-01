package net.lobster.rockets_aint_cheap.item;

import net.lobster.rockets_aint_cheap.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "rockets_aint_cheap");

    public static final RegistryObject<CreativeModeTab> ROCKETS_AINT_CHEAP_TAB = CREATIVE_MODE_TABS.register("rockets_aint_cheap_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.NETHERITE_PLATE.get()))
                    .title(Component.translatable("creativetab.rockets_aint_cheap_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.NETHERITE_CHUNK.get());
                        pOutput.accept(ModItems.NETHERITE_PLATE.get());
                        pOutput.accept(ModItems.NETHERITE_ROD.get());
                        pOutput.accept(ModItems.NETHER_COMPASS.get());
                        pOutput.accept(ModBlocks.NETHERITE_FACTORY_BLOCK.get());
                        pOutput.accept(ModBlocks.ENCASED_NETHERITE_BLOCK.get());
                        pOutput.accept(ModBlocks.NETHERITE_PLATEBLOCK.get());
                        pOutput.accept(ModBlocks.NETHERITE_PANEL.get());
                        pOutput.accept(ModBlocks.NETHERITE_PLATING.get());
                        pOutput.accept(ModBlocks.NETHERITE_PLATING_STAIRS.get());
                        pOutput.accept(ModBlocks.NETHERITE_PLATING_SLAB.get());
                        pOutput.accept(ModBlocks.NETHERITE_PILLAR.get());
                        pOutput.accept(ModBlocks.GLOWING_NETHERITE_PILLAR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}