package net.geoves.raintheworld.worldgen.biome;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> SEAFOAM_TIDE_FLATS = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, "seafoam_tide_flats"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(SEAFOAM_TIDE_FLATS, seafoamTideFlats(context));
    }

    private static Biome seafoamTideFlats(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

    BiomeGenerationSettings.Builder biomeBuilder =
            new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
    //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEAFOAM_CORAL_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(1.0f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .specialEffects((new BiomeSpecialEffects.Builder())
            .waterColor(0x6CD1AB)
                        .waterFogColor(0x59B587)
                        .skyColor(0xACDEC4)
                        .fogColor(0x91CCAD)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
            .build();
}
}
