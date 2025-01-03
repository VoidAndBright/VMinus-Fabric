package lixir.vminus.vision.visions;

import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Visions {
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Block, BlockVision> ITEM_VISIONS = new HashMap<>();
    public static void clear() {
        BLOCK_VISIONS.clear();
        ITEM_VISIONS.clear();
    }
}