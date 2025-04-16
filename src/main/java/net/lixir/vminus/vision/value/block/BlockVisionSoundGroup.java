package net.lixir.vminus.vision.value.block;


import net.lixir.vminus.vision.condition.BlockCondition;
import net.lixir.vminus.vision.condition.Condition;
import net.lixir.vminus.vision.properties.VisionSoundGroup;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.block.Block;

public class BlockVisionSoundGroup implements VisionValue<VisionSoundGroup,Block> {
    private final VisionSoundGroup value;
    private final int priority;
    private final BlockCondition[] conditions;
    public BlockVisionSoundGroup(VisionSoundGroup value, int priority, BlockCondition[] conditions) {
        this.value = value;
        this.priority = priority;
        this.conditions = conditions;
    }
    public VisionSoundGroup get_value() {
        return value;
    }

    public int get_priority() {
        return priority;
    }

    public Condition<Block>[] get_conditions() {
        return conditions;
    }
}
