package net.lixir.vminus.vision.value.status_effect;

import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.StatusEffectCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.effect.StatusEffect;

public class StatusEffectVisionFloat implements VisionValue<Float, StatusEffect> {
    private final Float value;
    private final int priority;
    private final StatusEffectCondition[] conditions;

    public StatusEffectVisionFloat(Float value, int priority, StatusEffectCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }

    public Float get_value() {
        return value;
    }

    public int get_priority() {
        return priority;
    }

    @Override
    public Condition<StatusEffect>[] get_conditions() {
        return conditions;
    }
}
