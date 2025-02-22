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
    public static void set_visions(){
        Arrays.stream(block_visions).forEach(Vision::set_block_visions);
        Arrays.stream(enchantment_visions).forEach(Vision::set_enchantment_visions);
        Arrays.stream(entity_type_visions).forEach(Vision::set_entity_type_visions);
        Arrays.stream(status_effect_visions).forEach(Vision::set_status_effect_visions);
        Arrays.stream(item_visions).forEach(Vision::set_item_visions);
    }
    private static void set_block_visions(BlockVision block_vision){
        Arrays.stream(block_vision.get_blocks(new Vector<>(), 0)).forEach(block -> set_block_vision(block,block_vision));
    }
    private static void set_block_vision(Block block, BlockVision block_vision){
        if (get_vision(block) != null)
            set_vision(block,new BlockVision(get_vision(block),block_vision));
        else set_vision(block,block_vision);
    }
    private static void set_enchantment_visions(EnchantmentVision enchantment_vision){
        Arrays.stream(enchantment_vision.get_enchantments(new Vector<>(),0)).forEach(enchantment -> set_enchantment_vision(enchantment,enchantment_vision));
    }
    private static void set_enchantment_vision(Enchantment enchantment, EnchantmentVision enchantment_vision){
        if (enchantment != null && get_vision(enchantment) != null)
            set_vision(enchantment,new EnchantmentVision(get_vision(enchantment),enchantment_vision));
        else if (enchantment != null) set_vision(enchantment,enchantment_vision);
    }
    private static void set_entity_type_visions(EntityTypeVision entity_type_vision){
        Arrays.stream(entity_type_vision.get_entity_types(new Vector<>(),0)).forEach(entity_type -> set_entity_type_vision(entity_type,entity_type_vision));
    }
    private static void set_entity_type_vision(EntityType<?> entity_type, EntityTypeVision entity_type_vision){
        if (get_vision(entity_type) != null)
            set_vision(entity_type,new EntityTypeVision(get_vision(entity_type),entity_type_vision));
        else set_vision(entity_type,entity_type_vision);
    }
    private static void set_item_visions(ItemVision item_vision){
        Arrays.stream(item_vision.get_items(new Vector<>(),0)).forEach(item -> set_item_vision(item,item_vision));
    }
    private static void set_item_vision(Item item, ItemVision item_vision){
        if (get_vision(item) != null)
            set_vision(item,new ItemVision(get_vision(item),item_vision));
        else set_vision(item,item_vision);
    }
    private static void set_status_effect_visions(StatusEffectVision status_effect_vision){
        Arrays.stream(status_effect_vision.get_status_effects(new Vector<>(),0)).forEach(status_effect -> set_status_effect_vision(status_effect,status_effect_vision));
    }
    private static void set_status_effect_vision(StatusEffect status_effect, StatusEffectVision status_effect_vision){
        if (status_effect != null && get_vision(status_effect) != null)
            set_vision(status_effect,new StatusEffectVision(get_vision(status_effect),status_effect_vision));
        else if (status_effect != null) set_vision(status_effect,status_effect_vision);
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