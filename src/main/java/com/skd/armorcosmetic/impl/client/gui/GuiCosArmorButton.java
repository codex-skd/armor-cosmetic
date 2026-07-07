package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import java.util.function.BiConsumer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class GuiCosArmorButton extends Button implements IShiftingWidget, ICreativeInvWidget {

    private static final Identifier ICON_CHESTPLATE = ArmorCosmetic.id("textures/gui/icon_chestplate.png");
    private final BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged;
    private final boolean showIcon;

    public GuiCosArmorButton(int x, int y, int width, int height, Component message, boolean showIcon,
            OnPress onPress, BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.showIcon = showIcon;
        this.onCreativeTabChanged = onCreativeTabChanged;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int bgColor = isHoveredOrFocused() ? 0xFFFFFFFF : 0xFF8B8B8B;
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);
        graphics.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, bgColor);
        if (showIcon && width >= 16 && height >= 16) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ICON_CHESTPLATE, getX() + (width - 16) / 2, getY() + (height - 16) / 2, 0, 0, 16, 16, 16, 16);
        } else if (width >= 14) {
            graphics.centeredText(
                    net.minecraft.client.Minecraft.getInstance().font,
                    getMessage(),
                    getX() + width / 2,
                    getY() + (height - 8) / 2,
                    0x000000);
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
