package net.geoves.raintheworld.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.payloads.records.DietRecord;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.PacketDistributor;

public class DietTypeSelectScreen extends Screen {
    private static final ResourceLocation WINDOW = ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, "textures/gui/diet_selection.png");

    protected int guiTop, guiLeft;



    protected static final int windowWidth = 176;
    protected static final int windowHeight = 168;

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        double X = mouseX - guiLeft;
        double Y = mouseY - guiTop;
        if (X > 3 && X < 60 && Y < 166 && Y > 0) {
            PacketDistributor.sendToServer(new DietRecord("carnivore"));
            onClose();
        }
        RainTheWorld.LOGGER.info("clicked {}, {}", mouseX, mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }


    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - windowWidth) / 2;
        this.guiTop = (this.height - windowHeight) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(WINDOW, guiLeft, guiTop, 0.5f, 0.5f,176, 166, 176, 166);
    }

    public DietTypeSelectScreen(Component title) {
        super(title);
    }
}
