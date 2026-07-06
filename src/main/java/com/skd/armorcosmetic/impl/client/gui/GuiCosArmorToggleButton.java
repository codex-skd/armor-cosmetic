package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.impl.ModConfigs;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class GuiCosArmorToggleButton extends Button implements IShiftingWidget {

    public int state;
    private final Identifier iconOn;
    private final Identifier iconOff;

    public GuiCosArmorToggleButton(int x, int y, int width, int height, Component message, int state,
            Identifier iconOn, Identifier iconOff, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.state = state;
        this.iconOn = iconOn;
        this.iconOff = iconOff;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int bgColor = state == 1 ? 0xFF55FF55 : 0xFF808080;
        if (isHoveredOrFocused()) {
            bgColor = state == 1 ? 0xFF33DD33 : 0xFFAAAAAA;
        }
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);
        graphics.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, bgColor);
        Identifier icon = state == 1 ? iconOn : iconOff;
        if (icon != null) {
            int iconSize = ModConfigs.ToggleButton_IconSize.get();
            int iw = iconSize > 0 ? iconSize : width - 2;
            int ih = iconSize > 0 ? iconSize : height - 2;
            int ix = getX() + (width - iw) / 2;
            int iy = getY() + (height - ih) / 2;
            graphics.blit(RenderPipelines.GUI_TEXTURED, icon, ix, iy, 0, 0, iw, ih, 16, 16);
        }
    }

    @Override
    public void shiftLeft(int amount) {
        setX(getX() + amount);
    }
}
