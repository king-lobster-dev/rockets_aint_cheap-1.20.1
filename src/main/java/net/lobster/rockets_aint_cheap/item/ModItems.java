package net.lobster.rockets_aint_cheap.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "rockets_aint_cheap");

    public static final RegistryObject<Item> ASTROSTEEL_PLATE = ITEMS.register("astrosteel_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTROSTEEL_ROD = ITEMS.register("astrosteel_rod",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTROSTEEL_INGOT = ITEMS.register("astrosteel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENGINE_PROPELLER = ITEMS.register("engine_propeller",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
