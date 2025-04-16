package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionBoolean;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionFloat;
import net.lixir.vminus.vision.value.entity_type.EntityTypeVisionInteger;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;


public class EntityTypeVision {
    private final String[] entity_types;
    private final Vector<EntityTypeVisionBoolean> banned;
    private final Vector<EntityTypeVisionBoolean> fire_proof;
    private final Vector<EntityTypeVisionBoolean> dampens_vibrations;
    private final Vector<EntityTypeVisionBoolean> silent;
    private final Vector<EntityTypeVisionBoolean> underwater_breathing;
    private final Vector<EntityTypeVisionBoolean> water_sensitive;
    private final Vector<EntityTypeVisionInteger> exp_drop_amount;
    private final Vector<EntityTypeVisionFloat> volume;

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
    public EntityTypeVision(EntityTypeVision left_vision,EntityTypeVision right_vision){
        this.entity_types = new String[]{};
        this.banned = Vision.sum(left_vision.banned,right_vision.banned);
        this.fire_proof = Vision.sum(left_vision.fire_proof,right_vision.fire_proof);
        this.silent = Vision.sum(left_vision.silent,right_vision.silent);
        this.dampens_vibrations = Vision.sum(left_vision.dampens_vibrations,right_vision.dampens_vibrations);
        this.underwater_breathing = Vision.sum(left_vision.underwater_breathing,right_vision.underwater_breathing);
        this.water_sensitive = Vision.sum(left_vision.water_sensitive,right_vision.water_sensitive);
        this.exp_drop_amount = Vision.sum(left_vision.exp_drop_amount,right_vision.exp_drop_amount);
        this.volume = Vision.sum(left_vision.volume,right_vision.volume);
    }
    public Boolean get_banned(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,banned);
    }
    public Boolean get_fire_proof(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,fire_proof);
    }
    public Boolean get_silent(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,fire_proof);
    }
    public Boolean get_dampens_vibrations(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,fire_proof);
    }
    public Boolean get_underwater_breathing(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,underwater_breathing);
    }
    public Boolean get_water_sensitive(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,water_sensitive);
    }
    public Integer get_exp_drop_amount(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,exp_drop_amount);
    }
    public Float get_volume(EntityType<?> entity_type){
        return Vision.unwrap(entity_type,volume);
    }

    public EntityType<?>[] get_entity_types(){
        Vector<EntityType<?>> vector_enchantments = new Vector<>();
        for (String entity_type_entry:entity_types){
            if (entity_type_entry.startsWith("*")){
                vector_enchantments.addAll(Registries.ENTITY_TYPE.stream().toList());
            }
            if (entity_type_entry.startsWith("!#")) {
                String entity_type_tag = entity_type_entry.substring(2);
                TagKey<EntityType<?>> entity_type_tag_key = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(entity_type_tag));
                vector_enchantments.removeAll(Registries.ENTITY_TYPE.getOrCreateEntryList(entity_type_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (entity_type_entry.startsWith("#")) {
                String entity_type_tag = entity_type_entry.substring(1);
                TagKey<EntityType<?>> entity_type_tag_key = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(entity_type_tag));
                vector_enchantments.addAll(Registries.ENTITY_TYPE.getOrCreateEntryList(entity_type_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (entity_type_entry.startsWith("!")) vector_enchantments.remove(Registries.ENTITY_TYPE.get(new Identifier(entity_type_entry.substring(1))));
            else vector_enchantments.add(Registries.ENTITY_TYPE.get(new Identifier(entity_type_entry)));
        }
        return vector_enchantments.toArray(EntityType<?>[]::new);
    }
}
