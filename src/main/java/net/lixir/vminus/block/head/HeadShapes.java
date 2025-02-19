package net.lixir.vminus.block.head;

import net.minecraft.util.shape.VoxelShape;

public interface HeadShapes {
    VoxelShape get_default_shape();
    VoxelShape get_on_wall_north_shape();
    VoxelShape get_on_wall_east_shape();
    VoxelShape get_on_wall_south_shape();
    VoxelShape get_on_wall_west_shape();
}
