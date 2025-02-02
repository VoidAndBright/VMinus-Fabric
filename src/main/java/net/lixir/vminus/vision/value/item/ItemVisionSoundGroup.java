package net.lixir.vminus.vision.value.item;


import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.value.VisionValue;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.minecraft.item.Item;

public class ItemVisionSoundGroup implements VisionValue<SoundGroup, Item> {
    private final SoundGroup value;
    private final int priority;
    private final ItemCondition[] conditions;
    public ItemVisionSoundGroup(SoundGroup value, int priority, ItemCondition[] conditions) {
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
    public Condition<Item>[] get_conditions() {
        return conditions;
    }

}
