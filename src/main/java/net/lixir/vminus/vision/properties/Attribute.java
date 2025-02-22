package net.lixir.vminus.vision.properties;

import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class Attribute {
    private String identifier;
    private String name;
    private String operation;
    private Float value;
    private String slot;
    private String uuid;
    public String get_identifier(){
       return identifier;
    }
    public String get_name(){
        return name;
    }
    public String get_operation(){
        return operation;
    }
    public Float get_value(){
        return value;
    }
    public String get_slot(){
        return slot;
    }
    public String get_uuid(){
        return uuid;
    }
    public EntityAttributeModifier new_thing(){

    }
}
