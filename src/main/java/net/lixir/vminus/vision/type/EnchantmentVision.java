package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;

public class EnchantmentVision implements Vision{
    public String[] enchantments;
    private final VisionElement<Boolean>[] banned;

    public EnchantmentVision(EnchantmentVision enchantment_vision) {
        this.enchantments = null;
        this.banned = enchantment_vision.banned;
    }
    public Boolean get_banned(){
        return get_value(banned);
    }

    public String[] get_targets() {
        return new String[0];
    }
}
