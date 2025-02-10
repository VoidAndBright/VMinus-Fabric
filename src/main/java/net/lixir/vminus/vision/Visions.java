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
    public static final CopyOnWriteArrayList<BlockVision> DATA_PACK_BLOCK_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EnchantmentVision> DATA_PACK_ENCHANTMENT_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<EntityTypeVision> DATA_PACK_ENTITY_TYPE_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<ItemVision> DATA_PACK_ITEM_VISIONS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<StatusEffectVision> DATA_PACK_STATUS_EFFECT_VISIONS = new CopyOnWriteArrayList<>();
    public static final Map<Block, BlockVision> BLOCK_VISIONS = new HashMap<>();
    public static final Map<Enchantment, EnchantmentVision> ENCHANTMENT_VISIONS = new HashMap<>();
    public static final Map<EntityType<?>, EntityTypeVision> ENTITY_TYPE_VISIONS = new HashMap<>();
    public static final Map<Item, ItemVision> ITEM_VISIONS = new HashMap<>();
    public static final Map<StatusEffect, StatusEffectVision> STATUS_EFFECT_VISIONS = new HashMap<>();
    private static void apply_block_visions(int index){
        if (index < DATA_PACK_BLOCK_VISIONS.size()) {
            BlockVision block_vision = DATA_PACK_BLOCK_VISIONS.get(index);
            iterate_block_vision(block_vision, block_vision.get_targets(),0);
            apply_block_visions(index + 1);
        }
    }
    private static void iterate_block_vision(BlockVision block_vision, String[] targets, int index){
        if (index < targets.length) {
            Block block = Registries.BLOCK.get(new Identifier(targets[index]));
            BLOCK_VISIONS.put(block, new BlockVision(block_vision));
            iterate_block_vision(block_vision,targets,index + 1);
        }
    }
    private static void apply_enchantment_visions(int index){
        if (index < DATA_PACK_ENCHANTMENT_VISIONS.size()) {
            EnchantmentVision enchantment_vision = DATA_PACK_ENCHANTMENT_VISIONS.get(index);
            iterate_enchantment_vision(enchantment_vision,enchantment_vision.get_targets(),0);
            apply_enchantment_visions(index + 1);
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
    private static void apply_entity_type_visions(int index){
        if (index < DATA_PACK_ENTITY_TYPE_VISIONS.size()) {
            EntityTypeVision entity_type_vision = DATA_PACK_ENTITY_TYPE_VISIONS.get(index);
            iterate_entity_type_vision(entity_type_vision,entity_type_vision.get_targets(),0);
            apply_entity_type_visions(index + 1);
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
    private static void apply_item_visions(int index){
        if (index < DATA_PACK_ITEM_VISIONS.size()) {
            ItemVision itemVision = DATA_PACK_ITEM_VISIONS.get(index);
            iterate_item_vision(itemVision,itemVision.get_targets(),0);
            apply_item_visions(index + 1);
        }
    }
    private static void iterate_item_vision(ItemVision item_vision,String[] targets, int index){
        if (index < targets.length) {
            String string = targets[index];
            Item item = Registries.ITEM.get(new Identifier(string));
            if (ITEM_VISIONS.containsKey(item)){
                ITEM_VISIONS.put(item,new ItemVision(ITEM_VISIONS.get(item),item_vision));
            }
            ITEM_VISIONS.put(item, new ItemVision(item_vision));
            iterate_item_vision(item_vision,targets,index + 1);
        }
    }
    private static void apply_status_effect_visions(int index){
        if (index < DATA_PACK_STATUS_EFFECT_VISIONS.size()) {
            StatusEffectVision status_effect_vision = DATA_PACK_STATUS_EFFECT_VISIONS.get(index);
            iterate_status_effect_vision(status_effect_vision,status_effect_vision.get_targets(),0);
            apply_status_effect_visions(index + 1);
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
        if (Visions.is_empty()) add_visions();
        return BLOCK_VISIONS.get(block);
    }
    public static EnchantmentVision get_enchantment_vision(Enchantment enchantment){
        if (Visions.is_empty()) add_visions();
        return ENCHANTMENT_VISIONS.get(enchantment);
    }
    public static EntityTypeVision get_entity_type_vision(EntityType<?> entity_type){
        if (Visions.is_empty()) add_visions();
        return ENTITY_TYPE_VISIONS.get(entity_type);
    }

    public static ItemVision get_item_vision(Item item){
        if (Visions.is_empty()) add_visions();
        return ITEM_VISIONS.get(item);
    }

    public static StatusEffectVision get_status_effect_vision(StatusEffect status_effect){
        if (Visions.is_empty()) add_visions();
        return STATUS_EFFECT_VISIONS.get(status_effect);
    }
    private static boolean is_empty(){
        return BLOCK_VISIONS.isEmpty() && ENCHANTMENT_VISIONS.isEmpty() && ENTITY_TYPE_VISIONS.isEmpty() && ITEM_VISIONS.isEmpty() && STATUS_EFFECT_VISIONS.isEmpty();
    }
    public static void add_visions(){
        apply_block_visions(0);
        apply_enchantment_visions(0);
        apply_entity_type_visions(0);
        apply_status_effect_visions(0);
        apply_item_visions(0);
    }
    public static void clear(){
        BLOCK_VISIONS.clear();
        ENCHANTMENT_VISIONS.clear();
        ENTITY_TYPE_VISIONS.clear();
        ITEM_VISIONS.clear();
        STATUS_EFFECT_VISIONS.clear();
        DATA_PACK_BLOCK_VISIONS.clear();
        DATA_PACK_ENCHANTMENT_VISIONS.clear();
        DATA_PACK_ENTITY_TYPE_VISIONS.clear();
        DATA_PACK_ITEM_VISIONS.clear();
        DATA_PACK_STATUS_EFFECT_VISIONS.clear();
    }
}