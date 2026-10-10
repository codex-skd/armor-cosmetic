package com.skd.armorcosmetic.impl.client;

import com.skd.armorcosmetic.impl.client.gui.InventoryScreenAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class ClientPayloadHandler {

    private static float mouseX;
    private static float mouseY;

    private ClientPayloadHandler() {
    }

    public static void setMouse(float x, float y) {
        mouseX = x;
        mouseY = y;
    }

    public static void openNormalInventory() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }
        InventoryScreen gui = new InventoryScreen(mc.player);
        InventoryScreenAccess.setXMouse(gui, mouseX);
        InventoryScreenAccess.setYMouse(gui, mouseY);
        mc.setScreen(gui);
    }
}
