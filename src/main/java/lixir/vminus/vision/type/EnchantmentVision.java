package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class EnchantmentVision {
    public String[] enchantments;
    private final VisionElement<Boolean>[] banned;

    public EnchantmentVision(VisionElement<Boolean>[] banned) {
        this.banned = banned;
    }
    public Boolean get_banned(){
        if (banned == null) return null;
        return VisionElement.get_value(banned);
    }
}
