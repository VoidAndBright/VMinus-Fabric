package lixir.vminus.mixin.block;

import lixir.vminus.vision.type.BlockVision;
import lixir.vminus.vision.Visions;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlockState.class)
public abstract class BlockStateVisionMixin {
    @Shadow public abstract Block getBlock();
    @Unique private final Block BLOCK = this.getBlock();
    @Inject(method = "getLuminance",at = @At(value = "HEAD"), cancellable = true)
    private void returnLuminance(CallbackInfoReturnable<Integer> cir){
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.luminance != null){
            cir.setReturnValue(Math.min(Math.max(blockVision.luminance, 0), 15));
        }
    }
    @Inject(method = "getHardness",at = @At(value = "HEAD"), cancellable = true)
    private void returnHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir){
        BlockVision blockVision = Visions.getBlockVision(BLOCK);
        if (blockVision != null && blockVision.hardness != null) {
            cir.setReturnValue(blockVision.hardness);
        }
    }
}
