package lixir.vminus.mixin.block;

import lixir.vminus.vision.BlockVision;
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
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.slipperiness != 0) {
            cir.setReturnValue(blockVision.slipperiness);
        }
    }
    @Inject(method = "getVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.speed_multiplier != 0) {
            cir.setReturnValue(blockVision.speed_multiplier);
        }
    }
    @Inject(method = "getJumpVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.jump_multiplier != 0) {
            cir.setReturnValue(blockVision.jump_multiplier);
        }
    }
    @Inject(method = "getBlastResistance",at = @At(value = "HEAD"), cancellable = true)
    private void returnBlastResistance(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.blast_resistance != 0) {
            cir.setReturnValue(blockVision.blast_resistance);
        }
    }
}
