package net.lixir.vminus.vision.value.entity_type;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EntityTypeCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class EntityTypeVisionBoolean implements VisionValue<Boolean, EntityType<? extends Entity>> {
    private final Boolean value;
    private final int priority;
    private final EntityTypeCondition[] conditions;
    public EntityTypeVisionBoolean(Boolean value, int priority, EntityTypeCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public Boolean get_value() {
        return value;
    }

    @Override
    public int get_priority() {
        return priority;
    }

    public Condition<EntityType<? extends Entity>>[] get_conditions() {
        return conditions;
    }
}
