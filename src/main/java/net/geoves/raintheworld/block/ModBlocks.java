package net.geoves.raintheworld.block;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.block.custom.*;
import net.geoves.raintheworld.item.ModItems;
import net.geoves.raintheworld.worldgen.tree.ModTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RainTheWorld.MODID);

    public static final DeferredBlock<Block> WEED_WORM_CROP = BLOCKS.register("weed_worm_crop", () -> new WeedWormCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> SEAFOAM_PLANKS = registerBlock("seafoam_planks", () -> new Block(BlockBehaviour.Properties.of().strength(2, 3).sound(SoundType.NETHER_WOOD)));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_STONE_BLOCK = registerBlock("seafoam_raineilium_stone_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(0.45f, 0.5f).sound(SoundType.CORAL_BLOCK)));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_MUD_BLOCK = registerBlock("seafoam_raineilium_mud_block", () -> new SeafoamRaineiliumMudBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(0.4f, 0.5f).sound(SoundType.WET_SPONGE)));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_TUFT = registerBlock("seafoam_raineilium_tuft", () -> new SeafoamFoliage(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.WET_GRASS).instabreak().noCollission().replaceable()));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_TUBE = registerBlock("seafoam_raineilium_tube", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHER_WOOD).strength(2f,2f)));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_NEMATOCYST = registerBlock("seafoam_raineilium_nematocyst", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.BIG_DRIPLEAF).requiresCorrectToolForDrops().strength(0.3f, 0.3f)
            .randomTicks().noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> SEAFOAM_RAINEILIUM_POLYP = registerBlock("seafoam_raineilium_polyp", () -> new SeafoamPolypBlock(ModTreeGrowers.SEAFOAM_CORAL, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)));

    public static final DeferredBlock<Block> ABYSSAL_SUNDEW_BLOCK = BLOCKS.register("abyssal_sundew",
            () -> new SundewBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
