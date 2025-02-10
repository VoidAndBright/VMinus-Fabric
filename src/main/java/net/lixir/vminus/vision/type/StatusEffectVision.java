package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionBoolean;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionString;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import java.util.Vector;

public class StatusEffectVision implements Vision<StatusEffect>{
    private final String[] status_effects;
    private final StatusEffectVisionBoolean[] banned;
    private final StatusEffectVisionString[] particle;
    private final StatusEffectVisionString[] color;
    private final StatusEffectVisionString[] category;

    public StatusEffectVision(StatusEffectVision status_effect_vision) {
        this.status_effects = new String[]{};
        this.banned = status_effect_vision.banned;
        this.particle = status_effect_vision.particle;
        this.color = status_effect_vision.color;
        this.category = status_effect_vision.category;
    }
    public Boolean get_banned(StatusEffect status_effect){
        return get_value(status_effect,banned);
    }
    public String get_particle(StatusEffect status_effect){
        return get_value(status_effect,particle);
    }
    public String get_color(StatusEffect status_effect){
        return get_value(status_effect,color);
    }
    public String get_category(StatusEffect status_effect){
        return get_value(status_effect,category);
    }

    public String[] get_targets() {
        return get_targets(new Vector<>(), status_effects, 0, Registries.STATUS_EFFECT, RegistryKeys.STATUS_EFFECT);
    }
}
