package com.skd.armorcosmetic.api;

import java.util.UUID;

import com.skd.armorcosmetic.api.inventory.CAStacksBase;
import com.skd.armorcosmetic.impl.ModObjects;

public class CosArmorAPI {

    public static CAStacksBase getCAStacks(UUID uuid) {
        return ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventory(uuid);
    }

    public static CAStacksBase getCAStacksClient(UUID uuid) {
        return ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).getCosArmorInventoryClient(uuid);
    }
}
