package lixir.vminus.vision;

import lixir.vminus.vision.type.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
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
    public static final CopyOnWriteArrayList<EnchantmentVision> RESOURCE_ENCHANTMENT_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EntityTypeVision> RESOURCE_ENTITY_TYPE_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<ItemVision> RESOURCE_ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<StatusEffectVision> RESOURCE_STATUS_EFFECT_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Enchantment, EnchantmentVision> ENCHANTMENT_VISIONS = new HashMap<>();
    public static final Map<EntityType<?>, EntityTypeVision> ENTITY_TYPE_VISIONS = new HashMap<>();
    public static final Map<Item, ItemVision> ITEM_VISIONS = new HashMap<>();
    public static final Map<StatusEffect, StatusEffectVision> STATUS_EFFECT_VISIONS = new HashMap<>();

    private static void add_block_visions(){
        iterate_block_visions(0);
    }
    private static void iterate_block_visions(int index){
        if (index < RESOURCE_BLOCK_VISIONS.size()) {
            BlockVision itemVision = RESOURCE_BLOCK_VISIONS.get(index);
            add_blocks(itemVision);
            RESOURCE_BLOCK_VISIONS.clear();
            iterate_block_visions(index + 1);
        }
    }
    private static void add_blocks(BlockVision blockVision){
        iterate_block_vision(blockVision,0);
    }
    private static void iterate_block_vision(BlockVision blockVision, int index){
        if (index < blockVision.blocks.length) {
            String string = blockVision.blocks[index];
            if (string.startsWith("#")) add_block_tag(string.substring(1),blockVision);
            else add_block(string,blockVision);
            iterate_block_vision(blockVision,index + 1);
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


    private static void add_item_visions(){
        iterate_item_visions(0);
    }
    private static void iterate_item_visions(int index){
        if (index < RESOURCE_ITEM_VISIONS.size()) {
            ItemVision itemVision = RESOURCE_ITEM_VISIONS.get(index);
            add_items(itemVision);
            iterate_item_visions(index + 1);
        }
    }
    private static void add_items(ItemVision itemVision){
        iterate_item_vision(itemVision,0);
    }
    private static void iterate_item_vision(ItemVision itemVision, int index){
        if (index < itemVision.items.length) {
            String string = itemVision.items[index];
            if (string.startsWith("#")) add_item_tag(string.substring(1),itemVision);
            else add_item(string,itemVision);
            iterate_item_vision(itemVision,index + 1);
        }
    }
    private static void add_item_tag(String string, ItemVision itemVision){
        TagKey<Item> itemTagKey = TagKey.of(RegistryKeys.ITEM, Identifier.tryParse(string));
        Registries.ITEM.iterateEntries(itemTagKey).forEach(
        itemRegistryEntry ->
            Visions.ITEM_VISIONS.put(itemRegistryEntry.value(), new ItemVision(itemVision))
        );
    }
    private static void add_item(String string, ItemVision itemVision){
        Item block = Registries.ITEM.get(Identifier.tryParse(string));
        Visions.ITEM_VISIONS.put(block, new ItemVision(itemVision));
    }

    private static void add_entity_type_visions(){
        iterate_entity_type_visions(0);
    }
    private static void iterate_entity_type_visions(int index){
        if (index < RESOURCE_ENTITY_TYPE_VISIONS.size()) {
            EntityTypeVision itemVision = RESOURCE_ENTITY_TYPE_VISIONS.get(index);
            add_entity_types(itemVision);
            iterate_entity_type_visions(index + 1);
        }
    }
    private static void add_entity_types(EntityTypeVision entityVision){
        iterate_entity_type_vision(entityVision,0);
    }
    private static void iterate_entity_type_vision(EntityTypeVision entityVision, int index){
        if (index < entityVision.entity_types.length) {
            String string = entityVision.entity_types[index];
            if (string.startsWith("#")) add_entity_type_tag(string.substring(1),entityVision);
            else if (!string.startsWith("!")) add_entity_type(string,entityVision);
            iterate_entity_type_vision(entityVision,index + 1);
        }
    }
    private static void add_entity_type(String string, EntityTypeVision entityVision){
        EntityType<?> entityType = Registries.ENTITY_TYPE.get(Identifier.tryParse(string));
        Visions.ENTITY_TYPE_VISIONS.put(entityType, new EntityTypeVision(entityVision));
    }
    private static void add_entity_type_tag(String string, EntityTypeVision entityVision){
        TagKey<EntityType<?>> entityTypeTagKey = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.tryParse(string));
        Registries.ENTITY_TYPE.iterateEntries(entityTypeTagKey).forEach(
                itemRegistryEntry ->
                        Visions.ENTITY_TYPE_VISIONS.put(itemRegistryEntry.value(), new EntityTypeVision(entityVision))
        );
    }

    public static BlockVision get_block_vision(Block block){
        if (BLOCK_VISIONS.isEmpty()) {
            add_block_visions();
            RESOURCE_BLOCK_VISIONS.clear();
        }
        return BLOCK_VISIONS.get(block);
    }

    public static EntityTypeVision get_entity_type_vision(EntityType<?> item){
        if (ENTITY_TYPE_VISIONS.isEmpty()) {
            add_entity_type_visions();
            RESOURCE_ITEM_VISIONS.clear();
        }
        return ENTITY_TYPE_VISIONS.get(item);
    }

    public static ItemVision get_item_vision(Item item){
        if (ITEM_VISIONS.isEmpty()) {
            add_item_visions();
            RESOURCE_ITEM_VISIONS.clear();
        }
        return ITEM_VISIONS.get(item);
    }
    public static void clear(){
        RESOURCE_BLOCK_VISIONS.clear();
        RESOURCE_ENCHANTMENT_VISIONS.clear();
        RESOURCE_ENTITY_TYPE_VISIONS.clear();
        RESOURCE_ITEM_VISIONS.clear();
        RESOURCE_STATUS_EFFECT_VISIONS.clear();

        BLOCK_VISIONS.clear();
        ENCHANTMENT_VISIONS.clear();
        ENTITY_TYPE_VISIONS.clear();
        ITEM_VISIONS.clear();
        STATUS_EFFECT_VISIONS.clear();
    }
    //You have already seen I like functional It Hard to have errors in it
}