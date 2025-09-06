package net.geoves.raintheworld.gui.screen;

import net.geoves.raintheworld.RainTheWorld;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DietTypeSelectScreen extends Screen {
    private static final ResourceLocation WINDOW = ResourceLocation.fromNamespaceAndPath(RainTheWorld.MODID, "textures/gui/diet_selection.png");

    protected int guiTop, guiLeft;



    protected static final int windowWidth = 176;
    protected static final int windowHeight = 168;

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
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

    protected DietTypeSelectScreen(Component title) {
        super(title);
    }
}
