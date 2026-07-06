package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class GuiCosArmorInventory extends AbstractRecipeBookScreen<ContainerCosArmor> {

    public static final Identifier TEXTURE = ArmorCosmetic.id("textures/gui/cosarmorinventory.png");
    private static final Identifier ICON_TOGGLE_ON = ArmorCosmetic.id("textures/gui/icon_toggle_on.png");
    private static final Identifier ICON_TOGGLE_OFF = ArmorCosmetic.id("textures/gui/icon_toggle_off.png");

    private final EffectsInInventory effects;
    public float oldMouseX;
    public float oldMouseY;
    private boolean useMousePos;
    private boolean buttonClicked;

    public GuiCosArmorInventory(ContainerCosArmor menu, Inventory playerInventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, title);
        this.effects = new EffectsInInventory(this);
        this.titleLabelX = 97;
    }

    @Override
    protected void init() {
        super.init();
        if (menu instanceof ContainerCosArmor) {
            for (int i = 0; i < 4; i++) {
                int slot = i;
                addRenderableWidget(new GuiCosArmorToggleButton(
                        leftPos + com.skd.armorcosmetic.impl.ModConfigs.SkinArmorToggle_Left.get() + i * com.skd.armorcosmetic.impl.ModConfigs.SkinArmorToggle_Spacing.get(),
                        topPos + com.skd.armorcosmetic.impl.ModConfigs.SkinArmorToggle_Top.get(),
                        com.skd.armorcosmetic.impl.ModConfigs.SkinArmorToggle_Width.get(),
                        com.skd.armorcosmetic.impl.ModConfigs.SkinArmorToggle_Height.get(),
                        Component.empty(),
                        menu.getSlot(49 - slot).getItem().isEmpty() ? 0 : 1,
                        ICON_TOGGLE_ON, ICON_TOGGLE_OFF,
                        btn -> {
                            if (btn instanceof GuiCosArmorToggleButton toggleBtn) {
                                toggleBtn.state = toggleBtn.state == 1 ? 0 : 1;
                            }
                        }));
            }
        }
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + 133, topPos + 62);
    }

    @Override
    protected void onRecipeBookButtonClick() {
        this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        if (useMousePos) {
            oldMouseX = mouseX;
            oldMouseY = mouseY;
        }
    }

    @Override
    public boolean showsActiveEffects() {
        return true;
    }

    @Override
    protected boolean isBiggerResultSlot() {
        return true;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int x = leftPos;
        int y = topPos;
        graphics.fill(0, 0, width, height, 0xC0101010);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);
        effects.extractRenderState(graphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        buttonClicked = false;
        return super.mouseReleased(event);
    }
}
