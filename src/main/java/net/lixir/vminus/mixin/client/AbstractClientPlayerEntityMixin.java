package net.lixir.vminus.mixin.client;

import net.lixir.vminus.cape.Cape;
import net.lixir.vminus.cape.CapeOwner;
import net.lixir.vminus.cape.CapeOwners;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {
    @Unique
    private final AbstractClientPlayerEntity client_player_entity = (AbstractClientPlayerEntity)(Object)this;
    @Inject(method = "getCapeTexture",at = @At("HEAD"), cancellable = true)
    private void redirect_cape_texture(CallbackInfoReturnable<Identifier> cir){
        UUID player_uuid = client_player_entity.getUuid();
        switch (CapeOwner.cast(client_player_entity).get_cape()) {
            case BEEPER -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.PATRONS) || CapeOwners.matches(player_uuid, CapeOwners.BOOSTERS)){
                    cir.setReturnValue(Cape.BEEPER_CAPE_TEXTURE);
                }
            }
            case GHOST -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.BOOSTERS)){
                    cir.setReturnValue(Cape.GHOST_CAPE_TEXTURE);
                }
            }
            case SHROUD -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.PATRONS)){
                    cir.setReturnValue(Cape.SHROUD_CAPE_TEXTURE);
                }
            }
            case MARROW -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.PATRONS)){
                    cir.setReturnValue(Cape.MARROW_CAPE_TEXTURE);
                }
            }
            case PROTOTYPE -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.DEVELOPERS)){
                    cir.setReturnValue(Cape.PROTOTYPE_CAPE_TEXTURE);
                }
            }
            case TROLL -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.CONTRIBUTORS)){
                    cir.setReturnValue(Cape.TROLL_CAPE_TEXTURE);
                }
            }
            case PHOTON -> {
                if(CapeOwners.matches(player_uuid, CapeOwners.PHOTON_BUILDERS)){
                    cir.setReturnValue(Cape.PHOTON_CAPE_TEXTURE);
                }
            }
            case NONE -> {}
        }
    }
}
