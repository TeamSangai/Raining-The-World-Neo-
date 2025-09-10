package net.geoves.raintheworld.worldgen.tree;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SEAFOAM_CORAL = new TreeGrower(RainTheWorld.MODID + ":seafoam_coral",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SEAFOAM_CORAL_KEY), Optional.empty());
}
