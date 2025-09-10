package net.geoves.raintheworld;

import net.geoves.raintheworld.block.ModBlocks;
import net.geoves.raintheworld.item.ModItems;
import net.geoves.raintheworld.payloads.DietPayloadReceiver;
import net.geoves.raintheworld.payloads.records.DietRecord;
import net.geoves.raintheworld.util.ModDataAttachments;
import net.geoves.raintheworld.worldgen.biome.ModTerrablender;
import net.geoves.raintheworld.worldgen.biome.surface.ModSurfaceRuleData;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RainTheWorld.MODID)
public class RainTheWorld {
    public static final String MODID = "raintheworld";
    public static final Logger LOGGER = LogUtils.getLogger();




    public RainTheWorld(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModDataAttachments.register(modEventBus);
        ModTerrablender.registerBiomes();
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, ModSurfaceRuleData.makeRules());
    });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.DIETSELECTITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.SEAFOAM_PLANKS);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.WEED_LARVA);
            event.accept(ModItems.COOKED_WEED_LARVA);
            event.accept(ModItems.WEED_WORM);
            event.accept(ModItems.COOKED_WEED_WORM);
            event.accept(ModItems.BUGMEAL);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.GRAY_CLOAK);
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.SEAFOAM_RAINEILIUM_STONE_BLOCK);
            event.accept(ModBlocks.SEAFOAM_RAINEILIUM_TUFT);
            event.accept(ModBlocks.SEAFOAM_RAINEILIUM_TUBE);
            event.accept(ModBlocks.SEAFOAM_RAINEILIUM_NEMATOCYST);
            event.accept(ModBlocks.SEAFOAM_RAINEILIUM_POLYP);

            event.accept(ModItems.SUNDEW_SEED);
        }
    }

    @EventBusSubscriber(modid = MODID)
    public static class ModEventsBus {
        @SubscribeEvent
        public static void registerPayloads(final RegisterPayloadHandlersEvent event){
            final PayloadRegistrar registrar=event.registrar("1");
            registrar.playToServer( DietRecord.DIET_TYPE, DietRecord.STREAM_CODEC,
                    DietPayloadReceiver::receivePayload);
        }
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
