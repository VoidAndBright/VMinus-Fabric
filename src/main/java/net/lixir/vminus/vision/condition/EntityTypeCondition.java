package net.lixir.vminus.vision.condition;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;

public class EntityTypeCondition implements Condition<EntityType<?>>{
    public String is_mod_loaded;
    public boolean inverted;

    public EntityTypeCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_false(EntityType<?> entity_type) {
        if (FabricLoader.getInstance().isModLoaded(is_mod_loaded)) return !inverted;
        return true;
    }
}
