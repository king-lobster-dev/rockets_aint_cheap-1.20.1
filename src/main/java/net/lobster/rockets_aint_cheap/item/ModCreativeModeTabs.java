package net.lobster.rockets_aint_cheap.item;

import net.lobster.rockets_aint_cheap.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "rockets_aint_cheap");

    public static final RegistryObject<CreativeModeTab> ROCKETS_AINT_CHEAP_TAB = CREATIVE_MODE_TABS.register("rockets_aint_cheap_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AEROSTEEL_PLATE.get()))
                    .title(Component.translatable("creativetab.rockets_aint_cheap_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AEROSTEEL_PLATE.get());
                        pOutput.accept(ModItems.AEROSTEEL_ROD.get());
                        pOutput.accept(ModItems.AEROSTEEL_INGOT.get());
                        pOutput.accept(ModItems.ENGINE_PROPELLER.get());
                        pOutput.accept(ModItems.TIER_1_ROCKET_PANEL.get());
                        pOutput.accept(ModItems.TIER_2_ROCKET_PANEL.get());
                        pOutput.accept(ModItems.TIER_3_ROCKET_PANEL.get());
                        pOutput.accept(ModItems.TIER_4_ROCKET_PANEL.get());

                        pOutput.accept(ModBlocks.AEROSTEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_FACTORY_BLOCK.get());
                        pOutput.accept(ModBlocks.ENCASED_AEROSTEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PLATEBLOCK.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PANEL.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PLATING.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PLATING_STAIRS.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PLATING_SLAB.get());
                        pOutput.accept(ModBlocks.AEROSTEEL_PILLAR.get());
                        pOutput.accept(ModBlocks.GLOWING_AEROSTEEL_PILLAR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}