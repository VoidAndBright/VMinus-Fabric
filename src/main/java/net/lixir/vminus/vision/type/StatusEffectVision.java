package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.VisionValue;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionBoolean;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionString;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import java.util.Vector;

public class StatusEffectVision implements Vision<StatusEffect>{
    public String[] status_effects;
    public StatusEffectVisionBoolean[] banned;
    public StatusEffectVisionString[] color;
    public StatusEffectVisionString[] category;

    public StatusEffectVision(StatusEffectVision status_effect_vision) {
        this.status_effects = new String[]{};
        this.banned = status_effect_vision.banned;
        this.color = status_effect_vision.color;
        this.category = status_effect_vision.category;
    }
    public Boolean get_banned(){
        return get_value(banned);
    }
    public String get_color(){
        return get_value(color);
    }
    public String get_category(){
        return get_value(category);
    }

    public String[] get_targets() {
        return refine_entries(new Vector<>(), status_effects, 0, Registries.STATUS_EFFECT, RegistryKeys.STATUS_EFFECT);
    }

    @Override
    public StatusEffect get_vision_type() {
        return null;
    }
}
