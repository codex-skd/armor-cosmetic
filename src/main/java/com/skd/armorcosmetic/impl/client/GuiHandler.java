package com.skd.armorcosmetic.impl.client;

import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorButton;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorInventory;
import com.skd.armorcosmetic.impl.client.gui.GuiCosArmorToggleButton;
import com.skd.armorcosmetic.impl.client.gui.IShiftingWidget;
import com.skd.armorcosmetic.impl.network.payload.PayloadOpenCosArmorInventory;
import com.skd.armorcosmetic.impl.network.payload.PayloadOpenNormalInventory;
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
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> containerScreen)) return;

        if (lastLeft != containerScreen.getGuiLeft()) {
            int delta = containerScreen.getGuiLeft() - lastLeft;
            lastLeft = containerScreen.getGuiLeft();
            containerScreen.children().stream()
                    .filter(w -> w instanceof IShiftingWidget)
                    .map(w -> (IShiftingWidget) w)
                    .forEach(w -> w.shiftLeft(delta));
        }

        if (event.getScreen() instanceof CreativeModeInventoryScreen creativeScreen) {
            boolean isInventoryOpen = creativeScreen.isInventoryOpen();
            if (lastInventoryOpen != isInventoryOpen) {
                lastInventoryOpen = isInventoryOpen;
                containerScreen.children().stream()
                        .filter(w -> w instanceof com.skd.armorcosmetic.impl.client.gui.ICreativeInvWidget)
                        .map(w -> (com.skd.armorcosmetic.impl.client.gui.ICreativeInvWidget) w)
                        .forEach(w -> w.onSelectedTabChanged(isInventoryOpen));
            }
        }
    }

    private void handleGuiInitPost(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof AbstractContainerScreen<?> containerScreen) {
            if (!(event.getScreen() instanceof CreativeModeInventoryScreen)) {
                lastLeft = containerScreen.getGuiLeft();
            } else {
                lastLeft = 0;
            }
            lastInventoryOpen = true;
        }

        if (event.getScreen() instanceof InventoryScreen || event.getScreen() instanceof GuiCosArmorInventory) {
            AbstractContainerScreen<?> containerScreen = (AbstractContainerScreen<?>) event.getScreen();

            if (!ModConfigs.CosArmorGuiButton_Hidden.get()) {
                int btnX = containerScreen.getGuiLeft() + ModConfigs.CosArmorGuiButton_Left.get();
                int btnY = containerScreen.getGuiTop() + ModConfigs.CosArmorGuiButton_Top.get();
                boolean isCosInventory = event.getScreen() instanceof GuiCosArmorInventory;
                Component label = Component.translatable(isCosInventory ? "cos.gui.buttonnormal" : "cos.gui.buttoncos");

                event.addListener(new GuiCosArmorButton(btnX, btnY,
                        ModConfigs.CosArmorGuiButton_Width.get(),
                        ModConfigs.CosArmorGuiButton_Height.get(),
                        label, btn -> {
                    if (isCosInventory) {
                        InventoryScreen newScreen = new InventoryScreen(containerScreen.getMinecraft().player);
                        InventoryScreenAccess.setXMouse(newScreen, ((GuiCosArmorInventory) containerScreen).oldMouseX);
                        InventoryScreenAccess.setYMouse(newScreen, ((GuiCosArmorInventory) containerScreen).oldMouseY);
                        containerScreen.getMinecraft().setScreen(newScreen);
                        ClientPacketDistributor.sendToServer(new PayloadOpenNormalInventory());
                    } else {
                        ClientPacketDistributor.sendToServer(new PayloadOpenCosArmorInventory());
                    }
                }, null));
            }

            if (!ModConfigs.CosArmorToggleButton_Hidden.get()) {
                int btnX = containerScreen.getGuiLeft() + ModConfigs.CosArmorToggleButton_Left.get();
                int btnY = containerScreen.getGuiTop() + ModConfigs.CosArmorToggleButton_Top.get();
                event.addListener(new GuiCosArmorToggleButton(btnX, btnY,
                        ModConfigs.CosArmorToggleButton_Width.get(),
                        ModConfigs.CosArmorToggleButton_Height.get(),
                        Component.empty(),
                        PlayerRenderHandler.Disabled ? 1 : 0,
                        btn -> {
                            PlayerRenderHandler.Disabled = !PlayerRenderHandler.Disabled;
                            ((GuiCosArmorToggleButton) btn).state = PlayerRenderHandler.Disabled ? 1 : 0;
                        }));
            }
        } else if (event.getScreen() instanceof CreativeModeInventoryScreen) {
            AbstractContainerScreen<?> containerScreen = (AbstractContainerScreen<?>) event.getScreen();

            if (!ModConfigs.CosArmorCreativeGuiButton_Hidden.get()) {
                int btnX = ModConfigs.CosArmorCreativeGuiButton_Left.get();
                int btnY = containerScreen.getGuiTop() + ModConfigs.CosArmorCreativeGuiButton_Top.get();
                Component label = Component.translatable("cos.gui.buttoncos");

                event.addListener(new GuiCosArmorButton(btnX, btnY,
                        ModConfigs.CosArmorCreativeGuiButton_Width.get(),
                        ModConfigs.CosArmorCreativeGuiButton_Height.get(),
                        label, btn -> {
                    ClientPacketDistributor.sendToServer(new PayloadOpenCosArmorInventory());
                }, (button, isInventoryOpen) -> {
                    button.visible = isInventoryOpen;
                }));
            }
        }
    }

    public void registerEvents() {
        NeoForge.EVENT_BUS.addListener(this::handleGuiInitPost);
        NeoForge.EVENT_BUS.addListener(this::handleGuiDrawPre);
    }

    public void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModObjects.getTypeContainerCosArmor(), GuiCosArmorInventory::new);
    }
}
