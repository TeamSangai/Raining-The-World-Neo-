package net.geoves.raintheworld.worldgen.biome.surface;

import net.geoves.raintheworld.block.ModBlocks;
import net.geoves.raintheworld.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import terrablender.api.SurfaceRuleManager;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource SEAFOAM_STONE = makeStateRule(ModBlocks.SEAFOAM_RAINEILIUM_STONE_BLOCK.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource SEAFOAM_MUD = makeStateRule(ModBlocks.SEAFOAM_RAINEILIUM_MUD_BLOCK.get());
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource underwater = SurfaceRules.ifTrue(
                SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
                SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())
        );

        SurfaceRules.RuleSource surface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.SEAFOAM_RAINEILIUM_MUD_BLOCK.get().defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.MUD.defaultBlockState())),
                SurfaceRules.state(Blocks.STONE.defaultBlockState())
        );
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SEAFOAM_TIDE_FLATS),
                SurfaceRules.sequence(
                        surface,
                        underwater));

    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    // already registered but this would work too.
    public static void registerSurfaceRules() {
        SurfaceRuleManager.addSurfaceRules(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                "raintheworld", //your modId
                makeRules()
        );
    }
}
