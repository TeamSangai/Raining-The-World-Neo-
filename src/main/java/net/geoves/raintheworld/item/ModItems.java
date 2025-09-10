package net.geoves.raintheworld.item;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RainTheWorld.MODID);

    public static final DeferredItem<Item> DIETSELECTITEM = ITEMS.register("dietselector", () -> new DietSelector(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WEED_LARVA = ITEMS.register("weed_larva", () -> new ItemNameBlockItem(ModBlocks.WEED_WORM_CROP.get(), new Item.Properties().food(new FoodProperties(1, 0.2f, false, 0.8f, Optional.empty(), new ArrayList<>()))));
    public static final DeferredItem<Item> COOKED_WEED_LARVA = ITEMS.register("cooked_weed_larva", () -> new Item(new Item.Properties().food(new FoodProperties(2, 1.2f, false, 0.8f, Optional.empty(), new ArrayList<>()))));
    public static final DeferredItem<Item> BUGMEAL = ITEMS.register("bugmeal", () -> new Item(new Item.Properties().food(new FoodProperties(3, 1.2f, false, 1.2f, Optional.empty(), new ArrayList<>()))));
    public static final DeferredItem<Item> WEED_WORM = ITEMS.register("weed_worm", () -> new Item(new Item.Properties().food(new FoodProperties(4, 0.6f, false, 1.6f, Optional.empty(), new ArrayList<>()))));
    public static final DeferredItem<Item> COOKED_WEED_WORM = ITEMS.register("cooked_weed_worm", () -> new Item(new Item.Properties().food(new FoodProperties(7, 1.6f, false, 1.6f, Optional.empty(), new ArrayList<>()))));
    public static final DeferredItem<Item> GRAY_CLOAK = ITEMS.register("gray_cloak", () -> new ArmorItem(ModArmorMaterials.CLOAK_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE
            , new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(9))));
    public static final DeferredItem<Item> SUNDEW_SEED = ITEMS.register("sundew_seed",
            () -> new ItemNameBlockItem(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
