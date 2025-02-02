package net.lixir.vminus.vision.value.entity_type;

import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EntityTypeCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.EntityType;

public class EntityTypeVisionFloat implements VisionValue<Float, EntityType<?>> {
    public final Float value;
    public final int priority;
    public final EntityTypeCondition[] conditions;

    public EntityTypeVisionFloat(Float value, int priority, EntityTypeCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }

    public Float get_value() {
        return value;
    }

    @Override
    public int get_priority() {
        return priority;
    }

    @Override
    public Condition<EntityType<?>>[] get_conditions() {
        return conditions;
    }
}
