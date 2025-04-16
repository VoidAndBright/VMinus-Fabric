package net.lixir.vminus.vision.implement;

import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.effect.StatusEffect;

public interface StatusEffectVisionable {
    StatusEffectVision vminus$get_vision();
    void vminus$set_vision(StatusEffectVision status_effect_vision);
    static <T extends StatusEffect> StatusEffectVision get_vision(T status_effect){
        return ((StatusEffectVisionable)status_effect).vminus$get_vision();
    }
    static <T extends StatusEffect> void set_vision(T status_effect, StatusEffectVision status_effect_vision){
        ((StatusEffectVisionable)status_effect).vminus$set_vision(status_effect_vision);
    }
}
