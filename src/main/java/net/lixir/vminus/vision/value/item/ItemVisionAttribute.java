package net.lixir.vminus.vision.value.item;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.properties.Attribute;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.item.Item;

public class ItemVisionAttribute implements VisionValue<Attribute,Item> {
    private final Attribute value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionAttribute(Attribute value, int priority, ItemCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public Attribute get_value() {
        return value;
    }

    @Override
    public int get_priority() {
        return priority;
    }

    public Condition<Item>[] get_conditions() {
        return conditions;
    }
}
