package net.lixir.vminus.vision.implement;

import net.lixir.vminus.vision.type.BlockVision;
import net.minecraft.block.Block;

public interface BlockVisionable {
    BlockVision vminus$get_vision();
    void vminus$set_vision(BlockVision block_vision);
    static <T extends Block> BlockVision get_vision(T block){
        return ((BlockVisionable)block).vminus$get_vision();
    }
    static <T extends Block> void set_vision(T block, BlockVision block_vision){
        ((BlockVisionable)block).vminus$set_vision(block_vision);
    }
}

