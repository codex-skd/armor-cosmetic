package com.skd.armorcosmetic;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.network.ModPayloads;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(ArmorCosmetic.MODID)
public class ArmorCosmetic {

    public static final String MODID = "armor_cosmetic";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(BuiltInRegistries.MENU, MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ContainerCosArmor>> typeContainerCosArmor = MENU.register(
            "inventorycosarmor",
            () -> new MenuType<>((id, inv) -> ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).createContainerClient(id, inv),
                    net.minecraft.world.flag.FeatureFlags.VANILLA_SET));

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public ArmorCosmetic(IEventBus modEventBus, ModContainer modContainer) {
        MENU.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::setupPayloadHandlers);

        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupKeyMappings);
        modEventBus.addListener(this::setupMenuScreens);

        ModConfigs.registerConfigs(modContainer);
    }

    private void setup(FMLCommonSetupEvent event) {
        ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).registerEvents();
    }

    private void setupClient(FMLClientSetupEvent event) {
        ((com.skd.armorcosmetic.impl.InventoryManager) ModObjects.invMan).registerEventsClient();
        com.skd.armorcosmetic.impl.client.GuiHandler.INSTANCE.registerEvents();
        com.skd.armorcosmetic.impl.client.KeyHandler.INSTANCE.registerEvents();
        com.skd.armorcosmetic.impl.client.PlayerRenderHandler.INSTANCE.registerEvents();
    }

    private void setupKeyMappings(RegisterKeyMappingsEvent event) {
        com.skd.armorcosmetic.impl.client.KeyHandler.INSTANCE.registerKeyMappings(event::register);
    }

    private void setupMenuScreens(RegisterMenuScreensEvent event) {
        com.skd.armorcosmetic.impl.client.GuiHandler.INSTANCE.registerMenuScreens(event);
    }

    private void setupPayloadHandlers(RegisterPayloadHandlersEvent event) {
        ModPayloads.setupPayloads(event.registrar(ModPayloads.VERSION));
    }
}
