package net.lixir.vminus.vision.implement;

import net.lixir.vminus.vision.type.EntityTypeVision;
import net.minecraft.entity.EntityType;

public interface EntityTypeVisionable {
    EntityTypeVision vminus$get_vision();
    void vminus$set_vision(EntityTypeVision entity_type_vision);
    static <T extends EntityType<?>> EntityTypeVision get_vision(T entity_type){
        return ((EntityTypeVisionable)entity_type).vminus$get_vision();
    }
    static <T extends EntityType<?>> void set_vision(T entity_type, EntityTypeVision entity_type_vision){
        ((EntityTypeVisionable)entity_type).vminus$set_vision(entity_type_vision);
    }
}
