package net.lixir.vminus.vision.value.block;


import net.lixir.vminus.vision.condition.BlockCondition;
import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.block.Block;

public class BlockVisionInteger implements VisionValue<Integer, Block> {
    Integer value;
    int priority;
    BlockCondition[] conditions;
    BlockVisionInteger(Integer value, int priority, BlockCondition[] conditions){
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public Integer get_value() {
        return value;
    }

    public int get_priority() {
        return priority;
    }

    public Condition<Block>[] get_conditions() {
        return conditions;
    }
}
