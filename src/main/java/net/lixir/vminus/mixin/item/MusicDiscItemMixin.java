package net.lixir.vminus.mixin.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MusicDiscItem.class)
public class MusicDiscItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void use_on_block(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos block_pos = context.getBlockPos();
        BlockState block_state = world.getBlockState(block_pos);
        if (block_state.isOf(Blocks.JUKEBOX) && !block_state.get(JukeboxBlock.HAS_RECORD)) {
            ItemStack item_stack = context.getStack();
            if (item_stack.getMaxCount() == 1)
                return;
            if (!world.isClient()) {
                PlayerEntity player = context.getPlayer();
                BlockEntity block_entity = world.getBlockEntity(block_pos);
                if (block_entity instanceof JukeboxBlockEntity jukeboxBlockEntity) {
                    ItemStack music_disc_item_stack = new ItemStack(item_stack.getItem());
                    jukeboxBlockEntity.setDisc(music_disc_item_stack);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, block_pos, GameEvent.Emitter.of(player, block_state));
                    item_stack.decrement(1);
                    if (player != null) {
                        player.incrementStat(Stats.PLAY_RECORD);
                    }
                }
            }
            cir.setReturnValue(ActionResult.success(world.isClient()));
        }
    }
}
