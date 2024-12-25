package net.lixir.vminus.mixins.client;

import net.lixir.vminus.helpers.CapeHelper;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(AbstractClientPlayer.class)
@OnlyIn(Dist.CLIENT)
public abstract class AbstractClientPlayerMixin {
    private static final Identifier DEFAULT_ELYTRA = new Identifier("textures/entity/elytra.png");
    @Shadow
    private PlayerInfo playerInfo;

    @Shadow
    @Nullable
    protected abstract PlayerInfo getPlayerInfo();

    @Inject(method = "getElytraTextureLocation", at = @At("HEAD"), cancellable = true)
    private void getElytraTextureLocation(CallbackInfoReturnable<Identifier> cir) {
        PlayerInfo playerInfo = this.getPlayerInfo();
        if (playerInfo != null) {
            AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
            Identifier customCapeTexture = CapeHelper.getCapeTexture(player);
            if (customCapeTexture != null) {
                cir.setReturnValue(customCapeTexture);
            }
        }
    }

    @Inject(method = "getCloakTextureLocation", at = @At("HEAD"), cancellable = true)
    private void getCloakTextureLocation(CallbackInfoReturnable<Identifier> cir) {
        PlayerInfo playerInfo = this.getPlayerInfo();
        if (playerInfo != null) {
            AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
            Identifier customCapeTexture = CapeHelper.getCapeTexture(player);
            if (customCapeTexture != null) {
                cir.setReturnValue(customCapeTexture);
            }
        }
    }
}
