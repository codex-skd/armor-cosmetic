package com.skd.armorcosmetic.impl.network.payload;

import java.util.UUID;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadSyncHiddenFlags(UUID uuid, String modid, String identifier, boolean hidden) implements CustomPacketPayload {

    public static final Type<PayloadSyncHiddenFlags> TYPE = new Type<>(ArmorCosmetic.id("sync_hidden_flags"));
    public static final StreamCodec<FriendlyByteBuf, PayloadSyncHiddenFlags> STREAM_CODEC = StreamCodec.ofMember(
            (payload, buf) -> PayloadSyncHiddenFlags.encode(buf, payload),
            PayloadSyncHiddenFlags::decode
    );

    public PayloadSyncHiddenFlags(UUID uuid, InventoryCosArmor inventory, String modid, String identifier) {
        this(uuid, modid, identifier, inventory.isHidden(modid, identifier));
    }

    private static PayloadSyncHiddenFlags decode(FriendlyByteBuf buf) {
        return new PayloadSyncHiddenFlags(buf.readUUID(), buf.readUtf(), buf.readUtf(), buf.readBoolean());
    }

    private static void encode(FriendlyByteBuf buf, PayloadSyncHiddenFlags payload) {
        buf.writeUUID(payload.uuid);
        buf.writeUtf(payload.modid);
        buf.writeUtf(payload.identifier);
        buf.writeBoolean(payload.hidden);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
