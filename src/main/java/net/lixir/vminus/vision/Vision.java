package net.lixir.vminus.vision;

import net.lixir.vminus.vision.accessor.*;
import net.lixir.vminus.vision.type.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;

import java.util.*;

public class Vision {
    public static BlockVision[] block_visions = new BlockVision[]{};
    public static EnchantmentVision[] enchantment_visions = new EnchantmentVision[]{};
    public static EntityTypeVision[] entity_type_visions = new EntityTypeVision[]{};
    public static ItemVision[] item_visions = new ItemVision[]{};
    public static StatusEffectVision[] status_effect_visions = new StatusEffectVision[]{};
    private static void set_block_visions(int index){
        if (index < block_visions.length) {
            BlockVision block_vision = block_visions[index];
            set_block_vision(block_vision, block_vision.get_blocks(new Vector<>(),0),0);
            set_block_visions(index + 1);
        }
    }
    private static void set_block_vision(BlockVision block_vision, Block[] blocks, int index){
        if (index < blocks.length) {
            Block block = blocks[index];
            if (get_vision(block) != null)
                set_vision(block,new BlockVision(get_vision(block),block_vision));
            else set_vision(block,block_vision);
            set_block_vision(block_vision,blocks,index + 1);
        }
    }
    private static void set_enchantment_visions(int index){
        if (index < enchantment_visions.length) {
            EnchantmentVision enchantment_vision = enchantment_visions[index];
            set_enchantment_vision(enchantment_vision,enchantment_vision.get_enchantments(new Vector<>(),0),0);
            set_enchantment_visions(index + 1);
        }
    }
    private static void set_enchantment_vision(EnchantmentVision enchantment_vision, Enchantment[] enchantments, int index){
        if (index < enchantments.length) {
            Enchantment enchantment = enchantments[index];
            if (enchantment != null && get_vision(enchantment) != null)
                set_vision(enchantment,new EnchantmentVision(get_vision(enchantment),enchantment_vision));
            else if (enchantment != null) set_vision(enchantment,enchantment_vision);
            set_enchantment_vision(enchantment_vision,enchantments,index + 1);
        }
    }
    private static void set_entity_type_visions(int index){
        if (index < entity_type_visions.length) {
            EntityTypeVision entity_type_vision = entity_type_visions[index];
            set_entity_type_vision(entity_type_vision,entity_type_vision.get_entity_types(new Vector<>(),0),0);
            set_entity_type_visions(index + 1);
        }
    }
    private static void set_entity_type_vision(EntityTypeVision entity_type_vision, EntityType<?>[] entity_types, int index){
        if (index < entity_types.length) {
            EntityType<?> entity_type = entity_types[index];
            if (get_vision(entity_type) != null)
                set_vision(entity_type,new EntityTypeVision(get_vision(entity_type),entity_type_vision));
            else set_vision(entity_type,entity_type_vision);
            set_entity_type_vision(entity_type_vision,entity_types,index + 1);
        }
    }
    private static void set_item_visions(int index){
        if (index < item_visions.length) {
            ItemVision item_vision = item_visions[index];
            set_item_vision(item_vision,item_vision.get_items(new Vector<>(),0),0);
            set_item_visions(index + 1);
        }
    }
    private static void set_item_vision(ItemVision item_vision, Item[] items, int index){
        if (index < items.length) {
            Item item = items[index];
            if (get_vision(item) != null) set_vision(item,new ItemVision(get_vision(item),item_vision));
            else set_vision(item,item_vision);
            set_item_vision(item_vision,items,index + 1);
        }
    }
    private static void set_status_effect_visions(int index){
        if (index < status_effect_visions.length) {
            StatusEffectVision status_effect_vision = status_effect_visions[index];
            set_status_effect_vision(status_effect_vision,status_effect_vision.get_status_effects(new Vector<>(),0),0);
            set_status_effect_visions(index + 1);
        }
    }
    private static void set_status_effect_vision(StatusEffectVision status_effect_vision, StatusEffect[] status_effects, int index){
        if (index < status_effects.length) {
            StatusEffect status_effect = status_effects[index];
            if (status_effect != null && get_vision(status_effect) != null)
                set_vision(status_effect,new StatusEffectVision(get_vision(status_effect),status_effect_vision));
            else if (status_effect != null) set_vision(status_effect,status_effect_vision);
            set_status_effect_vision(status_effect_vision,status_effects,index + 1);
        }
    }
    public static void set_visions(){
        set_block_visions(0);
        set_enchantment_visions(0);
        set_entity_type_visions(0);
        set_status_effect_visions(0);
        set_item_visions(0);
    }
    public static <T extends Block> BlockVision get_vision(T block){
        if (block instanceof BlockVisionAccessor block_vision_container) return block_vision_container.vminus$get_vision();
        return null;
    }
    public static <T extends Enchantment> EnchantmentVision get_vision(T enchantment){
        if (enchantment instanceof EnchantmentVisionAccessor enchantment_vision_container) return enchantment_vision_container.vminus$get_vision();
        return null;
    }
    public static <T extends EntityType<?>> EntityTypeVision get_vision(T entity_type){
        if (entity_type instanceof EntityTypeVisionAccessor entity_type_vision_container) return entity_type_vision_container.vminus$get_vision();
        return null;
    }
    public static <T extends Item> ItemVision get_vision(T item){
        if (item instanceof ItemVisionAccessor item_vision_container) return item_vision_container.vminus$get_vision();
        return null;
    }
    public static <T extends StatusEffect> StatusEffectVision get_vision(T status_effect){
        if (status_effect instanceof StatusEffectVisionAccessor status_effect_vision_container) return status_effect_vision_container.vminus$get_vision();
        return null;
    }
    public static <T extends Block> void set_vision(T block,BlockVision block_vision){
        if (block instanceof BlockVisionAccessor block_vision_accessor) block_vision_accessor.vminus$set_vision(block_vision);
    }
    public static <T extends Enchantment> void set_vision(T enchantment, EnchantmentVision enchantment_vision){
        if (enchantment instanceof EnchantmentVisionAccessor enchantment_vision_accessor) enchantment_vision_accessor.vminus$set_vision(enchantment_vision);
    }
    public static <T extends EntityType<?>> void set_vision(T entity_type, EntityTypeVision entity_type_vision){
        if (entity_type instanceof EntityTypeVisionAccessor entity_type_vision_accessor) entity_type_vision_accessor.vminus$set_vision(entity_type_vision);
    }
    public static <T extends Item> void set_vision(T item, ItemVision item_vision){
        if (item instanceof ItemVisionAccessor item_vision_accessor) item_vision_accessor.vminus$set_vision(item_vision);
    }
    public static <T extends StatusEffect> void set_vision(T status_effect, StatusEffectVision status_effect_vision){
        if (status_effect instanceof StatusEffectVisionAccessor status_effect_vision_container) status_effect_vision_container.vminus$set_vision(status_effect_vision);
    }
}