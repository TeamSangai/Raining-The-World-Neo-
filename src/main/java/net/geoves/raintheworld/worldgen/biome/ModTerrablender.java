package net.geoves.raintheworld.worldgen.biome;

import net.geoves.raintheworld.RainTheWorld;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Region;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, "overworld"), 2));
    }
}
