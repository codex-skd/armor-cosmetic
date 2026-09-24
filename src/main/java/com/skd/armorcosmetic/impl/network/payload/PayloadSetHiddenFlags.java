package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadSetHiddenFlags(String modid, String identifier, boolean hidden) implements CustomPacketPayload {

    public static final Type<PayloadSetHiddenFlags> TYPE = new Type<>(ArmorCosmetic.id("set_hidden_flags"));
    public static final StreamCodec<FriendlyByteBuf, PayloadSetHiddenFlags> STREAM_CODEC = StreamCodec.ofMember(
            (payload, buf) -> PayloadSetHiddenFlags.encode(buf, payload),
            PayloadSetHiddenFlags::decode
    );

    private static PayloadSetHiddenFlags decode(FriendlyByteBuf buf) {
        return new PayloadSetHiddenFlags(buf.readUtf(), buf.readUtf(), buf.readBoolean());
    }

    private static void encode(FriendlyByteBuf buf, PayloadSetHiddenFlags payload) {
        buf.writeUtf(payload.modid);
        buf.writeUtf(payload.identifier);
        buf.writeBoolean(payload.hidden);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
