package com.skd.armorcosmetic.impl.client;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.OptionalInt;

public class PlayerInventoryHelper {

    public static Inventory getPlayerInventory(Player player) { return player.getInventory(); }

    public static OptionalInt getPlayerEquipmentSlotIndex(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            case HEAD -> OptionalInt.of(39);
            case CHEST -> OptionalInt.of(38);
            case LEGS -> OptionalInt.of(37);
            case FEET -> OptionalInt.of(36);
            case OFFHAND -> OptionalInt.of(40);
            default -> OptionalInt.empty();
        };
    }
}
