package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionBoolean;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionFloat;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionInteger;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionString;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;


public class EntityTypeVision implements Vision<EntityType<?>> {
    private final String[] entity_types;
    private final EntityTypeVisionBoolean[] banned;
    private final EntityTypeVisionBoolean[] fire_proof;
    private final EntityTypeVisionBoolean[] dampens_vibrations;
    private final EntityTypeVisionBoolean[] silent;
    private final EntityTypeVisionBoolean[] underwater_breathing;
    private final EntityTypeVisionBoolean[] water_sensitive;
    private final EntityTypeVisionInteger[] exp_drop_amount;
    private final EntityTypeVisionFloat[] volume;

    public EntityTypeVision(EntityTypeVision entityVision){
        this.entity_types = new String[]{};
        this.banned = entityVision.banned;
        this.fire_proof = entityVision.fire_proof;
        this.silent = entityVision.silent;
        this.dampens_vibrations = entityVision.dampens_vibrations;
        this.underwater_breathing = entityVision.underwater_breathing;
        this.water_sensitive = entityVision.water_sensitive;
        this.exp_drop_amount = entityVision.exp_drop_amount;
        this.volume = entityVision.volume;
    }
    public Boolean get_banned(EntityType<?> entity_type){
        return get_value(entity_type,banned);
    }
    public Boolean get_fire_proof(EntityType<?> entity_type){
        return get_value(entity_type,fire_proof);
    }
    public Boolean get_silent(EntityType<?> entity_type){
        return get_value(entity_type,fire_proof);
    }
    public Boolean get_dampens_vibrations(EntityType<?> entity_type){
        return get_value(entity_type,fire_proof);
    }
    public Boolean get_underwater_breathing(EntityType<?> entity_type){
        return get_value(entity_type,underwater_breathing);
    }
    public Boolean get_water_sensitive(EntityType<?> entity_type){
        return get_value(entity_type,water_sensitive);
    }
    public Integer get_exp_drop_amount(EntityType<?> entity_type){
        return get_value(entity_type,exp_drop_amount);
    }
    public Float get_volume(EntityType<?> entity_type){
        return get_value(entity_type,volume);
    }

    public String[] get_targets() {
        return get_targets(new Vector<>(),entity_types,0,Registries.ENTITY_TYPE, RegistryKeys.ENTITY_TYPE);
    }
}
