package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSetSkinArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class GuiCosArmorInventory extends AbstractRecipeBookScreen<ContainerCosArmor> {

    public static final Identifier TEXTURE = ArmorCosmetic.id("textures/gui/cosarmorinventory.png");

    private final EffectsInInventory effects;
    private final boolean recipeBookDisabled;
    public float oldMouseX;
    public float oldMouseY;
    private boolean useMousePos;
    private boolean buttonClicked;
    private float lastMouseX;
    private float lastMouseY;

    public GuiCosArmorInventory(ContainerCosArmor menu, Inventory playerInventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, title);
        this.effects = new EffectsInInventory(this);
        this.titleLabelX = 97;
        this.recipeBookDisabled = ModConfigs.CosArmorDisableRecipeBook.get();
    }

    @Override
    protected void init() {
        super.init();
        if (recipeBookDisabled) {
            for (var child : this.children()) {
                if (child instanceof ImageButton btn) {
                    btn.visible = false;
                }
            }
        }
        if (menu instanceof ContainerCosArmor container) {
            InventoryCosArmor cosInv = getCosInventory(container);
            if (cosInv != null) {
                for (int i = 0; i < 4; i++) {
                    int invSlot = 3 - i;
                    boolean isSkin = cosInv.isSkinArmor(invSlot);
                    addRenderableWidget(new GuiCosArmorToggleButton(
                            leftPos + ModConfigs.SkinArmorToggle_Left.get() + i * ModConfigs.SkinArmorToggle_Spacing.get(),
                            topPos + ModConfigs.SkinArmorToggle_Top.get(),
                            ModConfigs.SkinArmorToggle_Width.get(),
                            ModConfigs.SkinArmorToggle_Height.get(),
                            Component.empty(),
                            isSkin ? 1 : 0,
                            Component.translatable("cos.gui.tooltip.skin.on"),
                            Component.translatable("cos.gui.tooltip.skin.off"),
                        btn -> {
                            if (btn instanceof GuiCosArmorToggleButton toggleBtn) {
                                toggleBtn.state = toggleBtn.state == 1 ? 0 : 1;
                                cosInv.setSkinArmor(invSlot, toggleBtn.state == 1);
                                ClientPacketDistributor.sendToServer(new PayloadSetSkinArmor(invSlot, toggleBtn.state == 1));
                            }
                        }));
                }
            }
        }
    }

    private InventoryCosArmor getCosInventory(ContainerCosArmor container) {
        for (int i = 49; i >= 46; i--) {
            if (container.getSlot(i).container instanceof InventoryCosArmor inv) {
                return inv;
            }
        }
        return null;
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + 133, topPos + 62);
    }

    @Override
    protected void onRecipeBookButtonClick() {
        if (recipeBookDisabled) return;
        this.minecraft.setScreenAndShow(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
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
        lastMouseX = mouseX;
        lastMouseY = mouseY;
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

        AbstractClientPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            renderPlayerPreview(graphics, player, lastMouseX, lastMouseY);
        }

        effects.extractRenderState(graphics, mouseX, mouseY);
    }

    private void renderPlayerPreview(GuiGraphicsExtractor graphics, AbstractClientPlayer player, float mouseX, float mouseY) {
        try {
            float centerX = leftPos + 26;
            float centerY = topPos + 75;
            float x0 = leftPos;
            float y0 = topPos;
            float x1 = leftPos + 52;
            float y1 = topPos + 120;
            int size = 30;
            float offsetY = 0.0625F;

            float xAngle = (float) Math.atan((centerX - mouseX) / 40.0F);
            float yAngle = (float) Math.atan((centerY - mouseY) / 40.0F);

            renderEntityInInventoryFollowsAngle(graphics, (int)x0, (int)y0, (int)x1, (int)y1, size, offsetY, xAngle, yAngle, player);
        } catch (Exception e) {
            com.skd.armorcosmetic.impl.ModObjects.logger.debug("Failed to render player preview: {}", e.getMessage());
        }
    }

    private void renderEntityInInventoryFollowsAngle(
            GuiGraphicsExtractor graphics, int x0, int y0, int x1, int y1, int size, float offsetY, float xAngle, float yAngle, LivingEntity entity
    ) {
        Quaternionf rotation = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf xRotation = new Quaternionf().rotateX(yAngle * 20.0F * (float) (Math.PI / 180.0));
        rotation.mul(xRotation);

        EntityRenderState renderState = extractRenderState(entity);
        if (renderState instanceof LivingEntityRenderState livingRenderState) {
            livingRenderState.bodyRot = 180.0F + xAngle * 20.0F;
            livingRenderState.yRot = xAngle * 20.0F;
            if (livingRenderState.pose != Pose.FALL_FLYING) {
                livingRenderState.xRot = -yAngle * 20.0F;
            } else {
                livingRenderState.xRot = 0.0F;
            }

            livingRenderState.boundingBoxWidth = livingRenderState.boundingBoxWidth / livingRenderState.scale;
            livingRenderState.boundingBoxHeight = livingRenderState.boundingBoxHeight / livingRenderState.scale;
            livingRenderState.scale = 1.0F;
        }

        Vector3f translation = new Vector3f(0.0F, renderState.boundingBoxHeight / 2.0F + offsetY, 0.0F);
        graphics.entity(renderState, size, translation, rotation, xRotation, x0, y0, x1, y1);
    }

    private EntityRenderState extractRenderState(LivingEntity entity) {
        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        EntityRenderer<? super LivingEntity, ?> renderer = entityRenderDispatcher.getRenderer(entity);
        EntityRenderState renderState = renderer.createRenderState(entity, 1.0F);
        renderState.shadowPieces.clear();
        renderState.outlineColor = 0;
        return renderState;
    }

    @Override
    public void removed() {
        super.removed();
        InventoryCosArmor cosInv = menu instanceof ContainerCosArmor c ? getCosInventory(c) : null;
        if (cosInv != null) {
            for (int i = 0; i < 4; i++) {
                ClientPacketDistributor.sendToServer(new PayloadSetSkinArmor(i, cosInv.isSkinArmor(i)));
            }
        }
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        buttonClicked = false;
        return super.mouseReleased(event);
    }
}
