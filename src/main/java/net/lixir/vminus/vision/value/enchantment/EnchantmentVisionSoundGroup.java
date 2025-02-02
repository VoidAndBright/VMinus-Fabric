package net.lixir.vminus.vision.value.enchantment;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.EnchantmentCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentVisionSoundGroup implements VisionValue<SoundGroup, Enchantment> {
    private final SoundGroup value;
    private final int priority;
    private final EnchantmentCondition[] conditions;
    public EnchantmentVisionSoundGroup(SoundGroup value, int priority, EnchantmentCondition[] conditions) {
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
    public Condition<Enchantment>[] get_conditions() {
        return conditions;
    }
}
