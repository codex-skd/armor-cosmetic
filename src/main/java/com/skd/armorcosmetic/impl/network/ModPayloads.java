package com.skd.armorcosmetic.impl.network;

import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.*;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModPayloads {

    public static final String VERSION = "5";

    public static void setupPayloads(PayloadRegistrar registrar) {
        registrar.playToClient(PayloadSyncCosArmor.TYPE, PayloadSyncCosArmor.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                InventoryCosArmor inventory = ModObjects.invMan instanceof com.skd.armorcosmetic.impl.InventoryManager
                        ? ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventoryClient(payload.uuid())
                        : null;
                if (inventory != null) {
                    inventory.setStackInSlot(payload.slot(), payload.itemCosArmor());
                    inventory.setSkinArmor(payload.slot(), payload.isSkinArmor());
                }
            });
        });

        registrar.playToClient(PayloadSyncHiddenFlags.TYPE, PayloadSyncHiddenFlags.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                InventoryCosArmor inventory = ModObjects.invMan instanceof com.skd.armorcosmetic.impl.InventoryManager
                        ? ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventoryClient(payload.uuid())
                        : null;
                if (inventory != null) {
                    inventory.setHidden(payload.modid(), payload.identifier(), payload.hidden());
                }
            });
        });

        registrar.playToServer(PayloadOpenCosArmorInventory.TYPE, PayloadOpenCosArmorInventory.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer serverPlayer) {
                    InventoryCosArmor inventory = (InventoryCosArmor) ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventory(serverPlayer.getUUID());
                    serverPlayer.openMenu(inventory, buf -> buf.writeUUID(serverPlayer.getUUID()));
                }
            });
        });

        registrar.playToServer(PayloadOpenNormalInventory.TYPE, PayloadOpenNormalInventory.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer serverPlayer) {
                    int containerId = serverPlayer.containerMenu.containerId;
                    serverPlayer.doCloseContainer();
                    serverPlayer.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerClosePacket(
                            containerId));
                }
            });
        });

        registrar.playToServer(PayloadSetSkinArmor.TYPE, PayloadSetSkinArmor.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer serverPlayer) {
                    InventoryCosArmor inventory = (InventoryCosArmor) ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventory(serverPlayer.getUUID());
                    int slot = payload.slot();
                    if (slot >= 0 && slot < inventory.getSlots()) {
                        inventory.setSkinArmor(slot, payload.isSkinArmor());
                    }
                }
            });
        });

        registrar.playToServer(PayloadSetHiddenFlags.TYPE, PayloadSetHiddenFlags.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer serverPlayer) {
                    InventoryCosArmor inventory = (InventoryCosArmor) ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventory(serverPlayer.getUUID());
                    inventory.setHidden(payload.modid(), payload.identifier(), payload.hidden());
                }
            });
        });
    }
}
