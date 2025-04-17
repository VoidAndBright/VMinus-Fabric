package net.lixir.vminus.vision.direct;

import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.effect.StatusEffect;

public interface StatusEffectVisionable {
    StatusEffectVision get_vision();
    void set_vision(StatusEffectVision status_effect_vision);
    static <T extends StatusEffect> StatusEffectVisionable convert(T status_effect) {
        return (StatusEffectVisionable)status_effect;
    }
    static <T extends StatusEffect> StatusEffectVision get_vision(T status_effect){
        return convert(status_effect).get_vision();
    }
    static <T extends StatusEffect> void set_vision(T status_effect, StatusEffectVision status_effect_vision){
        convert(status_effect).set_vision(status_effect_vision);
    }
}
