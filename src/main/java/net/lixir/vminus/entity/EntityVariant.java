package net.lixir.vminus.entity;

import net.minecraft.entity.Entity;

public interface EntityVariant {
    void vminus$set_variant(String new_variant);
    String vminus$get_variant();
    static <T extends Entity> void set_variant(T entity, String new_variant){
        if (entity instanceof EntityVariant entity_variant)
            entity_variant.vminus$set_variant(new_variant);

    }
    static <T extends Entity> String get_variant(T entity){
        if (entity instanceof EntityVariant entity_variant)
            return entity_variant.vminus$get_variant();
        return null;
    }
}
