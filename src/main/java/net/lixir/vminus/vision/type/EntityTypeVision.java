package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;

public class EntityTypeVision implements Vision {
    public String[] entity_types;
    public VisionElement<Boolean>[] banned;
    public EntityTypeVision(EntityTypeVision entityVision){
        this.entity_types = null;
        this.banned = entityVision.banned;
    }
    public Boolean get_banned(){
        return get_value(banned);
    }

    @Override
    public String[] get_targets() {
        return new String[0];
    }
}
