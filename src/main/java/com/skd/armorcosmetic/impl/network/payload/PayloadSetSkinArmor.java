package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadSetSkinArmor(int slot, boolean isSkinArmor) implements CustomPacketPayload {

    public static final Type<PayloadSetSkinArmor> TYPE = new Type<>(ArmorCosmetic.id("set_skin_armor"));
    public static final StreamCodec<FriendlyByteBuf, PayloadSetSkinArmor> STREAM_CODEC = StreamCodec.ofMember(
            (payload, buf) -> PayloadSetSkinArmor.encode(buf, payload),
            PayloadSetSkinArmor::decode
    );

    private static PayloadSetSkinArmor decode(FriendlyByteBuf buf) {
        return new PayloadSetSkinArmor(buf.readVarInt(), buf.readBoolean());
    }

    private static void encode(FriendlyByteBuf buf, PayloadSetSkinArmor payload) {
        buf.writeVarInt(payload.slot);
        buf.writeBoolean(payload.isSkinArmor);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
