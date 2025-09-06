package net.geoves.raintheworld.payloads.records;

import io.netty.buffer.ByteBuf;
import net.geoves.raintheworld.RainTheWorld;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DietRecord(String DietName) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DietRecord> DIET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, "diet_record"));

    public static final StreamCodec<ByteBuf, DietRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            DietRecord::DietName,
            DietRecord::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return DIET_TYPE;
    }
}
