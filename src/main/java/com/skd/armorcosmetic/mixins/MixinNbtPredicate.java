package com.skd.armorcosmetic.mixins;

import com.skd.armorcosmetic.impl.InventoryManager;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.client.PlayerRenderHandler;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import net.minecraft.advancements.predicates.NbtPredicate;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Patches the entity NBT used by EMF/ETF expressions so that cosmetic armor is visible to them.
 * Those mods read equipment through this predicate, which runs outside the render swap window.
 */
@Mixin(NbtPredicate.class)
public abstract class MixinNbtPredicate {

    @Inject(method = "getEntityTagToCompare(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/nbt/CompoundTag;",
            at = @At("RETURN"))
    private static void CosArmor_onGetEntityTagToCompare(Entity entity, CallbackInfoReturnable<CompoundTag> cir) {
        if (!(entity instanceof AbstractClientPlayer player)) {
            return;
        }
        if (PlayerRenderHandler.Disabled) {
            return;
        }

        CompoundTag tag = cir.getReturnValue();
        if (tag == null) {
            return;
        }

        try {
            InventoryCosArmor cosInventory = (InventoryCosArmor) ((InventoryManager) ModObjects.invMan)
                    .getCosArmorInventoryClient(player.getUUID());

            CompoundTag equipment = tag.getCompound("equipment").orElse(new CompoundTag());
            tag.put("equipment", equipment);

            EquipmentSlot[] slots = new EquipmentSlot[]{
                    EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD
            };

            for (int i = 0; i < slots.length; i++) {
                EquipmentSlot slot = slots[i];
                if (cosInventory.isSkinArmor(i)) {
                    equipment.remove(slot.getName());
                    continue;
                }

                ItemStack stack = cosInventory.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    equipment.put(slot.getName(), CosArmor_encodeStack(player, stack));
                }
            }
        } catch (Throwable t) {
            ModObjects.logger.error("Failed to apply cosmetic armor to entity NBT for {}", player.getUUID(), t);
        }
    }

    private static Tag CosArmor_encodeStack(AbstractClientPlayer player, ItemStack stack) {
        try {
            return ItemStack.CODEC
                    .encodeStart(player.level().registryAccess().createSerializationContext(NbtOps.INSTANCE), stack)
                    .getOrThrow();
        } catch (Throwable t) {
            CompoundTag fallback = new CompoundTag();
            var id = BuiltInRegistries.ITEM.getKey(stack.getItem());
            if (id != null) {
                fallback.putString("id", id.toString());
            }
            fallback.putInt("count", stack.getCount());
            return fallback;
        }
    }
}
