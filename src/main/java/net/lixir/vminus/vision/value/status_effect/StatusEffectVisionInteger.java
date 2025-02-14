package net.lixir.vminus.vision.value.status_effect;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.StatusEffectCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.entity.effect.StatusEffect;

public class StatusEffectVisionInteger implements VisionValue<Integer, StatusEffect> {
    private final Integer value;
    private final int priority;
    private final StatusEffectCondition[] conditions;
    public StatusEffectVisionInteger(Integer value, int priority, StatusEffectCondition[] conditions) {
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
    public Condition<StatusEffect>[] get_conditions() {
        return conditions;
    }
}
