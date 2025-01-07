package lixir.vminus.vision;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
    public static final Map<Item, ItemVision> ITEM_VISIONS = new HashMap<>();
    private static void addBlockVisions(int index){
        if (index < RESOURCE_BLOCK_VISIONS.size()) {
            BlockVision itemVision = RESOURCE_BLOCK_VISIONS.get(index);
            addBlocks(itemVision,0);
            addBlockVisions(index + 1);
        }
    }
    private static void addBlocks(BlockVision blockVision, int index){
        if (index < blockVision.blocks.length) {
            String string = blockVision.blocks[index];
            if (string.startsWith("#"))
                addBlockTag(string.substring(1),blockVision);
            else addBlock(string,blockVision);
            addBlocks(blockVision,index + 1);
        }
    }
    private static void addBlockTag(String string, BlockVision blockVision){
        TagKey<Block> blockTagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(string));
        Registries.BLOCK.iterateEntries(blockTagKey).forEach(
            blockRegistryEntry ->
                    Visions.BLOCK_VISIONS.put(blockRegistryEntry.value(), new BlockVision(blockVision))
        );
    }
    private static void addBlock(String string, BlockVision blockVision){
        Block block = Registries.BLOCK.get(Identifier.tryParse(string));
        Visions.BLOCK_VISIONS.put(block, new BlockVision(blockVision));
    }
    private static void addItemVisions(int index){
        if (index < RESOURCE_ITEM_VISIONS.size()) {
            ItemVision itemVision = RESOURCE_ITEM_VISIONS.get(index);
            addItems(itemVision,0);
            addItemVisions(index + 1);
        }
    }
    private static void addItems(ItemVision blockVision, int index){
        if (index < blockVision.items.length) {
            String string = blockVision.items[index];
            if (string.startsWith("#"))
                addItemTag(string.substring(1),blockVision);
            else addItem(string,blockVision);
            addItems(blockVision,index + 1);
        }
    }
    private static void addItemTag(String string, ItemVision itemVision){
        TagKey<Item> itemTagKey = TagKey.of(RegistryKeys.ITEM, Identifier.tryParse(string));
        Registries.ITEM.iterateEntries(itemTagKey).forEach(
                itemRegistryEntry ->
                        Visions.ITEM_VISIONS.put(itemRegistryEntry.value(), new ItemVision(itemVision))
        );
    }
    private static void addItem(String string, ItemVision blockVision){
        Item block = Registries.ITEM.get(Identifier.tryParse(string));
        Visions.ITEM_VISIONS.put(block, new ItemVision(blockVision));
    }
    public static BlockVision getBlockVision(Block block){
        if (BLOCK_VISIONS.isEmpty())
            addBlockVisions(0);
        return BLOCK_VISIONS.get(block);
    }
    public static ItemVision getItemVision(Item item){
        if (ITEM_VISIONS.isEmpty())
            addItemVisions(0);
        return ITEM_VISIONS.get(item);
    }
    public static void clear() {
        RESOURCE_BLOCK_VISIONS.clear();
        RESOURCE_ITEM_VISIONS.clear();
        BLOCK_VISIONS.clear();
        ITEM_VISIONS.clear();
    }
    //Hey lixier if your reading this just know that I have a demo
}