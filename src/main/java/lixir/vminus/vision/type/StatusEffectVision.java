package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class StatusEffectVision {
    public String[] entities;
    private final VisionElement<Boolean>[] banned;

    public StatusEffectVision(VisionElement<Boolean>[] banned) {
        this.entities = null;
        this.banned = banned;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
}
