package lixir.vminus.vision.visions;

import lixir.vminus.VMinus;
import lixir.vminus.vision.data.BlockVisionData;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Visions {
    public static final CopyOnWriteArrayList<BlockVision> BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<String,BlockVisionData> BLOCK_VISION_DATA = new HashMap<>();
    public static final CopyOnWriteArrayList<ItemVision> ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static BlockVision getBlockVision(Block block){
        for (BlockVision blockVision : BLOCK_VISIONS) {
            for (String block_tag : blockVision.block_tags) {
                TagKey<Block> tagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.splitOn(block_tag.substring(1), ':'));
                if (block.getDefaultState().isIn(tagKey)) {
                    return blockVision;
                }
            }
        }
        return null;
    }
    public static void clear(){
        BLOCK_VISIONS.clear();
        ITEM_VISIONS.clear();
        BLOCK_VISION_DATA.put("wool",new BlockVisionData());
        VMinus.LOGGER.info("{}",BLOCK_VISION_DATA.get("wool"));
    }
}
