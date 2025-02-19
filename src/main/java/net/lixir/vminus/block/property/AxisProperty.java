package net.lixir.vminus.block.property;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;

import java.util.Collection;

public class AxisProperty extends EnumProperty<Direction.Axis> {
    protected AxisProperty(String name, Class<Direction.Axis> type, Collection<Direction.Axis> values) {
        super(name, type, values);
    }
}
