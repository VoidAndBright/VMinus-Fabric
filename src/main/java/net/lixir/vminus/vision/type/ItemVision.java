package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;

public class ItemVision implements Vision {
    public String[] items;
    private final VisionElement<Integer>[] fuel_time;
    private final VisionElement<Boolean>[] banned;
    private final VisionElement<Boolean>[] fire_resistant;
    private final VisionElement<String>[] replacement;
    private final VisionElement<String>[] break_replacement;
    private final VisionElement<Boolean>[] transfer_nbt;
    private final VisionElement<Boolean>[] enchantable;
    private final VisionElement<Boolean>[] glinted;
    private final VisionElement<Integer>[] min_damage;
    private final VisionElement<Integer>[] reinforcement;
    private final VisionElement<Integer>[] max_damage;
    public ItemVision(ItemVision item_vision){
        this.items = null;
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

    @Override
    public String[] get_targets() {
        return new String[0];
    }
}
