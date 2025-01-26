package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;

public class StatusEffectVision implements Vision {
    public String[] entities;
    private final VisionElement<Boolean>[] banned;
    private final VisionElement<String>[] color;
    private final VisionElement<String>[] category;

    public StatusEffectVision(StatusEffectVision status_effect_vision) {
        this.entities = null;
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

    @Override
    public String[] get_targets() {
        return new String[0];
    }
}
