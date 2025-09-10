package net.geoves.raintheworld.payloads;

import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.payloads.records.DietRecord;
import net.geoves.raintheworld.util.ModDataAttachments;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class DietPayloadReceiver {
    public static void receivePayload(DietRecord data, IPayloadContext context) {
        RainTheWorld.LOGGER.info("Payload Received");
        Minecraft.getInstance().player.setData(ModDataAttachments.DIET, data.DietName());
        if (data.DietName().equals("carnivore")) {
            RainTheWorld.LOGGER.info("Carnivore Diet Received.");
        }
    }
}
