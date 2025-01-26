package net.lixir.vminus.mixin.block;

import net.lixir.vminus.vision.type.BlockVision;
import net.lixir.vminus.vision.Visions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Unique private final Block BLOCK = (Block)(Object)this;

    @Inject(method = "getSlipperiness",at = @At("HEAD"), cancellable = true)
    private void returnSlipperiness(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_slipperiness() != null) {
            cir.setReturnValue(blockVision.get_slipperiness());
        }
    }
    @Inject(method = "getVelocityMultiplier",at = @At("HEAD"), cancellable = true)
    private void returnVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_speed_multiplier() != null) {
            cir.setReturnValue(blockVision.get_speed_multiplier());
        }
    }
    @Inject(method = "getJumpVelocityMultiplier",at = @At("HEAD"), cancellable = true)
    private void returnJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_jump_multiplier() != null) {
            cir.setReturnValue(blockVision.get_jump_multiplier());
        }
    }
    @Inject(method = "getBlastResistance",at = @At("HEAD"), cancellable = true)
    private void returnBlastResistance(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_blast_resistance() != null) {
            cir.setReturnValue(blockVision.get_blast_resistance());
        }
    }
    @Inject(method = "getSoundGroup",at = @At("HEAD") , cancellable = true)
    private void returnBlockSoundGroup(BlockState state, CallbackInfoReturnable<BlockSoundGroup> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_sound_group() != null) {
            cir.setReturnValue(blockVision.get_sound_group().to_block_sound_group());
        }
    }
}
