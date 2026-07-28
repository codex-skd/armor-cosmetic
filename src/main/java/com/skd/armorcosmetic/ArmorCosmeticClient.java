package com.skd.armorcosmetic;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ArmorCosmetic.MODID, value = Dist.CLIENT)
public class ArmorCosmeticClient {

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
    }
}
