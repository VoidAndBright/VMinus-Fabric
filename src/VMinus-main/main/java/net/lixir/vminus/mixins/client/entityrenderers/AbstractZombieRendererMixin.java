package net.lixir.vminus.mixins.client.entityrenderers;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.renderer.entity.AbstractZombieRenderer.class)
public abstract class AbstractZombieRendererMixin {
    @Inject(method = "getTextureLocation", at = @At("HEAD"), cancellable = true)
    private void injectGetTextureLocation(Zombie zombie, CallbackInfoReturnable<Identifier> cir) {
        if (zombie.getPersistentData().contains("variant")) {
            String variant = zombie.getPersistentData().getString("variant");
            Identifier customTexture = new Identifier("vminus:textures/entity/variants/zombie/" + variant + ".png");
            if (customTexture != null)
                cir.setReturnValue(customTexture);
        }
    }
}
