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
    public static final CopyOnWriteArrayList<BlockVision> BLOCK_VISION_JSONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EnchantmentVision> ENCHANTMENT_VISION_JSONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EntityTypeVision> ENTITY_TYPE_VISION_JSONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<ItemVision> ITEM_VISION_JSONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<StatusEffectVision> STATUS_EFFECT_VISION_JSONS = new CopyOnWriteArrayList<>();
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Enchantment, EnchantmentVision> ENCHANTMENT_VISIONS = new HashMap<>();
    public static final Map<EntityType<?>, EntityTypeVision> ENTITY_TYPE_VISIONS = new HashMap<>();
    public static final Map<Item, ItemVision> ITEM_VISIONS = new HashMap<>();
    public static final Map<StatusEffect, StatusEffectVision> STATUS_EFFECT_VISIONS = new HashMap<>();

    private static void add_block_visions(){
        iterate_block_visions(0);
    }
    private static void iterate_block_visions(int index){
        if (index < BLOCK_VISION_JSONS.size()) {
            BlockVision itemVision = BLOCK_VISION_JSONS.get(index);
            add_blocks(itemVision);
            BLOCK_VISION_JSONS.clear();
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


    private static void add_enchantment_visions(){
        iterate_enchantment_visions(0);
    }
    private static void iterate_enchantment_visions(int index){
        if (index < ENCHANTMENT_VISION_JSONS.size()) {
            EnchantmentVision entityTypeVision = ENCHANTMENT_VISION_JSONS.get(index);
            add_enchantments(entityTypeVision);
            iterate_enchantment_visions(index + 1);
        }
    }
    private static void add_enchantments(EnchantmentVision enchantmentVision){
        iterate_enchantment_vision(enchantmentVision,0);
    }
    private static void iterate_enchantment_vision(EnchantmentVision enchantmentVision, int index){
        if (index < enchantmentVision.enchantments.length) {
            String string = enchantmentVision.enchantments[index];
            if (string.startsWith("#")) add_enchantment_tag(string.substring(1),enchantmentVision);
            else if (!string.startsWith("!")) add_enchantment(string,enchantmentVision);
            iterate_enchantment_vision(enchantmentVision,index + 1);
        }
    }
    private static void add_enchantment(String string, EnchantmentVision enchantmentVision){
        Enchantment entityType = Registries.ENCHANTMENT.get(Identifier.tryParse(string));
        Visions.ENCHANTMENT_VISIONS.put(entityType, new EnchantmentVision(enchantmentVision));
    }
    private static void add_enchantment_tag(String string, EnchantmentVision entityVision){
        TagKey<Enchantment> entityTypeTagKey = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.tryParse(string));
        Registries.ENCHANTMENT.iterateEntries(entityTypeTagKey).forEach(
                entityTypeRegistryEntry ->
                        Visions.ENCHANTMENT_VISIONS.put(entityTypeRegistryEntry.value(), new EnchantmentVision(entityVision))
        );
    }


    private static void add_entity_type_visions(){
        iterate_entity_type_visions(0);
    }
    private static void iterate_entity_type_visions(int index){
        if (index < ENTITY_TYPE_VISION_JSONS.size()) {
            EntityTypeVision itemVision = ENTITY_TYPE_VISION_JSONS.get(index);
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
                entityTypeRegistryEntry ->
                        Visions.ENTITY_TYPE_VISIONS.put(entityTypeRegistryEntry.value(), new EntityTypeVision(entityVision))
        );
    }


    private static void add_item_visions(){
        iterate_item_visions(0);
    }
    private static void iterate_item_visions(int index){
        if (index < ITEM_VISION_JSONS.size()) {
            ItemVision itemVision = ITEM_VISION_JSONS.get(index);
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

    private static void add_status_effect_visions(){
        iterate_status_effect_visions(0);
    }

    private static void iterate_status_effect_visions(int index){
        if (index < STATUS_EFFECT_VISION_JSONS.size()) {
            StatusEffectVision itemVision = STATUS_EFFECT_VISION_JSONS.get(index);
            add_status_effects(itemVision);
            iterate_status_effect_visions(index + 1);
        }
    }
    private static void add_status_effects(StatusEffectVision entityVision){
        iterate_status_effect_vision(entityVision,0);
    }
    private static void iterate_status_effect_vision(StatusEffectVision status_effect_vision, int index){
        if (index < status_effect_vision.entities.length) {
            String string = status_effect_vision.entities[index];
            if (string.startsWith("#")) add_status_effect_tag(string.substring(1),status_effect_vision);
            else if (!string.startsWith("!")) add_status_effect(string,status_effect_vision);
            iterate_status_effect_vision(status_effect_vision,index + 1);
        }
    }
    private static void add_status_effect(String string, StatusEffectVision entityVision){
        StatusEffect entityType = Registries.STATUS_EFFECT.get(Identifier.tryParse(string));
        Visions.STATUS_EFFECT_VISIONS.put(entityType, new StatusEffectVision(entityVision));
    }
    private static void add_status_effect_tag(String string, StatusEffectVision status_effect_vision){
        TagKey<StatusEffect> entityTypeTagKey = TagKey.of(RegistryKeys.STATUS_EFFECT, Identifier.tryParse(string));
        Registries.STATUS_EFFECT.iterateEntries(entityTypeTagKey).forEach(
                entityTypeRegistryEntry ->
                        Visions.STATUS_EFFECT_VISIONS.put(entityTypeRegistryEntry.value(), new StatusEffectVision(status_effect_vision))
        );
    }


    public static BlockVision get_block_vision(Block block){
        if (Visions.is_empty()) {
            add_visions();
            Visions.clear();
        }
        return BLOCK_VISIONS.get(block);
    }
    public static EnchantmentVision get_entity_type_vision(Enchantment enchantment){
        if (Visions.is_empty()) {
            add_visions();
            Visions.clear();
        }
        return ENCHANTMENT_VISIONS.get(enchantment);
    }
    public static EntityTypeVision get_entity_type_vision(EntityType<?> entity_type){
        if (Visions.is_empty()) {
            add_visions();
            Visions.clear();
        }
        return ENTITY_TYPE_VISIONS.get(entity_type);
    }

    public static ItemVision get_item_vision(Item item){
        if (Visions.is_empty()) {
            add_visions();
            Visions.json_clear();
        }
        return ITEM_VISIONS.get(item);
    }

    public static StatusEffectVision get_status_effect_vision(StatusEffect status_effect){
        if (Visions.is_empty()) {
            add_visions();
            Visions.json_clear();
        }
        return STATUS_EFFECT_VISIONS.get(status_effect);
    }

    public static void add_visions(){
        add_block_visions();
        add_enchantment_visions();
        add_entity_type_visions();
        add_status_effect_visions();
        add_item_visions();
    }
    public static boolean is_empty(){
        return BLOCK_VISIONS.isEmpty() && ENCHANTMENT_VISIONS.isEmpty() && ENTITY_TYPE_VISIONS.isEmpty() && ITEM_VISIONS.isEmpty() && STATUS_EFFECT_VISIONS.isEmpty();
    }
    public static void json_clear(){
        BLOCK_VISION_JSONS.clear();
        ENCHANTMENT_VISION_JSONS.clear();
        ENTITY_TYPE_VISION_JSONS.clear();
        ITEM_VISION_JSONS.clear();
        STATUS_EFFECT_VISION_JSONS.clear();
    }
    public static void clear(){
        BLOCK_VISIONS.clear();
        ENCHANTMENT_VISIONS.clear();
        ENTITY_TYPE_VISIONS.clear();
        ITEM_VISIONS.clear();
        STATUS_EFFECT_VISIONS.clear();
    }
    //You have already seen I like functional It Hard to have errors in it
}