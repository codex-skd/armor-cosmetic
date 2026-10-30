package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.InventoryManager;
import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.client.InventoryScreenAccess;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSetSkinArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class GuiCosArmorInventory extends AbstractRecipeBookScreen<ContainerCosArmor> {

    public static final Identifier TEXTURE = ArmorCosmetic.id("textures/gui/cosarmorinventory.png");

    private final EffectsInInventory effects;
    public float oldMouseX;
    public float oldMouseY;
    private boolean useMousePos;
    private boolean buttonClicked;

    public GuiCosArmorInventory(ContainerCosArmor menu, Inventory playerInventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, Component.translatable("container.crafting"));
        this.titleLabelX = 97;
        this.effects = new EffectsInInventory(this);
    }

    @Override
    protected void init() {
        super.init();
        if (ModConfigs.CosArmorDisableRecipeBook.get()) {
            for (var child : this.children()) {
                if (child instanceof ImageButton btn) {
                    btn.visible = false;
                }
            }
        }
        InventoryCosArmor invCosArmor = getCosArmorInventory();
        for (int i = 0; i < 4; i++) {
            final int j = 3 - i;
            addRenderableWidget(new GuiCosArmorToggleButton(leftPos + 97 + 18 * i, topPos + 61, 5, 5,
                    Component.empty(), invCosArmor.isSkinArmor(j) ? 1 : 0, button -> {
                        InventoryCosArmor inv = getCosArmorInventory();
                        inv.setSkinArmor(j, !inv.isSkinArmor(j));
                        ((GuiCosArmorToggleButton) button).state = inv.isSkinArmor(j) ? 1 : 0;
                        ClientPacketDistributor.sendToServer(new PayloadSetSkinArmor(j, inv.isSkinArmor(j)));
                    }));
        }
        smoothTransition();
    }

    private InventoryCosArmor getCosArmorInventory() {
        return ((InventoryManager) ModObjects.invMan).getCosArmorInventoryClient(minecraft.player.getUUID());
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + 5, height / 2 - 49);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        this.effects.extractRenderState(graphics, mouseX, mouseY);
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
    }

    @Override
    public boolean showsActiveEffects() {
        return this.effects.canSeeEffects();
    }

    @Override
    protected boolean isBiggerResultSlot() {
        return false;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        int i = leftPos;
        int j = topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0, 0, imageWidth, imageHeight, 256, 256);
        if (useMousePos) {
            oldMouseX = mouseX;
            oldMouseY = mouseY;
            useMousePos = false;
        }
        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, i + 26, j + 8, i + 75, j + 78, 30,
                0.0625F, oldMouseX, oldMouseY, minecraft.player);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        if (buttonClicked) {
            buttonClicked = false;
            return true;
        }
        return super.mouseReleased(event);
    }

    private void smoothTransition() {
        Screen current = Minecraft.getInstance().gui.screen();
        if (current instanceof InventoryScreen screen) {
            oldMouseX = InventoryScreenAccess.getXMouse(screen);
            oldMouseY = InventoryScreenAccess.getYMouse(screen);
        } else if (current instanceof CreativeModeInventoryScreen) {
            useMousePos = true;
        }
    }
}
