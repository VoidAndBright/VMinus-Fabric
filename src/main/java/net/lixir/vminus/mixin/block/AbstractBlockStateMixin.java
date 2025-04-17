package net.lixir.vminus.mixin.block;

import net.lixir.vminus.vision.direct.BlockVisionable;
import net.lixir.vminus.vision.type.BlockVision;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow public abstract Block getBlock();
    @Inject(method = "getLuminance",at = @At("HEAD"), cancellable = true)
    private void returnLuminance(CallbackInfoReturnable<Integer> cir){
        BlockVision blockVision = BlockVisionable.get_vision(getBlock());
        if (blockVision != null && blockVision.get_luminance(getBlock()) != null){
            cir.setReturnValue(Math.min(Math.max(blockVision.get_luminance(getBlock()), 0), 15));
        }
    }
    @Inject(method = "getHardness",at = @At(value = "HEAD"), cancellable = true)
    private void returnHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = BlockVisionable.get_vision(getBlock());
        if (blockVision != null && blockVision.get_hardness(getBlock()) != null) {
            cir.setReturnValue(blockVision.get_hardness(getBlock()));
        }
    }
}
