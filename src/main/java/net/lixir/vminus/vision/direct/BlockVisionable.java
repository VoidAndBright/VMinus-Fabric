package net.lixir.vminus.vision.direct;

import net.lixir.vminus.vision.type.BlockVision;
import net.minecraft.block.Block;

public interface BlockVisionable {
    BlockVision get_vision();
    void set_vision(BlockVision block_vision);
    static <T extends Block> BlockVisionable convert(T block) {
        return (BlockVisionable)block;
    }
    static <T extends Block> BlockVision get_vision(T block){
        return convert(block).get_vision();
    }
    static <T extends Block> void set_vision(T block, BlockVision block_vision){
        convert(block).set_vision(block_vision);
    }
}

