package net.lixir.vminus.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MiningToolItem.class)
public interface MiningToolItemAccessor {
    @Accessor("effectiveBlocks")
    TagKey<Block> get_effective_blocks();

    @Accessor("miningSpeed")
    float get_mining_speed();
}