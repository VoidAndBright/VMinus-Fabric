package net.lixir.vminus.vision.value.enchantment;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EnchantmentCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentVisionBoolean implements VisionValue<Boolean, Enchantment> {
    private final Boolean value;
    private final int priority;
    private final EnchantmentCondition[] conditions;
    public EnchantmentVisionBoolean(Boolean value, int priority, EnchantmentCondition[] conditions) {
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

    @Override
    public Condition<Enchantment>[] get_conditions() {
        return conditions;
    }
}
