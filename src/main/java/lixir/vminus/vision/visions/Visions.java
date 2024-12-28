package lixir.vminus.vision.visions;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CopyOnWriteArrayList;

public class Visions {
    public static final CopyOnWriteArrayList<BannedBlockVision> BANNED_BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<BlockVision> DATA_BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<FuelItemVision> FUEL_ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static BlockVision getBlockVision(Block block){
        for (BlockVision blockVision : DATA_BLOCK_VISIONS) {
            TagKey<Block> tagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.splitOn(blockVision.blocks,':'));
            if (block.getDefaultState().isIn(tagKey)){
                return blockVision;
            }
        }
        return null;
    }
    public static void clear(){
        BANNED_BLOCK_VISIONS.clear();
        DATA_BLOCK_VISIONS.clear();
        FUEL_ITEM_VISIONS.clear();
    }
}
