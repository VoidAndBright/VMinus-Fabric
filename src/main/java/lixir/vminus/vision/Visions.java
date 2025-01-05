package lixir.vminus.vision;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Visions {
    public static final CopyOnWriteArrayList<BlockVision> RESOURCE_BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<ItemVision> RESOURCE_ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Block, BlockVision> ITEM_VISIONS = new HashMap<>();
    private static void processBlockVisions(){
        for (BlockVision blockVision : RESOURCE_BLOCK_VISIONS){
            for (String string : blockVision.blocks) {
                if (string.startsWith("#")) {
                    String tag = string.substring(1);
                    TagKey<Block> blockTagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(tag));
                    Registries.BLOCK.iterateEntries(blockTagKey).forEach(blockRegistryEntry -> {
                        Visions.BLOCK_VISIONS.put(blockRegistryEntry.value(), new BlockVision(blockVision));
                        VMinus.LOGGER.info("{}", blockRegistryEntry.value().getTranslationKey());
                    });
                }
            }
        }
    }
    private static void processBlockVision(CopyOnWriteArrayList<ItemVision> itemVisions,int index){
        if (index < itemVisions.size()) {
            ItemVision itemVision = itemVisions.get(index);
            processBlockVision(itemVisions,index + 1);
        }
    }
    private static void processTag(String string){

    }
    private static void processBlock(String string){

    }

    public static BlockVision getBlockVision(Block block){
        if (BLOCK_VISIONS.isEmpty()){
            processBlockVisions();
            return null;
        }
        return BLOCK_VISIONS.get(block);
    }
    public static void clear() {
        BLOCK_VISIONS.clear();
        RESOURCE_BLOCK_VISIONS.clear();
        RESOURCE_ITEM_VISIONS.clear();
        ITEM_VISIONS.clear();
    }
    //Hey lixier if your reading this just know that I have a demo
}