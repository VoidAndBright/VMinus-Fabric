package net.lixir.vminus.mixin.world;

import net.lixir.vminus.block.property.AxisProperty;
import net.lixir.vminus.vision.implement.BlockVisionable;
import net.lixir.vminus.vision.type.BlockVision;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSection.class)
public abstract class ChunkSectionMixin {


    @Shadow public abstract BlockState setBlockState(int x, int y, int z, BlockState state, boolean lock);

    @Inject(method = "setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private void redirect_block_state(int x, int y, int z, BlockState blockstate, boolean lock, CallbackInfoReturnable<BlockState> cir){
        Block block = blockstate.getBlock();
        BlockVision block_vision = BlockVisionable.get_vision(block);
        if (block_vision != null) {
            if (block_vision.get_replacement(block) != null) {
                cir.setReturnValue(setBlockState(x,y,z, block_vision.get_replacement(block).getDefaultState(), lock));
            }
            else if (block_vision.get_banned(block) != null && block_vision.get_banned(block)) cir.setReturnValue(Blocks.AIR.getDefaultState());
            else if (block_vision.get_direction(block) != null) {
                Direction direction = block_vision.get_direction(block);
                if (direction != null) {
                    BlockState new_blockState = replace_direction(blockstate, direction);
                    if (new_blockState != null && !blockstate.equals(new_blockState)) cir.setReturnValue(setBlockState(x,y,z, new_blockState, lock));
                }
            }
        }
    }
    @Unique
    private static BlockState replace_direction(BlockState blockState, Direction direction) {
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
}
