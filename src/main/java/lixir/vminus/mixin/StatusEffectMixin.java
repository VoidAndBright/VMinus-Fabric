package lixir.vminus.mixin;

import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(StatusEffect.class)
public class StatusEffectMixin {
    @Inject(method = "getColor",at = @At(value = "HEAD"))
    private void returnColour(CallbackInfoReturnable<Integer> cir){

    }
}
