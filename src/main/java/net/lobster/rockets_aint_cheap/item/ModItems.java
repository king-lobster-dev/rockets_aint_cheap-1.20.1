package net.lobster.rockets_aint_cheap.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "rockets_aint_cheap");

    public static final RegistryObject<Item> NETHERITE_CHUNK = ITEMS.register("netherite_chunk",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_PLATE = ITEMS.register("netherite_plate",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_ROD = ITEMS.register("netherite_rod",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHER_COMPASS = ITEMS.register("nether_compass",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
