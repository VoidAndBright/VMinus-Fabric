package net.lixir.vminus.vision.value.enchantment;

import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EnchantmentCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentVisionFloat implements VisionValue<Float, Enchantment> {
    private final Float value;
    private final int priority;
    private final EnchantmentCondition[] conditions;
    public EnchantmentVisionFloat(Float value, int priority, EnchantmentCondition[] conditions) {
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
    public Condition<Enchantment>[] get_conditions() {
        return conditions;
    }
}
