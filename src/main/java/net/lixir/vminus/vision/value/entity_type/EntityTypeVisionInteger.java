package net.lixir.vminus.vision.value.entity_type;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EntityTypeCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.EntityType;

public class EntityTypeVisionInteger implements VisionValue<Integer, EntityType<?>> {
    private final Integer value;
    private final int priority;
    private final EntityTypeCondition[] conditions;
    public EntityTypeVisionInteger(Integer value, int priority, EntityTypeCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public Integer get_value() {
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
