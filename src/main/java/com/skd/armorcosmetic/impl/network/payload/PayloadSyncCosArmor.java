package com.skd.armorcosmetic.impl.network.payload;

import java.util.UUID;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;

public record PayloadSyncCosArmor(UUID uuid, int slot, boolean isSkinArmor, ItemStack itemCosArmor) implements CustomPacketPayload {

    public static final Type<PayloadSyncCosArmor> TYPE = new Type<>(ArmorCosmetic.id("sync_cos_armor"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PayloadSyncCosArmor> STREAM_CODEC = StreamCodec.ofMember(
            (payload, buf) -> PayloadSyncCosArmor.encode(buf, payload),
            PayloadSyncCosArmor::decode
    );

    public PayloadSyncCosArmor(UUID uuid, InventoryCosArmor inventory, int slot) {
        this(uuid, slot, inventory.isSkinArmor(slot), inventory.getStackInSlot(slot).copy());
    }

    private static PayloadSyncCosArmor decode(RegistryFriendlyByteBuf buf) {
        return new PayloadSyncCosArmor(buf.readUUID(), buf.readVarInt(), buf.readBoolean(), ItemStack.OPTIONAL_STREAM_CODEC.decode(buf));
    }

    private static void encode(RegistryFriendlyByteBuf buf, PayloadSyncCosArmor payload) {
        buf.writeUUID(payload.uuid);
        buf.writeVarInt(payload.slot);
        buf.writeBoolean(payload.isSkinArmor);
        ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, payload.itemCosArmor);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
