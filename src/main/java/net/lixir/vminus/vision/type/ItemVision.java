package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.properties.VisionAttributeModifier;
import net.lixir.vminus.vision.properties.VisionFoodProperties;
import net.lixir.vminus.vision.value.item.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;


public class ItemVision {
    private final String[] items;
    private final Vector<ItemVisionBoolean> banned;
    private final Vector<ItemVisionBoolean> fire_resistant;
    private final Vector<ItemVisionBoolean> transfer_nbt;
    private final Vector<ItemVisionBoolean> enchantable;
    private final Vector<ItemVisionBoolean> glinted;
    private final Vector<ItemVisionString> replacement;
    private final Vector<ItemVisionString> break_replacement;
    private final Vector<ItemVisionString> pick_up_sound;
    private final Vector<ItemVisionFoodProperties> food_properties;
    private final Vector<ItemVisionAttributes> attributes;
    private final Vector<ItemVisionAttributes> add_attributes;
    private final Vector<ItemVisionAttributes> remove_attributes;
    private final Vector<ItemVisionInteger> fuel_time;
    private final Vector<ItemVisionInteger> min_damage;
    private final Vector<ItemVisionInteger> reinforcement;
    private final Vector<ItemVisionInteger> max_damage;
    private final Vector<ItemVisionInteger> max_stack;

    public ItemVision(ItemVision base, ItemVision add){
        this.items = null;
        this.attributes = Vision.sum(base.attributes,add.attributes);
        this.add_attributes = Vision.sum(base.attributes,add.attributes);
        this.remove_attributes = Vision.sum(base.attributes,add.attributes);
        this.banned = Vision.sum(base.banned,add.banned);
        this.replacement = Vision.sum(base.replacement,add.replacement);
        this.break_replacement = Vision.sum(base.break_replacement,add.break_replacement);
        this.pick_up_sound = Vision.sum(base.pick_up_sound,add.pick_up_sound);
        this.food_properties = Vision.sum(base.food_properties,add.food_properties);
        this.fire_resistant = Vision.sum(base.fire_resistant,add.fire_resistant);
        this.transfer_nbt = Vision.sum(base.transfer_nbt,add.transfer_nbt);
        this.enchantable = Vision.sum(base.enchantable,add.enchantable);
        this.glinted = Vision.sum(base.glinted,add.glinted);
        this.fuel_time = Vision.sum(base.fuel_time,add.fuel_time);
        this.min_damage = Vision.sum(base.min_damage,add.min_damage);
        this.reinforcement = Vision.sum(base.reinforcement,add.reinforcement);
        this.max_damage = Vision.sum(base.max_damage,add.max_damage);
        this.max_stack = Vision.sum(base.max_stack,add.max_stack);
    }
    public Boolean get_banned(Item item) {
        return Vision.unwrap(item,banned);
    }
    public Boolean get_fire_resistant(Item item) {
        return Vision.unwrap(item,fire_resistant);
    }
    public Item get_replacement(Item item) {
        String string = Vision.unwrap(item,replacement);
        if (string == null) return null;
        return Registries.ITEM.get(new Identifier(string));
    }
    public Item get_break_replacement(Item item) {
        String string = Vision.unwrap(item,break_replacement);
        if (string == null) return null;
        return Registries.ITEM.get(new Identifier(string));
    }
    public String get_pick_up_sound(Item item) {
        return Vision.unwrap(item,pick_up_sound);
    }
    public Boolean get_transfer_nbt(Item item) {
        return Vision.unwrap(item,transfer_nbt);
    }
    public Boolean get_enchantable(Item item) {
        return Vision.unwrap(item,enchantable);
    }
    public Integer get_min_damage(Item item) {
        return Vision.unwrap(item,min_damage);
    }
    public Integer get_max_damage(Item item) {
        return Vision.unwrap(item,max_damage);
    }
    public Boolean get_glinted(Item item) {
        return Vision.unwrap(item,glinted);
    }
    public VisionFoodProperties get_food_properties(Item item){
        return Vision.unwrap(item,food_properties);
    }
    public VisionAttributeModifier[] get_attributes(Item item){
        return Vision.unwrap(item,attributes);
    }
    public VisionAttributeModifier[] get_add_attributes(Item item){
        return Vision.unwrap(item,add_attributes);
    }
    public VisionAttributeModifier[] get_remove_attributes(Item item){
        return Vision.unwrap(item,remove_attributes);
    }
    public Vector<Item> get_items(){
        Vector<Item> vector_items = new Vector<>();
        for (String item_entry:items){
            if (item_entry.startsWith("*")){
                vector_items.addAll(Registries.ITEM.stream().toList());
            }
            if (item_entry.startsWith("!#")) {
                String item_tag = item_entry.substring(2);
                TagKey<Item> item_tag_key = TagKey.of(RegistryKeys.ITEM, new Identifier(item_tag));
                vector_items.removeAll(Registries.ITEM.getOrCreateEntryList(item_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (item_entry.startsWith("#")) {
                String item_tag = item_entry.substring(1);
                TagKey<Item> item_tag_key = TagKey.of(RegistryKeys.ITEM, new Identifier(item_tag));
                vector_items.addAll(Registries.ITEM.getOrCreateEntryList(item_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (item_entry.startsWith("!")) vector_items.remove(Registries.ITEM.get(new Identifier(item_entry.substring(1))));
            else vector_items.add(Registries.ITEM.get(new Identifier(item_entry)));
        }
        return vector_items;
    }
}

