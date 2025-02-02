package net.lixir.vminus.vision;

import net.lixir.vminus.vision.type.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.*;
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
    private static void iterate_block_visions(int index){
        if (index < BLOCK_VISION_JSONS.size()) {
            BlockVision block_vision = BLOCK_VISION_JSONS.get(index);
            iterate_block_vision(block_vision, block_vision.get_targets(),0);
            iterate_block_visions(index + 1);
        }
    }
    private static void iterate_block_vision(BlockVision block_vision, String[] targets, int index){
        if (index < targets.length) {
            Block block = Registries.BLOCK.get(new Identifier(targets[index]));
            BLOCK_VISIONS.put(block, new BlockVision(block,block_vision));
            iterate_block_vision(block_vision,targets,index + 1);
        }
    }
    private static void iterate_enchantment_visions(int index){
        if (index < ENCHANTMENT_VISION_JSONS.size()) {
            EnchantmentVision enchantment_vision = ENCHANTMENT_VISION_JSONS.get(index);
            iterate_enchantment_vision(enchantment_vision,enchantment_vision.get_targets(),0);
            iterate_enchantment_visions(index + 1);
        }
    }
    private static void iterate_enchantment_vision(EnchantmentVision enchantmentVision,String[] targets, int index){
        if (index < targets.length) {
            String string = targets[index];
            Enchantment enchantment = Registries.ENCHANTMENT.get(new Identifier(string));
            ENCHANTMENT_VISIONS.put(enchantment, new EnchantmentVision(enchantmentVision));
            iterate_enchantment_vision(enchantmentVision,targets,index + 1);
        }
    }
    private static void iterate_entity_type_visions(int index){
        if (index < ENTITY_TYPE_VISION_JSONS.size()) {
            EntityTypeVision entity_type_vision = ENTITY_TYPE_VISION_JSONS.get(index);
            iterate_entity_type_vision(entity_type_vision,entity_type_vision.get_targets(),0);
            iterate_entity_type_visions(index + 1);
        }
    }
    private static void iterate_entity_type_vision(EntityTypeVision entity_type_vision, String[] targets, int index){
        if (index < targets.length) {
            String string = targets[index];
            EntityType<?> entity_type = Registries.ENTITY_TYPE.get(new Identifier(string));
            ENTITY_TYPE_VISIONS.put(entity_type, new EntityTypeVision(entity_type_vision));
            iterate_entity_type_vision(entity_type_vision,targets,index + 1);
        }
    }
    private static void iterate_item_visions(int index){
        if (index < ITEM_VISION_JSONS.size()) {
            ItemVision itemVision = ITEM_VISION_JSONS.get(index);
            iterate_item_vision(itemVision,itemVision.get_targets(),0);
            iterate_item_visions(index + 1);
        }
    }
    private static void iterate_item_vision(ItemVision item_vision,String[] targets, int index){
        if (index < targets.length) {
            String string = targets[index];
            Item item = Registries.ITEM.get(new Identifier(string));
            if (ITEM_VISIONS.containsKey(item)){
                ITEM_VISIONS.put(item,new ItemVision(ITEM_VISIONS.get(item),item_vision));
            }
            ITEM_VISIONS.put(item, new ItemVision(item,item_vision));
            iterate_item_vision(item_vision,targets,index + 1);
        }
    }
    private static void iterate_status_effect_visions(int index){
        if (index < STATUS_EFFECT_VISION_JSONS.size()) {
            StatusEffectVision status_effect_vision = STATUS_EFFECT_VISION_JSONS.get(index);
            iterate_status_effect_vision(status_effect_vision,status_effect_vision.get_targets(),0);
            iterate_status_effect_visions(index + 1);
        }
    }
    private static void iterate_status_effect_vision(StatusEffectVision status_effect_vision,String[] targets, int index){
        if (index < targets.length) {
            String string = targets[index];
            StatusEffect status_effect = Registries.STATUS_EFFECT.get(new Identifier(string));
            STATUS_EFFECT_VISIONS.put(status_effect, new StatusEffectVision(status_effect_vision));
            iterate_status_effect_vision(status_effect_vision,targets,index + 1);
        }
    }
    public static BlockVision get_block_vision(Block block){
        vision_protocol();
        return BLOCK_VISIONS.get(block);
    }
    public static EnchantmentVision get_enchantment_vision(Enchantment enchantment){
        vision_protocol();
        return ENCHANTMENT_VISIONS.get(enchantment);
    }
    public static EntityTypeVision get_entity_type_vision(EntityType<?> entity_type){
        vision_protocol();
        return ENTITY_TYPE_VISIONS.get(entity_type);
    }

    public static ItemVision get_item_vision(Item item){
        vision_protocol();
        return ITEM_VISIONS.get(item);
    }

    public static StatusEffectVision get_status_effect_vision(StatusEffect status_effect){
        vision_protocol();
        return STATUS_EFFECT_VISIONS.get(status_effect);
    }

    private static void vision_protocol(){
        if (Visions.is_empty()) {
            add_visions();
            Visions.json_clear();
        }
    }
    private static boolean is_empty(){
        return BLOCK_VISIONS.isEmpty() && ENCHANTMENT_VISIONS.isEmpty() && ENTITY_TYPE_VISIONS.isEmpty() && ITEM_VISIONS.isEmpty() && STATUS_EFFECT_VISIONS.isEmpty();
    }
    public static void add_visions(){
        iterate_block_visions(0);
        iterate_enchantment_visions(0);
        iterate_entity_type_visions(0);
        iterate_status_effect_visions(0);
        iterate_item_visions(0);
    }
    private static void json_clear(){
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
}