package net.lixir.vminus.mixin;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffect.class)
public class StatusEffectMixin {
    @Unique
    private final StatusEffect This = (StatusEffect)(Object)this;
    @Inject(method = "getColor",at = @At("HEAD"), cancellable = true)
    private void returnColour(CallbackInfoReturnable<Integer> cir){
        StatusEffectVision status_effect_vision = Visions.get_status_effect_vision(This);
        if(status_effect_vision != null && status_effect_vision.get_color(This) != null)
            cir.setReturnValue(VisionHelper.hex(status_effect_vision.get_color(This)));
    }
    @Inject(method = "getCategory",at = @At("HEAD"),cancellable = true)
    private void returnCategory(CallbackInfoReturnable<StatusEffectCategory> cir) {
        StatusEffectVision status_effect_vision =Visions.get_status_effect_vision(This);
        if(status_effect_vision != null && status_effect_vision.get_category(This) != null)
            cir.setReturnValue(VisionHelper.category(status_effect_vision.get_category(This)));
    }
}
