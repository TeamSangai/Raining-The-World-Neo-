package net.geoves.raintheworld.util;

import net.geoves.raintheworld.RainTheWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> SEAFOAM_RAINEILIUMS = createTag("seafoam_raineiliums");
        public static final TagKey<Block> SUNDEW_MAY_PLACE_ON = createTag("sundew_may_place_on");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PLAYER_HERBIVORE_FOOD = createTag("player_herbivore_food");
        public static final TagKey<Item> PLAYER_CARNIVORE_FOOD = createTag("player_carnivore_food");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, name));
        }
    }
}
