package net.geoves.raintheworld.block.custom;

import com.mojang.serialization.MapCodec;
import net.geoves.raintheworld.block.ModBlocks;
import net.geoves.raintheworld.worldgen.ModPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.awt.*;
import java.util.List;
import java.util.Optional;


public class SeafoamRaineiliumMudBlock extends SpreadingSnowyDirtBlock implements BonemealableBlock {
    public static final MapCodec<SeafoamRaineiliumMudBlock> CODEC = simpleCodec(SeafoamRaineiliumMudBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 14.0F, 16.0F);

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    public SeafoamRaineiliumMudBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends SpreadingSnowyDirtBlock> codec() {
        return null;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        BlockPos blockPos1 = blockPos.above();
        BlockState blockState1 = ModBlocks.SEAFOAM_RAINEILIUM_TUFT.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = serverLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(ModPlacedFeatures.SEAFOAM_CORAL_PLACED_KEY);


        seafoamLabel:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos1;
            for(int j = 0; j < i / 16; ++j) {
                    blockPos2 = blockPos2.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
                if (!serverLevel.getBlockState(blockPos2.below()).is(this) || serverLevel.getBlockState(blockPos2).isCollisionShapeFullBlock(serverLevel, blockPos2)) {
                    continue seafoamLabel;
                }
            }
            BlockState blockstate2 = serverLevel.getBlockState(blockPos2);
            if (blockstate2.is(blockState1.getBlock()) && randomSource.nextInt(10) == 0) {
                ((BonemealableBlock)blockState.getBlock()).performBonemeal(serverLevel, randomSource, blockPos2, blockstate2);
            }
            if (blockstate2.isAir()) {
                Holder<PlacedFeature> holder;
                if (randomSource.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = (serverLevel.getBiome(blockPos2).value()).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration)((ConfiguredFeature)list.get(0)).config()).feature();
                } else {
                    if (!optional.isPresent()) {
                        continue;
                    }

                    holder = optional.get();
                }

                (holder.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos2);
            }
        }
    }
}

