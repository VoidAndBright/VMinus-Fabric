package net.lixir.vminus.vision.value.block;


import net.lixir.vminus.vision.condition.BlockCondition;
import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.block.Block;

public class BlockVisionBoolean implements VisionValue<Boolean, Block> {
    private final Boolean value;
    private final int priority;
    private final BlockCondition[] conditions;
    BlockVisionBoolean(Boolean value, int priority, BlockCondition[] conditions){
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

    public Condition<Block>[] get_conditions() {
        return conditions;
    }
}
