package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class EntityVision {
    public String[] entities;
    public VisionElement<Boolean>[] banned;
    public EntityVision(EntityVision entityVision){
        this.entities = null;
        this.banned = entityVision.banned;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
}
