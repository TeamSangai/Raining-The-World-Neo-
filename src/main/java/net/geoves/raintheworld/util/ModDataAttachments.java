package net.geoves.raintheworld.util;

import com.mojang.serialization.Codec;
import net.geoves.raintheworld.RainTheWorld;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, RainTheWorld.MODID);

    public static final Supplier<AttachmentType<String>> DIET = ATTACHMENT_TYPES.register("diets",
            () -> AttachmentType.builder(() -> "").serialize(Codec.unit("omnivore")).build()
    );
    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
