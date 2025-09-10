package net.geoves.raintheworld.datagen;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.block.ModBlocks;
import net.geoves.raintheworld.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> WEED_LARVA_SMELTABLES = List.of(ModItems.WEED_LARVA);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEAFOAM_PLANKS.asItem(), 4).requires(ModBlocks.SEAFOAM_RAINEILIUM_TUBE).unlockedBy("has_seafoam_tube", has(ModBlocks.SEAFOAM_RAINEILIUM_TUBE)).save(recipeOutput, "raintheworld:seafoamtubetoplanks");
        oreSmelting(recipeOutput, WEED_LARVA_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_WEED_LARVA.asItem(), 0.2f, 200, "cooked_weed_larva");


    }
}
