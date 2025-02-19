package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.properties.FoodProperties;
import net.lixir.vminus.vision.value.item.ItemVisionBoolean;
import net.lixir.vminus.vision.value.item.ItemVisionFoodProperties;
import net.lixir.vminus.vision.value.item.ItemVisionInteger;
import net.lixir.vminus.vision.value.item.ItemVisionString;
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
    private final ItemVisionString[] replacement;
    private final ItemVisionString[] break_replacement;
    private final ItemVisionString[] pick_up_sound;
    private final ItemVisionFoodProperties[] food_properties;
    private final ItemVisionBoolean[] fire_resistant;
    private final ItemVisionBoolean[] transfer_nbt;
    private final ItemVisionBoolean[] enchantable;
    private final ItemVisionBoolean[] glinted;
    private final ItemVisionInteger[] fuel_time;
    private final ItemVisionInteger[] min_damage;
    private final ItemVisionInteger[] reinforcement;
    private final ItemVisionInteger[] max_damage;
    private final ItemVisionInteger[] max_stack;

    public ItemVision(String[] items, ItemVisionBoolean[] banned, ItemVisionString[] replacement,ItemVisionString[] pick_up_sound,ItemVisionFoodProperties[] food_properties, ItemVisionString[] break_replacement, ItemVisionBoolean[] fire_resistant, ItemVisionBoolean[] transfer_nbt, ItemVisionBoolean[] enchantable, ItemVisionBoolean[] glinted, ItemVisionInteger[] fuel_time, ItemVisionInteger[] min_damage, ItemVisionInteger[] reinforcement, ItemVisionInteger[] max_damage,ItemVisionInteger[] max_stack) {
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
        this.fuel_time = fuel_time;
        this.min_damage = min_damage;
        this.reinforcement = reinforcement;
        this.max_damage = max_damage;
        this.max_stack = max_stack;
    }
    public ItemVision(ItemVision vision_left,ItemVision vision_right){
        this.items = new String[]{};
        this.banned = VisionHelper.collect_vision_values(vision_left.banned,vision_right.banned).toArray(ItemVisionBoolean[]::new);
        this.replacement = VisionHelper.collect_vision_values(vision_left.replacement,vision_right.replacement).toArray(ItemVisionString[]::new);
        this.break_replacement = VisionHelper.collect_vision_values(vision_left.break_replacement,vision_right.break_replacement).toArray(ItemVisionString[]::new);
        this.pick_up_sound = VisionHelper.collect_vision_values(vision_left.pick_up_sound,vision_right.pick_up_sound).toArray(ItemVisionString[]::new);
        this.food_properties = VisionHelper.collect_vision_values(vision_left.food_properties,vision_right.food_properties).toArray(ItemVisionFoodProperties[]::new);
        this.fire_resistant = VisionHelper.collect_vision_values(vision_left.fire_resistant,vision_right.fire_resistant).toArray(ItemVisionBoolean[]::new);
        this.transfer_nbt = VisionHelper.collect_vision_values(vision_left.transfer_nbt,vision_right.transfer_nbt).toArray(ItemVisionBoolean[]::new);
        this.enchantable = VisionHelper.collect_vision_values(vision_left.enchantable,vision_right.enchantable).toArray(ItemVisionBoolean[]::new);
        this.glinted = VisionHelper.collect_vision_values(vision_left.glinted,vision_right.glinted).toArray(ItemVisionBoolean[]::new);
        this.fuel_time = VisionHelper.collect_vision_values(vision_left.fuel_time,vision_right.fuel_time).toArray(ItemVisionInteger[]::new);
        this.min_damage = VisionHelper.collect_vision_values(vision_left.min_damage,vision_right.min_damage).toArray(ItemVisionInteger[]::new);
        this.reinforcement = VisionHelper.collect_vision_values(vision_left.reinforcement,vision_right.reinforcement).toArray(ItemVisionInteger[]::new);
        this.max_damage = VisionHelper.collect_vision_values(vision_left.max_damage,vision_right.max_damage).toArray(ItemVisionInteger[]::new);
        this.max_stack = VisionHelper.collect_vision_values(vision_left.max_stack,vision_right.max_stack).toArray(ItemVisionInteger[]::new);
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

