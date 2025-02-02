package net.lixir.vminus.vision.value.status_effect;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.StatusEffectCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.effect.StatusEffect;

public class StatusEffectVisionBoolean implements VisionValue<Boolean, StatusEffect> {
    private final Boolean value;
    private final int priority;
    private final StatusEffectCondition[] conditions;
    public StatusEffectVisionBoolean(Boolean value, int priority, StatusEffectCondition[] conditions) {
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

    public Condition<StatusEffect>[] get_conditions() {
        return conditions;
    }
}
