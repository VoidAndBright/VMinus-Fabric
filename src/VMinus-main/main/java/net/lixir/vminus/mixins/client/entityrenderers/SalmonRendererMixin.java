package net.lixir.vminus.mixins.client.entityrenderers;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.Salmon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.renderer.entity.SalmonRenderer.class)
public abstract class SalmonRendererMixin {
    @Inject(method = "getTextureLocation", at = @At("HEAD"), cancellable = true)
    private void getTextureLocation(Salmon entity, CallbackInfoReturnable<Identifier> cir) {
        if (entity.getPersistentData().contains("variant")) {
            String variant = entity.getPersistentData().getString("variant");
            Identifier customTexture = new Identifier("vminus:textures/entity/variants/salmon/" + variant + ".png");
            if (customTexture != null)
                cir.setReturnValue(customTexture);
        }
    }
}