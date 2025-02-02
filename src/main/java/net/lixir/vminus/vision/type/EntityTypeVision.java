package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionBoolean;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;


public class EntityTypeVision implements Vision<EntityType<?>> {
    public String[] entity_types;
    public EntityTypeVisionBoolean[] banned;
    public EntityTypeVision(EntityTypeVision entityVision){
        this.entity_types = new String[]{};
        this.banned = entityVision.banned;
    }
    public Boolean get_banned(){
        return get_value(banned);
    }

    public String[] get_targets() {
        return refine_entries(new Vector<>(),entity_types,0,Registries.ENTITY_TYPE, RegistryKeys.ENTITY_TYPE);
    }

    public EntityType<?> get_vision_type() {
        return null;
    }
}
