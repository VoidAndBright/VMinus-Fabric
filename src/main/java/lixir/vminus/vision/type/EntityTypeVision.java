package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class EntityTypeVision {
    public String[] entity_types;
    public VisionElement<Boolean>[] banned;
    public EntityTypeVision(EntityTypeVision entityVision){
        this.entity_types = null;
        this.banned = entityVision.banned;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
}
