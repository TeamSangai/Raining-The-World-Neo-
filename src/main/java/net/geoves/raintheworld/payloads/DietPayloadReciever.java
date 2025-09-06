package net.geoves.raintheworld.payloads;

import net.geoves.raintheworld.gui.screen.DietTypeSelectScreen;
import net.geoves.raintheworld.payloads.records.DietRecord;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class DietPayloadReciever {
    @OnlyIn(Dist.CLIENT)
    public static void recievePayload(DietRecord data, IPayloadContext context) {

    }
}
