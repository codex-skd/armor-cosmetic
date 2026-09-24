package com.skd.armorcosmetic.init.neoforge;

import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.client.GuiHandler;
import com.skd.armorcosmetic.impl.client.KeyHandler;
import com.skd.armorcosmetic.impl.client.PlayerRenderHandler;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.network.ModPayloads;
import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(ArmorCosmetic.MODID)
public class NeoForgeArmorCosmetic {

    private static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(BuiltInRegistries.MENU, ArmorCosmetic.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ContainerCosArmor>> typeContainerCosArmor = MENU.register("inventorycosarmor", () -> new MenuType<>(ModObjects.invMan::createContainerClient, FeatureFlags.VANILLA_SET));

    public NeoForgeArmorCosmetic(IEventBus bus) {
        MENU.register(bus);
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(this::setupKeyMappings);
            bus.addListener(this::setupMenuScreens);
        }
        bus.addListener(this::setupPayloadHandlers);
        ModConfigs.registerConfigs();
    }

    private void setup(FMLCommonSetupEvent event) {
        ModObjects.invMan.registerEvents();
    }

    private void setupClient(FMLClientSetupEvent event) {
        ModObjects.invMan.registerEventsClient();
        GuiHandler.INSTANCE.registerEvents();
        KeyHandler.INSTANCE.registerEvents();
        PlayerRenderHandler.INSTANCE.registerEvents();
    }

    private void setupKeyMappings(RegisterKeyMappingsEvent event) {
        KeyHandler.INSTANCE.registerKeyMappings(event::register);
    }

    private void setupMenuScreens(RegisterMenuScreensEvent event) {
        GuiHandler.INSTANCE.registerMenuScreens(event);
    }

    private void setupPayloadHandlers(RegisterPayloadHandlersEvent event) {
        ModPayloads.setupPayloads(event.registrar("5"));
    }

}
