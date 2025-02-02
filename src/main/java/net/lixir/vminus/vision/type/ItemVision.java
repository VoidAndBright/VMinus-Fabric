package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.item.ItemVisionBoolean;
import net.lixir.vminus.vision.value.item.ItemVisionInteger;
import net.lixir.vminus.vision.value.item.ItemVisionString;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;


public class ItemVision implements Vision<Item> {
    private final String[] items;
    private final Item item;
    private final ItemVisionBoolean[] banned;
    private final ItemVisionString[] replacement;
    private final ItemVisionString[] break_replacement;
    private final ItemVisionBoolean[] fire_resistant;
    private final ItemVisionBoolean[] transfer_nbt;
    private final ItemVisionBoolean[] enchantable;
    private final ItemVisionBoolean[] glinted;
    private final ItemVisionInteger[] fuel_time;
    private final ItemVisionInteger[] min_damage;
    private final ItemVisionInteger[] reinforcement;
    private final ItemVisionInteger[] max_damage;


    public ItemVision(String[] items, ItemVisionBoolean[] banned, ItemVisionString[] replacement, ItemVisionString[] break_replacement, ItemVisionBoolean[] fire_resistant, ItemVisionBoolean[] transfer_nbt, ItemVisionBoolean[] enchantable, ItemVisionBoolean[] glinted, ItemVisionInteger[] fuel_time, ItemVisionInteger[] min_damage, ItemVisionInteger[] reinforcement, ItemVisionInteger[] max_damage) {
        this.items = items;
        this.item = null;
        this.banned = banned;
        this.replacement = replacement;
        this.break_replacement = break_replacement;
        this.fire_resistant = fire_resistant;
        this.transfer_nbt = transfer_nbt;
        this.enchantable = enchantable;
        this.glinted = glinted;
        this.fuel_time = fuel_time;
        this.min_damage = min_damage;
        this.reinforcement = reinforcement;
        this.max_damage = max_damage;
    }
    public ItemVision(Item item,ItemVision item_vision) {
        this.items = new String[]{};
        this.item = item;
        this.fuel_time = item_vision.fuel_time;
        this.banned = item_vision.banned;
        this.fire_resistant = item_vision.fire_resistant;
        this.replacement = item_vision.replacement;
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
        this.item = vision_left.item;
        this.banned = merge(vision_left.banned,vision_right.banned).toArray(ItemVisionBoolean[]::new);
        this.replacement = merge(vision_left.replacement,vision_right.replacement).toArray(ItemVisionString[]::new);
        this.break_replacement = merge(vision_left.break_replacement,vision_right.break_replacement).toArray(ItemVisionString[]::new);
        this.fire_resistant = merge(vision_left.fire_resistant,vision_right.fire_resistant).toArray(ItemVisionBoolean[]::new);
        this.transfer_nbt = merge(vision_left.transfer_nbt,vision_right.transfer_nbt).toArray(ItemVisionBoolean[]::new);
        this.enchantable = merge(vision_left.enchantable,vision_right.enchantable).toArray(ItemVisionBoolean[]::new);
        this.glinted = merge(vision_left.glinted,vision_right.glinted).toArray(ItemVisionBoolean[]::new);
        this.fuel_time = merge(vision_left.fuel_time,vision_right.fuel_time).toArray(ItemVisionInteger[]::new);
        this.min_damage = merge(vision_left.min_damage,vision_right.min_damage).toArray(ItemVisionInteger[]::new);
        this.reinforcement = merge(vision_left.reinforcement,vision_right.reinforcement).toArray(ItemVisionInteger[]::new);
        this.max_damage = merge(vision_left.max_damage,vision_right.max_damage).toArray(ItemVisionInteger[]::new);
    }
    public Boolean get_banned() {
        return get_value(banned);
    }
    public Boolean get_fire_resistant() {
        return get_value(fire_resistant);
    }
    public String get_replacement() {
        return get_value(replacement);
    }
    public String get_break_replacement() {
        return get_value(break_replacement);
    }
    public Boolean get_transfer_nbt() {
        return get_value(transfer_nbt);
    }
    public Boolean get_enchantable() {
        return get_value(enchantable);
    }
    public Integer get_min_damage() {
        return get_value(min_damage);
    }
    public Integer get_max_damage() {
        return get_value(max_damage);
    }
    public Boolean get_glinted() {
        return get_value(glinted);
    }

    public String[] get_targets() {
        return refine_entries(new Vector<>(),items,0, Registries.ITEM, RegistryKeys.ITEM);
    }

    public Item get_vision_type() {
        return item;
    }
}
