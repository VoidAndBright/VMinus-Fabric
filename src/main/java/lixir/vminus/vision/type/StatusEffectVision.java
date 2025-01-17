package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class StatusEffectVision {
    public String[] entities;
    private final VisionElement<Boolean>[] banned;
    private final VisionElement<String>[] color;

    public StatusEffectVision(StatusEffectVision status_effect_vision) {
        this.entities = null;
        this.banned = status_effect_vision.banned;
        this.color = status_effect_vision.color;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
    public String get_color(){
        if (color == null) return null;
        return VisionElement.get_value(color);
    }
}
