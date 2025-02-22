package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.properties.Attribute;
import net.lixir.vminus.vision.properties.FoodProperties;
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
    private final ItemVisionBoolean[] banned;
    private final ItemVisionBoolean[] fire_resistant;
    private final ItemVisionBoolean[] transfer_nbt;
    private final ItemVisionBoolean[] enchantable;
    private final ItemVisionBoolean[] glinted;
    private final ItemVisionString[] replacement;
    private final ItemVisionString[] break_replacement;
    private final ItemVisionString[] pick_up_sound;
    private final ItemVisionFoodProperties[] food_properties;
    private final ItemVisionAttributes[] attributes;
    private final ItemVisionAttributes[] add_attributes;
    private final ItemVisionAttributes[] remove_attributes;
    private final ItemVisionInteger[] fuel_time;
    private final ItemVisionInteger[] min_damage;
    private final ItemVisionInteger[] reinforcement;
    private final ItemVisionInteger[] max_damage;
    private final ItemVisionInteger[] max_stack;

    public ItemVision(String[] items, ItemVisionBoolean[] banned, ItemVisionString[] replacement, ItemVisionString[] pick_up_sound, ItemVisionFoodProperties[] food_properties, ItemVisionString[] break_replacement, ItemVisionBoolean[] fire_resistant, ItemVisionBoolean[] transfer_nbt, ItemVisionBoolean[] enchantable, ItemVisionBoolean[] glinted, ItemVisionAttributes[] attributes, ItemVisionAttributes[] add_attributes, ItemVisionAttributes[] remove_attributes, ItemVisionInteger[] fuel_time, ItemVisionInteger[] min_damage, ItemVisionInteger[] reinforcement, ItemVisionInteger[] max_damage, ItemVisionInteger[] max_stack) {
        this.items = items;
        this.banned = banned;
        this.replacement = replacement;
        this.break_replacement = break_replacement;
        this.pick_up_sound = pick_up_sound;
        this.food_properties = food_properties;
        this.fire_resistant = fire_resistant;
        this.transfer_nbt = transfer_nbt;
        this.enchantable = enchantable;
        this.glinted = glinted;
        this.attributes = attributes;
        this.add_attributes = add_attributes;
        this.remove_attributes = remove_attributes;
        this.fuel_time = fuel_time;
        this.min_damage = min_damage;
        this.reinforcement = reinforcement;
        this.max_damage = max_damage;
        this.max_stack = max_stack;
    }
    public ItemVision(ItemVision vision_left, ItemVision vision_right){
        this.items = null;
        this.attributes = VisionHelper.collect_vision_values(vision_left.attributes,vision_right.attributes, ItemVisionAttributes[]::new);
        this.add_attributes = VisionHelper.collect_vision_values(vision_left.attributes,vision_right.attributes, ItemVisionAttributes[]::new);
        this.remove_attributes = VisionHelper.collect_vision_values(vision_left.attributes,vision_right.attributes, ItemVisionAttributes[]::new);
        this.banned = VisionHelper.collect_vision_values(vision_left.banned,vision_right.banned,ItemVisionBoolean[]::new);
        this.replacement = VisionHelper.collect_vision_values(vision_left.replacement,vision_right.replacement,ItemVisionString[]::new);
        this.break_replacement = VisionHelper.collect_vision_values(vision_left.break_replacement,vision_right.break_replacement,ItemVisionString[]::new);
        this.pick_up_sound = VisionHelper.collect_vision_values(vision_left.pick_up_sound,vision_right.pick_up_sound,ItemVisionString[]::new);
        this.food_properties = VisionHelper.collect_vision_values(vision_left.food_properties,vision_right.food_properties,ItemVisionFoodProperties[]::new);
        this.fire_resistant = VisionHelper.collect_vision_values(vision_left.fire_resistant,vision_right.fire_resistant,ItemVisionBoolean[]::new);
        this.transfer_nbt = VisionHelper.collect_vision_values(vision_left.transfer_nbt,vision_right.transfer_nbt,ItemVisionBoolean[]::new);
        this.enchantable = VisionHelper.collect_vision_values(vision_left.enchantable,vision_right.enchantable,ItemVisionBoolean[]::new);
        this.glinted = VisionHelper.collect_vision_values(vision_left.glinted,vision_right.glinted,ItemVisionBoolean[]::new);
        this.fuel_time = VisionHelper.collect_vision_values(vision_left.fuel_time,vision_right.fuel_time,ItemVisionInteger[]::new);
        this.min_damage = VisionHelper.collect_vision_values(vision_left.min_damage,vision_right.min_damage,ItemVisionInteger[]::new);
        this.reinforcement = VisionHelper.collect_vision_values(vision_left.reinforcement,vision_right.reinforcement,ItemVisionInteger[]::new);
        this.max_damage = VisionHelper.collect_vision_values(vision_left.max_damage,vision_right.max_damage,ItemVisionInteger[]::new);
        this.max_stack = VisionHelper.collect_vision_values(vision_left.max_stack,vision_right.max_stack,ItemVisionInteger[]::new);
    }
    public Boolean get_banned(Item item) {
        return VisionHelper.vision_value(item,banned);
    }
    public Boolean get_fire_resistant(Item item) {
        return VisionHelper.vision_value(item,fire_resistant);
    }
    public Item get_replacement(Item item) {
        return VisionHelper.item(VisionHelper.vision_value(item,replacement));
    }
    public String get_break_replacement(Item item) {
        return VisionHelper.vision_value(item,break_replacement);
    }
    public String get_pick_up_sound(Item item) {
        return VisionHelper.vision_value(item,pick_up_sound);
    }
    public Boolean get_transfer_nbt(Item item) {
        return VisionHelper.vision_value(item,transfer_nbt);
    }
    public Boolean get_enchantable(Item item) {
        return VisionHelper.vision_value(item,enchantable);
    }
    public Integer get_min_damage(Item item) {
        return VisionHelper.vision_value(item,min_damage);
    }
    public Integer get_max_damage(Item item) {
        return VisionHelper.vision_value(item,max_damage);
    }
    public Boolean get_glinted(Item item) {
        return VisionHelper.vision_value(item,glinted);
    }
    public FoodProperties get_food_properties(Item item){
        return VisionHelper.vision_value(item,food_properties);
    }
    public Attribute[] get_attributes(Item item){
        return VisionHelper.vision_value(item,attributes);
    }
    public Attribute[] get_add_attributes(Item item){
        return VisionHelper.vision_value(item,add_attributes);
    }
    public Attribute[] get_remove_attributes(Item item){
        return VisionHelper.vision_value(item,remove_attributes);
    }
    public Item[] get_items(Vector<Item> vector_items,int index){
        if (index < items.length){
            String item_entry = items[index];
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
            return get_items(vector_items,index+1);
        }
        else return vector_items.toArray(Item[]::new);
    }
}

