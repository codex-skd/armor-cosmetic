package com.skd.armorcosmetic.impl.inventory;

import java.util.Map;

import com.skd.armorcosmetic.impl.ModObjects;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

public class ContainerCosArmor extends AbstractCraftingMenu {

    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    private final Player player;

    public ContainerCosArmor(int id, Inventory playerInventory, InventoryCosArmor cosInventory, Player player) {
        super(ModObjects.getTypeContainerCosArmor(), id, 2, 2);

        this.player = player;

        addResultSlot(player, 154, 28);
        addCraftingGridSlots(98, 18);

        for (int i = 0; i < 4; i++) {
            EquipmentSlot slotType = SLOT_IDS[i];
            addSlot(new Slot(playerInventory, 39 - i, 8, 8 + i * 18) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.isEmpty() || player.getEquipmentSlotForItem(stack) == slotType;
                }

                @Override
                public void setByPlayer(ItemStack oldStack, ItemStack newStack) {
                    ContainerCosArmor.this.player.onEquipItem(slotType, newStack, oldStack);
                    super.setByPlayer(oldStack, newStack);
                }

                @Override
                public boolean mayPickup(Player player) {
                    ItemStack stack = getItem();
                    if (!stack.isEmpty() && !player.isCreative()) {
                        return !stack.getEnchantments().keySet().stream()
                                .anyMatch(e -> e.value().effects().has(EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE));
                    }
                    return true;
                }
            });
        }

        addStandardInventorySlots(playerInventory, 8, 84);

        addSlot(new Slot(playerInventory, 40, 77, 62) {
            @Override
            public boolean mayPickup(Player player) {
                return false;
            }
        });

        for (int i = 0; i < 4; i++) {
            EquipmentSlot slotType = SLOT_IDS[i];
            addSlot(new Slot(cosInventory, 3 - i, 98 + i * 18, 62) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.isEmpty() || player.getEquipmentSlotForItem(stack) == slotType;
                }

                @Override
                public void setByPlayer(ItemStack oldStack, ItemStack newStack) {
                    ContainerCosArmor.this.player.onEquipItem(slotType, newStack, oldStack);
                    super.setByPlayer(oldStack, newStack);
                }

                @Override
                public boolean mayPickup(Player player) {
                    ItemStack stack = getItem();
                    if (!stack.isEmpty() && !player.isCreative()) {
                        return !stack.getEnchantments().keySet().stream()
                                .anyMatch(e -> e.value().effects().has(EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE));
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void slotsChanged(Container container) {
        if (player.level() instanceof ServerLevel serverLevel) {
            slotChangedCraftingGrid(this, serverLevel, player, craftSlots, resultSlots, null);
        }
    }

    private static void slotChangedCraftingGrid(AbstractContainerMenu menu, ServerLevel level, Player player,
            CraftingContainer crafting, ResultContainer result, RecipeHolder<CraftingRecipe> recipeHolder) {
        var input = crafting.asCraftInput();
        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack resultStack = ItemStack.EMPTY;
        var recipe = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, input, level, recipeHolder);
        if (recipe.isPresent()) {
            var holder = recipe.get();
            CraftingRecipe craftingRecipe = holder.value();
            if (result.setRecipeUsed(serverPlayer, holder)) {
                ItemStack assembled = craftingRecipe.assemble(input);
                if (assembled.isItemEnabled(level.enabledFeatures())) {
                    resultStack = assembled;
                }
            }
        }
        result.setItem(0, resultStack);
        menu.setRemoteSlot(0, resultStack);
        serverPlayer.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                menu.containerId, menu.incrementStateId(), 0, resultStack));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        resultSlots.clearContent();
        if (!player.level().isClientSide()) {
            clearContainer(player, craftSlots);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            EquipmentSlot equipSlot = player.getEquipmentSlotForItem(result);

            if (index == 0) {
                if (!moveItemStackTo(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, result);
            } else if (index >= 1 && index < 5) {
                if (!moveItemStackTo(stack, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 5 && index < 9) {
                if (!moveItemStackTo(stack, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 46 && index < 50) {
                if (!moveItemStackTo(stack, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                int armorSlot = 8 - equipSlot.getIndex();
                if (!slots.get(armorSlot).hasItem()) {
                    if (!moveItemStackTo(stack, armorSlot, armorSlot + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    int cosSlot = 49 - equipSlot.getIndex();
                    if (!slots.get(cosSlot).hasItem()) {
                        if (!moveItemStackTo(stack, cosSlot, cosSlot + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 9 && index < 36) {
                        if (!moveItemStackTo(stack, 36, 45, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 36 && index < 45) {
                        if (!moveItemStackTo(stack, 9, 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            } else if (index >= 9 && index < 36) {
                if (!moveItemStackTo(stack, 36, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 36 && index < 45) {
                if (!moveItemStackTo(stack, 9, 36, false)) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return result;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public Slot getResultSlot() {
        return slots.get(0);
    }

    @Override
    public java.util.List<Slot> getInputGridSlots() {
        return slots.subList(1, 5);
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    protected Player owner() {
        return player;
    }
}
