package lixir.vminus.mixin.block;

import lixir.vminus.vision.type.BlockVision;
import lixir.vminus.vision.Visions;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockVisionMixin {
    @Unique private final Block BLOCK = (Block)(Object)this;

    @Inject(method = "getSlipperiness",at = @At(value = "HEAD"), cancellable = true)
    private void returnSlipperiness(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_slipperiness() != null) {
            cir.setReturnValue(blockVision.get_slipperiness());
        }
    }
    @Inject(method = "getVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_speed_multiplier() != null) {
            cir.setReturnValue(blockVision.get_speed_multiplier());
        }
    }
    @Inject(method = "getJumpVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_jump_multiplier() != null) {
            cir.setReturnValue(blockVision.get_jump_multiplier());
        }
    }
    @Inject(method = "getBlastResistance",at = @At(value = "HEAD"), cancellable = true)
    private void returnBlastResistance(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.get_block_vision(BLOCK);
        if (blockVision != null && blockVision.get_blast_resistance() != null) {
            cir.setReturnValue(blockVision.get_blast_resistance());
        }
    }
}
