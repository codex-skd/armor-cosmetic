package com.skd.armorcosmetic.impl.client;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import com.skd.armorcosmetic.impl.network.payload.PayloadOpenCosArmorInventory;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;

public enum KeyHandler {
    INSTANCE;

    public KeyMapping keyOpenCosArmorInventory = new KeyMapping(
            "cos.key.opencosarmorinventory",
            GLFW.GLFW_KEY_LEFT_ALT,
            KeyMapping.Category.INVENTORY);

    private void handleClientTick(ClientTickEvent.Pre event) {
        if (keyOpenCosArmorInventory.consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.gui.screen() == null && mc.player != null) {
                ClientPacketDistributor.sendToServer(new PayloadOpenCosArmorInventory());
            }
        }
    }

    public void registerEvents() {
        NeoForge.EVENT_BUS.addListener(this::handleClientTick);
    }

    public void registerKeyMappings(Consumer<KeyMapping> consumer) {
        consumer.accept(keyOpenCosArmorInventory);
    }
}
