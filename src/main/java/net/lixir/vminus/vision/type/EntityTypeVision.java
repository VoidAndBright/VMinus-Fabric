package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
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
    public EntityTypeVision(EntityTypeVision left_vision,EntityTypeVision right_vision){
        this.entity_types = new String[]{};
        this.banned = VisionHelper.collect_vision_values(left_vision.banned,right_vision.banned,EntityTypeVisionBoolean[]::new);
        this.fire_proof = VisionHelper.collect_vision_values(left_vision.fire_proof,right_vision.fire_proof,EntityTypeVisionBoolean[]::new);
        this.silent = VisionHelper.collect_vision_values(left_vision.silent,right_vision.silent,EntityTypeVisionBoolean[]::new);
        this.dampens_vibrations = VisionHelper.collect_vision_values(left_vision.dampens_vibrations,right_vision.dampens_vibrations,EntityTypeVisionBoolean[]::new);
        this.underwater_breathing = VisionHelper.collect_vision_values(left_vision.underwater_breathing,right_vision.underwater_breathing,EntityTypeVisionBoolean[]::new);
        this.water_sensitive = VisionHelper.collect_vision_values(left_vision.water_sensitive,right_vision.water_sensitive,EntityTypeVisionBoolean[]::new);
        this.exp_drop_amount = VisionHelper.collect_vision_values(left_vision.exp_drop_amount,right_vision.exp_drop_amount,EntityTypeVisionInteger[]::new);
        this.volume = VisionHelper.collect_vision_values(left_vision.volume,right_vision.volume,EntityTypeVisionFloat[]::new);
    }
    public Boolean get_banned(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,banned);
    }
    public Boolean get_fire_proof(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,fire_proof);
    }
    public Boolean get_silent(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,fire_proof);
    }
    public Boolean get_dampens_vibrations(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,fire_proof);
    }
    public Boolean get_underwater_breathing(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,underwater_breathing);
    }
    public Boolean get_water_sensitive(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,water_sensitive);
    }
    public Integer get_exp_drop_amount(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,exp_drop_amount);
    }
    public Float get_volume(EntityType<?> entity_type){
        return VisionHelper.vision_value(entity_type,volume);
    }

    public EntityType<?>[] get_entity_types(Vector<EntityType<?>> vector_enchantments,int index){
        if (index < entity_types.length){
            String entity_type_entry = entity_types[index];
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
            return get_entity_types(vector_enchantments,index+1);
        }
        else return vector_enchantments.toArray(EntityType<?>[]::new);
    }
    private static Identifier get_enchantment_identifier(RegistryEntry<EntityType<?>> registry_entry_enchantment){
        return Registries.ENTITY_TYPE.getId(registry_entry_enchantment.value());
    }
}
