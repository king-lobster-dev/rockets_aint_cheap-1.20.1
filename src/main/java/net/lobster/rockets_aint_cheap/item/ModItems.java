package net.lobster.rockets_aint_cheap.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "rockets_aint_cheap");

    public static final RegistryObject<Item> AEROSTEEL_PLATE = ITEMS.register("aerosteel_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AEROSTEEL_INGOT = ITEMS.register("aerosteel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENGINE_PROPELLER = ITEMS.register("engine_propeller",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIER_1_ROCKET_PANEL = ITEMS.register("tier_1_rocket_panel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIER_2_ROCKET_PANEL = ITEMS.register("tier_2_rocket_panel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIER_3_ROCKET_PANEL = ITEMS.register("tier_3_rocket_panel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIER_4_ROCKET_PANEL = ITEMS.register("tier_4_rocket_panel",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
