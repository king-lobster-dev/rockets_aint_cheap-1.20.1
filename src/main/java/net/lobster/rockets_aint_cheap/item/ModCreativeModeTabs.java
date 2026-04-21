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

    @SuppressWarnings("unused")
    public static final RegistryObject<CreativeModeTab> ROCKETS_AINT_CHEAP_TAB = CREATIVE_MODE_TABS.register("rockets_aint_cheap_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ASTROSTEEL_PLATE.get()))
                    .title(Component.translatable("creativetab.rockets_aint_cheap_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ASTROSTEEL_PLATE.get());
                        pOutput.accept(ModItems.ASTROSTEEL_ROD.get());
                        pOutput.accept(ModItems.ASTROSTEEL_INGOT.get());
                        pOutput.accept(ModItems.ENGINE_PROPELLER.get());

                        pOutput.accept(ModBlocks.ASTROSTEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_FACTORY_BLOCK.get());
                        pOutput.accept(ModBlocks.ENCASED_ASTROSTEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATEBLOCK.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PANEL.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATING.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATING_STAIRS.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATING_SLAB.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PILLAR.get());
                        pOutput.accept(ModBlocks.GLOWING_ASTROSTEEL_PILLAR.get());

                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATING_BUTTON.get());
                        pOutput.accept(ModBlocks.ASTROSTEEL_PLATING_PRESSURE_PLATE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}