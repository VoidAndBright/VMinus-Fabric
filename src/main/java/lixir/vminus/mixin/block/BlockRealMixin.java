package lixir.vminus.mixin.block;

import lixir.vminus.vision.visions.BlockVision;
import lixir.vminus.vision.visions.Visions;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockRealMixin {
    @Unique @Final private Block block = (Block)(Object)this;
    @Inject(method = "getSlipperiness",at = @At(value = "HEAD"), cancellable = true)
    private void returnSlipperiness(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(block);
        if (blockVision != null && blockVision.getSlipperiness() > 0){
            cir.setReturnValue(blockVision.getSlipperiness());
        }
    }
    @Inject(method = "getVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(block);
        if (blockVision != null && blockVision.getVelocityFactor() > 0){
            cir.setReturnValue(blockVision.getVelocityFactor());
        }
    }
    @Inject(method = "getJumpVelocityMultiplier",at = @At(value = "HEAD"), cancellable = true)
    private void returnJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(block);
        if (blockVision != null){
            cir.setReturnValue(blockVision.getJumpVelocityFactor());
        }
    }
    @Inject(method = "getBlastResistance",at = @At(value = "HEAD"), cancellable = true)
    private void returnBlastResistance(CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(block);
        if (blockVision != null && blockVision.getJumpVelocityFactor() > 0){
            cir.setReturnValue(blockVision.getJumpVelocityFactor());
        }
    }
}
