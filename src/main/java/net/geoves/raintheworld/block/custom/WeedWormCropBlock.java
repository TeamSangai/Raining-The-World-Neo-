package net.geoves.raintheworld.block.custom;

import net.geoves.raintheworld.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WeedWormCropBlock extends ModCropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
    private static final VoxelShape[] SHAPE_BY_AGE =
            new VoxelShape[]{
                    Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)
     };

    public WeedWormCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.WEED_LARVA;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
}
