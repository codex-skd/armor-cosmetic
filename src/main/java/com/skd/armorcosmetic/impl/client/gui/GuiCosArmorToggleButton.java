package com.skd.armorcosmetic.impl.client.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

public class GuiCosArmorToggleButton extends Button implements IShiftingWidget {

    public int state;
    private Component tooltipOn;
    private Component tooltipOff;

    public GuiCosArmorToggleButton(int x, int y, int width, int height, Component message, int state,
            Component tooltipOn, Component tooltipOff, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.state = state;
        this.tooltipOn = tooltipOn;
        this.tooltipOff = tooltipOff;
        updateTooltip();
    }

    private void updateTooltip() {
        Component tip = state == 1 ? tooltipOn : tooltipOff;
        if (tip != null) {
            setTooltip(Tooltip.create(tip));
        } else {
            setTooltip((Tooltip) null);
        }
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int bgColor;
        String text;
        if (state == 1) {
            bgColor = isHoveredOrFocused() ? 0xFF33DD33 : 0xFF55FF55;
            text = "S";
        } else {
            bgColor = isHoveredOrFocused() ? 0xFFAAAAAA : 0xFF808080;
            text = "A";
        }
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);
        graphics.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, bgColor);
        if (width >= 10 && height >= 8) {
            graphics.centeredText(
                    net.minecraft.client.Minecraft.getInstance().font,
                    text,
                    getX() + width / 2,
                    getY() + (height - 8) / 2,
                    0xFFFFFF);
        }
    }

    @Override
    public void shiftLeft(int amount) {
        setX(getX() + amount);
    }
}
