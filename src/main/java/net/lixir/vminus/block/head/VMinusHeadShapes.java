package net.lixir.vminus.block.head;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public enum VMinusHeadShapes implements HeadShapes {
    DEFAULT(
            cuboid(4, 0, 4, 12, 8, 12),
            cuboid(4, 4, 0, 12, 12, 8),
            cuboid(8, 4, 4, 16, 12, 12),
            cuboid(4, 4, 8, 12, 12, 16),
            cuboid(0, 4, 4, 8, 12, 12)
            );
    public final VoxelShape default_shape;
    public final VoxelShape shape_north;
    public final VoxelShape shape_east;
    public final VoxelShape shape_south;
    public final VoxelShape shape_west;

    VMinusHeadShapes(VoxelShape default_shape, VoxelShape shape_north, VoxelShape shape_east, VoxelShape shape_south, VoxelShape shape_west) {
        this.default_shape = default_shape;
        this.shape_north = shape_north;
        this.shape_east = shape_east;
        this.shape_south = shape_south;
        this.shape_west = shape_west;
    }
    static VoxelShape cuboid(int min_x, int min_y, int min_z, int max_x, int max_y, int max_z){
        return VoxelShapes.cuboid(min_x / 16.0, min_y / 16.0, min_z / 16.0, max_x / 16.0, max_y / 16.0, max_z / 16.0);
    }
    public VoxelShape get_default_shape() {
        return default_shape;
    }

    public VoxelShape get_on_wall_north_shape() {
        return shape_north;
    }

    public VoxelShape get_on_wall_east_shape() {
        return shape_east;
    }

    public VoxelShape get_on_wall_south_shape() {
        return shape_south;
    }

    public VoxelShape get_on_wall_west_shape() {
        return shape_west;
    }
}
