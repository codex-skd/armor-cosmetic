package com.skd.armorcosmetic.impl.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class GuiCosArmorToggleButton extends Button implements IShiftingWidget {

    public int state;

    public GuiCosArmorToggleButton(int x, int y, int width, int height, Component message, int state, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.state = state;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int color = state == 1 ? 0xFF00FF00 : 0xFF808080;
        if (isHoveredOrFocused()) {
            color = state == 1 ? 0xFF00DD00 : 0xFFAAAAAA;
        }
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);
        graphics.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, color);
        String text = state == 1 ? "V" : "X";
        Minecraft mc = Minecraft.getInstance();
        graphics.centeredText(mc.font, text, getX() + width / 2, getY() + (height - 8) / 2, 0xFFFFFF);
    }

    @Override
    public void shiftLeft(int amount) {
        setX(getX() + amount);
    }
}
