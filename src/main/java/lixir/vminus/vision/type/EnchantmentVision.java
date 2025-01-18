package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class EnchantmentVision {
    public String[] enchantments;
    private final VisionElement<Boolean>[] banned;

    public EnchantmentVision(EnchantmentVision enchantment_vision) {
        this.enchantments = null;
        this.banned = enchantment_vision.banned;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
}
