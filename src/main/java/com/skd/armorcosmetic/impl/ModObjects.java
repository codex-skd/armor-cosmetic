package com.skd.armorcosmetic.impl;

import com.skd.armorcosmetic.impl.client.InventoryManagerClient;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.init.neoforge.NeoForgeArmorCosmetic;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModObjects {

    public static final Logger logger = LogManager.getLogger(NeoForgeArmorCosmetic.class);
    public static final InventoryManager invMan = FMLEnvironment.dist.isClient() ? new InventoryManagerClient() : new InventoryManager();

    public static MenuType<ContainerCosArmor> getTypeContainerCosArmor() {
        return NeoForgeArmorCosmetic.typeContainerCosArmor.get();
    }

}
