package lixir.vminus.vision.visions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Visions {
    public static final CopyOnWriteArrayList<BlockVision> BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Integer, BlockVision> BLOCK_DATA = new HashMap<>();
    public static final CopyOnWriteArrayList<BlockVision> ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Integer, BlockVision> ITEM_DATA = new HashMap<>();
    public static void clear() {
        BLOCK_DATA.clear();
        BLOCK_VISIONS.clear();
        ITEM_DATA.clear();
    }
}