package net.lixir.vminus.vision.direct;

import net.lixir.vminus.vision.type.EntityTypeVision;
import net.minecraft.entity.EntityType;

public interface EntityTypeVisionable {
    EntityTypeVision get_vision();
    void set_vision(EntityTypeVision entity_type_vision);
    static <T extends EntityType<?>> EntityTypeVisionable convert(T entity_type) {
        return (EntityTypeVisionable)entity_type;
    }
    static <T extends EntityType<?>> EntityTypeVision get_vision(T entity_type){
        return convert(entity_type).get_vision();
    }
    static <T extends EntityType<?>> void set_vision(T entity_type, EntityTypeVision entity_type_vision){
        convert(entity_type).set_vision(entity_type_vision);
    }
}
