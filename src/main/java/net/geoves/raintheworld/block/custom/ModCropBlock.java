package net.geoves.raintheworld.block.custom;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModCropBlock extends CropBlock {
    public ModCropBlock(Properties properties) {
        super(properties);
    }
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}
