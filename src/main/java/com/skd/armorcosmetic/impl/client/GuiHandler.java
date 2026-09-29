package com.skd.armorcosmetic.impl.client;

import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorButton;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorInventory;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorToggleButton;
import com.skd.armorcosmetic.impl.client.gui.ICreativeInvWidget;
import com.skd.armorcosmetic.impl.client.gui.IShiftingWidget;
import com.skd.armorcosmetic.impl.network.payload.PayloadOpenCosArmorInventory;
import com.skd.armorcosmetic.impl.network.payload.PayloadOpenNormalInventory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;

public enum GuiHandler {
    INSTANCE;

    private int lastLeft;
    private boolean lastInventoryOpen;

    private void handleGuiDrawPre(ScreenEvent.Render.Pre event) {
        if (event.getScreen() instanceof AbstractContainerScreen<?> screen) {
            if (lastLeft != screen.getGuiLeft()) {
                int diffLeft = screen.getGuiLeft() - lastLeft;
                lastLeft = screen.getGuiLeft();
                screen.children().stream()
                        .filter(IShiftingWidget.class::isInstance)
                        .map(IShiftingWidget.class::cast)
                        .forEach(b -> b.shiftLeft(diffLeft));
            }
            if (event.getScreen() instanceof CreativeModeInventoryScreen) {
                boolean isInventoryOpen = ((CreativeModeInventoryScreen) event.getScreen()).isInventoryOpen();
                if (lastInventoryOpen != isInventoryOpen) {
                    lastInventoryOpen = isInventoryOpen;
                    screen.children().stream()
                            .filter(ICreativeInvWidget.class::isInstance)
                            .map(ICreativeInvWidget.class::cast)
                            .forEach(b -> b.onSelectedTabChanged(isInventoryOpen));
                }
            }
        }
    }

    private void handleGuiInitPost(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof AbstractContainerScreen<?> screen) {
            lastLeft = screen instanceof CreativeModeInventoryScreen ? 0 : screen.getGuiLeft();
            lastInventoryOpen = true;
        }

        if (event.getScreen() instanceof InventoryScreen || event.getScreen() instanceof GuiCosArmorInventory) {
            AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) event.getScreen();

            if (!ModConfigs.CosArmorGuiButton_Hidden.get()) {
                event.addListener(new GuiCosArmorButton(
                        screen.getGuiLeft() + ModConfigs.CosArmorGuiButton_Left.get(),
                        screen.getGuiTop() + ModConfigs.CosArmorGuiButton_Top.get(),
                        10, 10,
                        event.getScreen() instanceof GuiCosArmorInventory ?
                                Component.translatable("cos.gui.buttonnormal") :
                                Component.translatable("cos.gui.buttoncos"),
                        button -> {
                            if (screen instanceof GuiCosArmorInventory) {
                                InventoryScreen newGui = new InventoryScreen(screen.getMinecraft().player);
                                InventoryScreenAccess.setXMouse(newGui, ((GuiCosArmorInventory) screen).oldMouseX);
                                InventoryScreenAccess.setYMouse(newGui, ((GuiCosArmorInventory) screen).oldMouseY);
                                screen.getMinecraft().setScreen(newGui);
                                ClientPacketDistributor.sendToServer(new PayloadOpenNormalInventory());
                            } else {
                                ClientPacketDistributor.sendToServer(new PayloadOpenCosArmorInventory());
                            }
                        },
                        null));
            }
            if (!ModConfigs.CosArmorToggleButton_Hidden.get()) {
                event.addListener(new GuiCosArmorToggleButton(
                        screen.getGuiLeft() + ModConfigs.CosArmorToggleButton_Left.get(),
                        screen.getGuiTop() + ModConfigs.CosArmorToggleButton_Top.get(),
                        5, 5,
                        Component.empty(),
                        PlayerRenderHandler.Disabled ? 1 : 0,
                        button -> {
                            PlayerRenderHandler.Disabled = !PlayerRenderHandler.Disabled;
                            ((GuiCosArmorToggleButton) button).state = PlayerRenderHandler.Disabled ? 1 : 0;
                        }));
            }
        } else if (event.getScreen() instanceof CreativeModeInventoryScreen) {
            AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) event.getScreen();

            if (!ModConfigs.CosArmorCreativeGuiButton_Hidden.get()) {
                event.addListener(new GuiCosArmorButton(
                        ModConfigs.CosArmorCreativeGuiButton_Left.get(),
                        screen.getGuiTop() + ModConfigs.CosArmorCreativeGuiButton_Top.get(),
                        10, 10,
                        Component.translatable("cos.gui.buttoncos"),
                        button -> ClientPacketDistributor.sendToServer(new PayloadOpenCosArmorInventory()),
                        (button, isInventoryOpen) -> button.visible = isInventoryOpen));
            }
        }
    }

    public void registerEvents() {
        NeoForge.EVENT_BUS.addListener(this::handleGuiDrawPre);
        NeoForge.EVENT_BUS.addListener(this::handleGuiInitPost);
    }

    public void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModObjects.getTypeContainerCosArmor(), GuiCosArmorInventory::new);
    }

}
