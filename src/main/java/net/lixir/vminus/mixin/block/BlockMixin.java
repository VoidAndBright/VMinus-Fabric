package net.lixir.vminus.mixin.block;

import net.lixir.vminus.vision.type.BlockVision;
import net.lixir.vminus.vision.accessor.BlockVisionAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin implements BlockVisionAccessor {
    @Unique
    private BlockVision block_vision;
    @Unique private final Block block = (Block)(Object)this;
    @Inject(method = "getSlipperiness",at = @At("HEAD"), cancellable = true)
    private void returnSlipperiness(CallbackInfoReturnable<Float> cir){
        if (block_vision != null && block_vision.get_slipperiness(block) != null) {
            cir.setReturnValue(block_vision.get_slipperiness(block));
        }
    }
    @Inject(method = "getVelocityMultiplier",at = @At("HEAD"), cancellable = true)
    private void returnVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        if (block_vision != null && block_vision.get_speed_multiplier(block) != null) {
            cir.setReturnValue(block_vision.get_speed_multiplier(block));
        }
    }
    @Inject(method = "getJumpVelocityMultiplier",at = @At("HEAD"), cancellable = true)
    private void returnJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        if (block_vision != null && block_vision.get_jump_multiplier(block) != null) {
            cir.setReturnValue(block_vision.get_jump_multiplier(block));
        }
    }
    @Inject(method = "getBlastResistance",at = @At("HEAD"), cancellable = true)
    private void returnBlastResistance(CallbackInfoReturnable<Float> cir){
        if (block_vision != null && block_vision.get_blast_resistance(block) != null) {
            cir.setReturnValue(block_vision.get_blast_resistance(block));
        }
    }
    @Inject(method = "getSoundGroup",at = @At("HEAD") , cancellable = true)
    private void returnBlockSoundGroup(BlockState state, CallbackInfoReturnable<BlockSoundGroup> cir){
        if (block_vision != null && block_vision.get_sound_group(block) != null) {
            cir.setReturnValue(block_vision.get_sound_group(block));
        }
    }

    public BlockVision vminus$get_vision() {
        return block_vision;
    }

    public void vminus$set_vision(BlockVision block_vision) {
        this.block_vision = block_vision;
    }
}
