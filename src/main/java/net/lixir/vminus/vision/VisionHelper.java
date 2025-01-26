package net.lixir.vminus.vision;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.block.AxisProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Iterator;
import java.util.function.Consumer;

public class VisionHelper {
    public static int hex(String hex) {
        if (hex.startsWith("#")) {
            return hex(hex.substring(1));
        }
        return Integer.parseInt(hex, 16);
    }
    public static Direction direction(String directionString) {
        return switch (directionString.toLowerCase()) {
            case "up" -> Direction.UP;
            case "down" -> Direction.DOWN;
            case "north" -> Direction.NORTH;
            case "south" -> Direction.SOUTH;
            case "west" -> Direction.WEST;
            case "east" -> Direction.EAST;
            default -> null;
        };
    }
    public static BlockState new_direction(BlockState blockState, Direction direction) {
        Property<?> facing_property = blockState.getBlock().getStateManager().getProperty("facing");
        if (facing_property instanceof DirectionProperty directionProperty && directionProperty.getValues().contains(direction))
            return blockState.with(directionProperty, direction);
        Property<?> axis_property = blockState.getBlock().getStateManager().getProperty("axis");
        if (axis_property instanceof EnumProperty<?> enum_property && enum_property.getValues().contains(direction.getAxis())) {
            EnumProperty<Direction.Axis> direction_axis_property = (AxisProperty) enum_property;
            return blockState.with(direction_axis_property, direction.getAxis());
        }
        return null;
    }
    public static Item item(String string){
        return Registries.ITEM.get(new Identifier(string));
    }
    public static ItemStack itemstack(String string,int count){
        return new ItemStack(Registries.ITEM.get(new Identifier(string)),count);
    }
    public static StatusEffectCategory category(String category) {
        return switch (category.toLowerCase()) {
            case "harmful" -> StatusEffectCategory.HARMFUL;
            case "beneficial" -> StatusEffectCategory.BENEFICIAL;
            case "neutral" -> StatusEffectCategory.NEUTRAL;
            default -> {
                VMinus.LOGGER.warn("Unknown Mob Effect Category: {}", category);
                yield null;
            }
        };
    }

    public static Block block(String string) {
        return Registries.BLOCK.get(new Identifier(string));
    }
}
