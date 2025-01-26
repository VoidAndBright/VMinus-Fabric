package net.lixir.vminus.mixin.world;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.BlockVision;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSection.class)
public abstract class ChunkSectionMixin {

    @Shadow public abstract BlockState setBlockState(int x, int y, int z, BlockState state);

    @Inject(method = "setBlockState(IIILnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private void setBlockState(int x, int y, int z, BlockState blockstate, CallbackInfoReturnable<BlockState> cir){
        BlockVision block_vision = Visions.get_block_vision(blockstate.getBlock());
        if (block_vision != null) {
            if (block_vision.get_replacement() != null) {
                Block replacement = VisionHelper.block(block_vision.get_replacement());
                cir.setReturnValue(setBlockState(x,y,z, replacement.getDefaultState()));
            }
            else if (block_vision.get_banned() != null && block_vision.get_banned()) cir.setReturnValue(Blocks.AIR.getDefaultState());
            else if (block_vision.get_direction() != null) {
                Direction direction = VisionHelper.direction(block_vision.get_direction());
                if (direction != null) {
                    BlockState new_blockState = VisionHelper.new_direction(blockstate, direction);
                    if (new_blockState != null && !blockstate.equals(new_blockState)) cir.setReturnValue(setBlockState(x,y,z, new_blockState));
                }
            }
        }
    }
}
