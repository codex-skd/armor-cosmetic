package com.skd.armorcosmetic.impl.client.gui;

import java.util.function.BiConsumer;

import com.skd.armorcosmetic.impl.ModConfigs;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class GuiCosArmorButton extends Button implements IShiftingWidget, ICreativeInvWidget {

    private final BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged;
    private final Identifier icon;

    public GuiCosArmorButton(int x, int y, int width, int height, Component message, Identifier icon,
            OnPress onPress, BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.icon = icon;
        this.onCreativeTabChanged = onCreativeTabChanged;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int bgColor = isHoveredOrFocused() ? 0xFFFFFFFF : 0xFF8B8B8B;
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);
        graphics.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, bgColor);
        if (icon != null) {
            int iconSize = ModConfigs.GuiButton_IconSize.get();
            int iw = iconSize > 0 ? iconSize : width - 4;
            int ih = iconSize > 0 ? iconSize : height - 4;
            int ix = getX() + (width - iw) / 2;
            int iy = getY() + (height - ih) / 2;
            graphics.blit(RenderPipelines.GUI_TEXTURED, icon, ix, iy, 0, 0, iw, ih, 16, 16);
        }
    }

    @Override
    public void shiftLeft(int amount) {
        setX(getX() + amount);
    }

    @Override
    public void onSelectedTabChanged(boolean isInventoryOpen) {
        if (onCreativeTabChanged != null) {
            onCreativeTabChanged.accept(this, isInventoryOpen);
        }
    }
}
