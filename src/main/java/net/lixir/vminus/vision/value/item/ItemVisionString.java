package net.lixir.vminus.vision.value.item;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.item.Item;

public class ItemVisionString implements VisionValue<String, Item> {
    private final String value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionString(String value, int priority, ItemCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public String get_value() {
        return value;
    }

    public int get_priority() {
        return priority;
    }

    public Condition<Item>[] get_conditions() {
        return conditions;
    }
}
