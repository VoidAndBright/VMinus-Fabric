package net.lixir.vminus.vision.value.item;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.properties.VisionAttributeModifier;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.item.Item;

public class ItemVisionAttributes implements VisionValue<VisionAttributeModifier[],Item> {
    private final VisionAttributeModifier[] value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionAttributes(VisionAttributeModifier[] value, int priority, ItemCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public VisionAttributeModifier[] get_value() {
        return value;
    }

    public int get_priority() {
        return priority;
    }

    public Condition<Item>[] get_conditions() {
        return conditions;
    }
}
