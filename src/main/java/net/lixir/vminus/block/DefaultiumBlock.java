package net.lixir.vminus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DefaultiumBlock extends Block {
    public DefaultiumBlock(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos.up(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        world.setBlockState(pos.down(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        world.setBlockState(pos.east(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        world.setBlockState(pos.west(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        world.setBlockState(pos.north(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        world.setBlockState(pos.south(),VMinusBlocks.DEFAULTIUM_BLOCK.getDefaultState());
        return ActionResult.SUCCESS;
    }
}
