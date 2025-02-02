package net.lixir.vminus.vision.value.entity_type;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EntityTypeCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.minecraft.entity.EntityType;

public class EntityTypeVisionSoundGroup implements VisionValue<SoundGroup, EntityType<?>> {
    private final SoundGroup value;
    private final int priority;
    private final EntityTypeCondition[] conditions;
    public EntityTypeVisionSoundGroup(SoundGroup value, int priority, EntityTypeCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public SoundGroup get_value() {
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
