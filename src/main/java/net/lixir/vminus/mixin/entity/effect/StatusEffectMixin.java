package net.lixir.vminus.mixin.entity.effect;

import net.lixir.vminus.vision.direct.StatusEffectVisionable;
import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffect.class)
public class StatusEffectMixin implements StatusEffectVisionable {
    @Unique
    private StatusEffectVision status_effect_vision;
    @Unique
    private final StatusEffect status_effect = (StatusEffect)(Object)this;
    @Inject(method = "getColor",at = @At("HEAD"), cancellable = true)
    private void returnColour(CallbackInfoReturnable<Integer> cir){
        if(status_effect_vision != null && status_effect_vision.get_color(status_effect) != null)
            cir.setReturnValue(status_effect_vision.get_color(status_effect));
    }
    @Inject(method = "getCategory",at = @At("HEAD"),cancellable = true)
    private void returnCategory(CallbackInfoReturnable<StatusEffectCategory> cir) {
        if(status_effect_vision != null && status_effect_vision.get_category(status_effect) != null)
            cir.setReturnValue(status_effect_vision.get_category(status_effect));
    }

    @Override
    public StatusEffectVision get_vision() {
        return status_effect_vision;
    }

    @Override
    public void set_vision(StatusEffectVision status_effect_vision) {
        this.status_effect_vision = status_effect_vision;
    }
}
