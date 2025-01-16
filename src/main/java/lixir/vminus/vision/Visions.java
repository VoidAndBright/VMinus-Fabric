package lixir.vminus.vision;

import lixir.vminus.vision.type.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
    public static final CopyOnWriteArrayList<EntityVision> RESOURCE_ENTITY_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EnchantmentVision> RESOURCE_ENCHANTMENT_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<ItemVision> RESOURCE_ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<StatusEffectVision> RESOURCE_STATUS_EFFECT_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Entity, EntityVision> ENTITY_VISIONS = new HashMap<>();
    public static final Map<Entity, EnchantmentVision> ENCHANTMENT_VISIONS = new HashMap<>();
    public static final Map<Item, ItemVision> ITEM_VISIONS = new HashMap<>();
    public static final Map<Entity, StatusEffectVision> STATUS_EFFECT_VISIONS = new HashMap<>();


    private static void add_block_visions(int index){
        if (index < RESOURCE_BLOCK_VISIONS.size()) {
            BlockVision itemVision = RESOURCE_BLOCK_VISIONS.get(index);
            add_blocks(itemVision,0);
            add_block_visions(index + 1);
        }
    }
    private static void add_blocks(BlockVision blockVision, int index){
        if (index < blockVision.blocks.length) {
            String string = blockVision.blocks[index];
            if (string.startsWith("#"))
                add_block_tag(string.substring(1),blockVision);
            else add_block(string,blockVision);
            add_blocks(blockVision,index + 1);
        }
    }
    private static void add_block_tag(String string, BlockVision blockVision){
        TagKey<Block> blockTagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(string));
        Registries.BLOCK.iterateEntries(blockTagKey).forEach(
        blockRegistryEntry ->
            Visions.BLOCK_VISIONS.put(blockRegistryEntry.value(), new BlockVision(blockVision))
        );
    }
    private static void add_block(String string, BlockVision blockVision){
        Block block = Registries.BLOCK.get(Identifier.tryParse(string));
        Visions.BLOCK_VISIONS.put(block, new BlockVision(blockVision));
    }


    private static void add_item_visions(int index){
        if (index < RESOURCE_ITEM_VISIONS.size()) {
            ItemVision itemVision = RESOURCE_ITEM_VISIONS.get(index);
            add_items(itemVision,0);
            add_item_visions(index + 1);
        }
    }
    private static void add_items(ItemVision blockVision, int index){
        if (index < blockVision.items.length) {
            String string = blockVision.items[index];
            if (string.startsWith("#")) add_item_tag(string.substring(1),blockVision);
            else add_item(string,blockVision);
            add_items(blockVision,index + 1);
        }
    }
    private static void add_item_tag(String string, ItemVision itemVision){
        TagKey<Item> itemTagKey = TagKey.of(RegistryKeys.ITEM, Identifier.tryParse(string));
        Registries.ITEM.iterateEntries(itemTagKey).forEach(
        itemRegistryEntry ->
            Visions.ITEM_VISIONS.put(itemRegistryEntry.value(), new ItemVision(itemVision))
        );
    }
    private static void add_item(String string, ItemVision blockVision){
        Item block = Registries.ITEM.get(Identifier.tryParse(string));
        Visions.ITEM_VISIONS.put(block, new ItemVision(blockVision));
    }


    public static BlockVision get_block_vision(Block block){
        if (BLOCK_VISIONS.isEmpty()) {
            add_block_visions(0);
            return null;
        }
        return BLOCK_VISIONS.get(block);
    }
    public static ItemVision get_item_vision(Item item){
        if (ITEM_VISIONS.isEmpty())
            add_item_visions(0);
        return ITEM_VISIONS.get(item);
    }
    public static void clear() {
        RESOURCE_BLOCK_VISIONS.clear();
        RESOURCE_ITEM_VISIONS.clear();
        BLOCK_VISIONS.clear();
        ITEM_VISIONS.clear();
    }
    //You have already seen I like functional It Hard to have errors in it
}