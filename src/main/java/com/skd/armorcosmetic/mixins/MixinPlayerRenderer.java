package com.skd.armorcosmetic.mixins;

import com.skd.armorcosmetic.impl.client.PlayerRenderHandler;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public abstract class MixinPlayerRenderer {

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V",
            at = @At("TAIL"))
    private void CosArmor_onExtractRenderState(Avatar avatar, AvatarRenderState renderState, float partialTick, CallbackInfo ci) {
        if (avatar instanceof AbstractClientPlayer player) {
            PlayerRenderHandler.INSTANCE.onExtractPlayerRenderState(player, renderState, partialTick);
        }
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V",
            at = @At("RETURN"))
    private void CosArmor_onFinishRenderState(Avatar avatar, AvatarRenderState renderState, float partialTick, CallbackInfo ci) {
        if (avatar instanceof AbstractClientPlayer player) {
            PlayerRenderHandler.INSTANCE.onFinishPlayerRenderState(player, renderState, partialTick);
        }
    }
}
