package net.lixir.vminus.vision.value.block;


import net.lixir.vminus.vision.condition.BlockCondition;
import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.block.Block;

public class BlockVisionString implements VisionValue<String,Block> {
    private final String value;
    private final int priority;
    private final BlockCondition[] conditions;
    public BlockVisionString(String value, int priority, BlockCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public String get_value() {
        return value;
    }

    @Override
    public int get_priority() {
        return priority;
    }

    @Override
    public Condition<Block>[] get_conditions() {
        return conditions;
    }
}
