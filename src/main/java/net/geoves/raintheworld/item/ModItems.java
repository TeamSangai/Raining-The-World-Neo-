package net.geoves.raintheworld.item;

import net.geoves.raintheworld.RainTheWorld;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RainTheWorld.MODID);

    public static final DeferredItem<Item> DIETSELECTITEM = ITEMS.register("dietselector", () -> new DietSelector(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
