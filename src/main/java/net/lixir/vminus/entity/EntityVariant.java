package net.lixir.vminus.entity;

import net.minecraft.entity.Entity;

public interface EntityVariant {
    void set_variant(String new_variant);
    String get_variant();
    static <T extends Entity> EntityVariant convert(T entity){
        return (EntityVariant)entity;
    }
    static <T extends Entity> void set_variant(T entity, String new_variant){
        convert(entity).set_variant(new_variant);
    }
    static <T extends Entity> String get_variant(T entity){
        return convert(entity).get_variant();
    }
}
