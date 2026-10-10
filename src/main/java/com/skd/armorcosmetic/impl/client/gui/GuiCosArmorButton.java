package com.skd.armorcosmetic.impl.client.gui;

import java.util.function.BiConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

public class GuiCosArmorButton extends Button implements IShiftingWidget, ICreativeInvWidget {

    private final BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged;

    public GuiCosArmorButton(int x, int y, int width, int height, Component message, Button.OnPress onPress,
            BiConsumer<GuiCosArmorButton, Boolean> onCreativeTabChanged) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.onCreativeTabChanged = onCreativeTabChanged;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        boolean state = isHoveredOrFocused();
        graphics.blit(RenderPipelines.GUI_TEXTURED, GuiCosArmorInventory.TEXTURE, getX(), getY(),
                state ? 10 : 0, 166, 10, 10, 256, 256);
        if (state) {
            graphics.centeredText(Minecraft.getInstance().font, getMessage(), getX() + 5, getY() + height, 0xFFFFFF);
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
