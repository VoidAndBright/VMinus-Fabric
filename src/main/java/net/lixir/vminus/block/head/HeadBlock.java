package net.lixir.vminus.block.head;

import net.lixir.vminus.block.property.VMinusProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class HeadBlock extends Block implements Equipment {
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty ON_WALL = VMinusProperties.ON_WALL;
    public final HeadShapes skull_shapes;
    public HeadBlock(HeadShapes skull_shapes, Settings settings) {
        super(settings);
        this.skull_shapes = skull_shapes;
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION,ON_WALL);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(ON_WALL) ? switch (rotation_to_direction(state.get(ROTATION))){
            case NORTH -> skull_shapes.get_on_wall_north_shape();
            case EAST -> skull_shapes.get_on_wall_east_shape();
            case SOUTH -> skull_shapes.get_on_wall_south_shape();
            case WEST -> skull_shapes.get_on_wall_west_shape();
            default -> skull_shapes.get_default_shape();
        } : skull_shapes.get_default_shape();
    }
    public BlockState getPlacementState(ItemPlacementContext context) {
        return switch (context.getSide().getOpposite()){
            case DOWN -> this.getDefaultState().with(ROTATION, RotationPropertyHelper.fromYaw(context.getPlayerYaw())).with(ON_WALL,Boolean.FALSE);
            case UP -> this.getDefaultState().with(ROTATION, RotationPropertyHelper.fromDirection(context.getHorizontalPlayerFacing().getOpposite())).with(ON_WALL,Boolean.TRUE);
            case NORTH -> this.getDefaultState().with(ROTATION, 0).with(ON_WALL, Boolean.TRUE);
            case EAST -> this.getDefaultState().with(ROTATION, 4).with(ON_WALL,Boolean.TRUE);
            case SOUTH -> this.getDefaultState().with(ROTATION, 8).with(ON_WALL,Boolean.TRUE);
            case WEST -> this.getDefaultState().with(ROTATION, 12).with(ON_WALL,Boolean.TRUE);
        };
    }
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
    public static Direction rotation_to_direction(int rotation) {
        return switch (rotation) {
            case 12, 13, 14, 15 -> Direction.WEST;
            case 8, 9, 10, 11 -> Direction.SOUTH;
            case 4, 5, 6, 7 -> Direction.EAST;
            default -> Direction.NORTH;
        };
    }
}
