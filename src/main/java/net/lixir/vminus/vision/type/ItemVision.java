package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.properties.FoodProperties;
import net.lixir.vminus.vision.value.item.ItemVisionBoolean;
import net.lixir.vminus.vision.value.item.ItemVisionFoodProperties;
import net.lixir.vminus.vision.value.item.ItemVisionInteger;
import net.lixir.vminus.vision.value.item.ItemVisionString;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;


public class ItemVision implements Vision<Item> {
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


    public ItemVision(String[] items, ItemVisionBoolean[] banned, ItemVisionString[] replacement,ItemVisionString[] pick_up_sound,ItemVisionFoodProperties[] food_properties, ItemVisionString[] break_replacement, ItemVisionBoolean[] fire_resistant, ItemVisionBoolean[] transfer_nbt, ItemVisionBoolean[] enchantable, ItemVisionBoolean[] glinted, ItemVisionInteger[] fuel_time, ItemVisionInteger[] min_damage, ItemVisionInteger[] reinforcement, ItemVisionInteger[] max_damage) {
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
    }
    public ItemVision(ItemVision item_vision) {
        this.items = new String[]{};
        this.fuel_time = item_vision.fuel_time;
        this.banned = item_vision.banned;
        this.fire_resistant = item_vision.fire_resistant;
        this.replacement = item_vision.replacement;
        this.pick_up_sound = item_vision.pick_up_sound;
        this.food_properties = item_vision.food_properties;
        this.break_replacement = item_vision.break_replacement;
        this.transfer_nbt = item_vision.transfer_nbt;
        this.enchantable = item_vision.enchantable;
        this.glinted = item_vision.glinted;
        this.min_damage = item_vision.min_damage;
        this.reinforcement = item_vision.reinforcement;
        this.max_damage = item_vision.max_damage;
    }
    public ItemVision(ItemVision vision_left,ItemVision vision_right){
        this.items = new String[]{};
        this.banned = group_vision_values(vision_left.banned,vision_right.banned).toArray(ItemVisionBoolean[]::new);
        this.replacement = group_vision_values(vision_left.replacement,vision_right.replacement).toArray(ItemVisionString[]::new);
        this.break_replacement = group_vision_values(vision_left.break_replacement,vision_right.break_replacement).toArray(ItemVisionString[]::new);
        this.pick_up_sound = group_vision_values(vision_left.pick_up_sound,vision_right.pick_up_sound).toArray(ItemVisionString[]::new);
        this.food_properties = group_vision_values(vision_left.food_properties,vision_right.food_properties).toArray(ItemVisionFoodProperties[]::new);
        this.fire_resistant = group_vision_values(vision_left.fire_resistant,vision_right.fire_resistant).toArray(ItemVisionBoolean[]::new);
        this.transfer_nbt = group_vision_values(vision_left.transfer_nbt,vision_right.transfer_nbt).toArray(ItemVisionBoolean[]::new);
        this.enchantable = group_vision_values(vision_left.enchantable,vision_right.enchantable).toArray(ItemVisionBoolean[]::new);
        this.glinted = group_vision_values(vision_left.glinted,vision_right.glinted).toArray(ItemVisionBoolean[]::new);
        this.fuel_time = group_vision_values(vision_left.fuel_time,vision_right.fuel_time).toArray(ItemVisionInteger[]::new);
        this.min_damage = group_vision_values(vision_left.min_damage,vision_right.min_damage).toArray(ItemVisionInteger[]::new);
        this.reinforcement = group_vision_values(vision_left.reinforcement,vision_right.reinforcement).toArray(ItemVisionInteger[]::new);
        this.max_damage = group_vision_values(vision_left.max_damage,vision_right.max_damage).toArray(ItemVisionInteger[]::new);
    }
    public Boolean get_banned(Item item) {
        return get_value(item,banned);
    }
    public Boolean get_fire_resistant(Item item) {
        return get_value(item,fire_resistant);
    }
    public String get_replacement(Item item) {
        return get_value(item,replacement);
    }
    public String get_break_replacement(Item item) {
        return get_value(item,break_replacement);
    }
    public String get_pick_up_sound(Item item) {
        return get_value(item,pick_up_sound);
    }
    public Boolean get_transfer_nbt(Item item) {
        return get_value(item,transfer_nbt);
    }
    public Boolean get_enchantable(Item item) {
        return get_value(item,enchantable);
    }
    public Integer get_min_damage(Item item) {
        return get_value(item,min_damage);
    }
    public Integer get_max_damage(Item item) {
        return get_value(item,max_damage);
    }
    public Boolean get_glinted(Item item) {
        return get_value(item,glinted);
    }
    public FoodProperties get_food_properties(Item item){
        return get_value(item,food_properties);
    }
    public String[] get_targets() {
        return get_targets(new Vector<>(),items,0, Registries.ITEM, RegistryKeys.ITEM);
    }
}
