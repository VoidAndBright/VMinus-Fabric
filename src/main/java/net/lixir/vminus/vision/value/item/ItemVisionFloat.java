package net.lixir.vminus.vision.value.item;

import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.item.Item;

public class ItemVisionFloat implements VisionValue<Float, Item> {
    private final Float value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionFloat(Float value, int priority, ItemCondition[] conditions) {
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
    public Condition<Item>[] get_conditions() {
        return conditions;
    }
}
