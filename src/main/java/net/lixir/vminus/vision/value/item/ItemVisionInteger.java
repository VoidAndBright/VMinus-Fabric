package net.lixir.vminus.vision.value.item;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.item.Item;

public class ItemVisionInteger implements VisionValue<Integer, Item> {
    private final Integer value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionInteger(Integer value, int priority, ItemCondition[] conditions) {
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
    public Condition<Item>[] get_conditions() {
        return conditions;
    }
}
