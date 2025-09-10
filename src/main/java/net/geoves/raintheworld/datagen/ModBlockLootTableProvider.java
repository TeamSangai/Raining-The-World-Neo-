package net.geoves.raintheworld.datagen;

import net.geoves.raintheworld.block.ModBlocks;
import net.geoves.raintheworld.block.custom.SundewBlock;
import net.geoves.raintheworld.block.custom.WeedWormCropBlock;
import net.geoves.raintheworld.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SEAFOAM_PLANKS.get());
        dropSelf(ModBlocks.SEAFOAM_RAINEILIUM_TUBE.get());
        add(ModBlocks.SEAFOAM_RAINEILIUM_NEMATOCYST.get(), createShearsOnlyDrop(ModBlocks.SEAFOAM_RAINEILIUM_NEMATOCYST.asItem()));
        otherWhenSilkTouch(ModBlocks.SEAFOAM_RAINEILIUM_STONE_BLOCK.get(), Blocks.STONE);
        otherWhenSilkTouch(ModBlocks.SEAFOAM_RAINEILIUM_MUD_BLOCK.get(), Blocks.MUD);
        add(ModBlocks.SEAFOAM_RAINEILIUM_TUFT.get(), block -> createSeafoamTuftDrops(ModBlocks.SEAFOAM_RAINEILIUM_TUFT.get()));
        dropSelf(ModBlocks.SEAFOAM_RAINEILIUM_POLYP.get());


        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.WEED_WORM_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeedWormCropBlock.AGE, 4));

        this.add(ModBlocks.WEED_WORM_CROP.get(), this.createCropDrops(ModBlocks.WEED_WORM_CROP.get(),
                ModItems.WEED_WORM.get(), ModItems.WEED_LARVA.get(), lootItemConditionBuilder));

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SundewBlock.AGE, 5))
                                ).add(LootItem.lootTableItem(ModItems.SUNDEW_SEED.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                .add(LootItem.lootTableItem(ModItems.BUGMEAL.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SundewBlock.AGE, 4))
                                ).add(LootItem.lootTableItem(ModItems.SUNDEW_SEED.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .add(LootItem.lootTableItem(ModItems.BUGMEAL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SundewBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.SUNDEW_SEED.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                .add(LootItem.lootTableItem(ModItems.BUGMEAL.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ABYSSAL_SUNDEW_BLOCK.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SundewBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.SUNDEW_SEED.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

    }
    protected LootTable.Builder createSeafoamTuftDrops(Block block) {
            HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            return this.createSilkTouchOrShearsDispatchTable(
                    block,
                    this.applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(ModItems.WEED_LARVA)
                                    .when(LootItemRandomChanceCondition.randomChance(0.125F))
                                    .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE), 2))
                    )
            );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
