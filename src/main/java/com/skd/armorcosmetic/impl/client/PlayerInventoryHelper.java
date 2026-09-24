package com.skd.armorcosmetic.impl.client;

import java.util.EnumMap;
import java.util.OptionalInt;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class PlayerInventoryHelper {

    private static final EnumMap<EquipmentSlot, OptionalInt> known = new EnumMap<>(EquipmentSlot.class);

    static {
        known.put(EquipmentSlot.HEAD, OptionalInt.of(EquipmentSlot.HEAD.getIndex(36)));
        known.put(EquipmentSlot.CHEST, OptionalInt.of(EquipmentSlot.CHEST.getIndex(36)));
        known.put(EquipmentSlot.LEGS, OptionalInt.of(EquipmentSlot.LEGS.getIndex(36)));
        known.put(EquipmentSlot.FEET, OptionalInt.of(EquipmentSlot.FEET.getIndex(36)));
    }

    public static Inventory getPlayerInventory(Player player) {
        return player.getInventory();
    }

    public static OptionalInt getPlayerEquipmentSlotIndex(EquipmentSlot slot) {
        return known.getOrDefault(slot, OptionalInt.empty());
    }
}
