package net.lixir.vminus.util;

import net.lixir.vminus.mixin.util.RarityAccessor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

public class VMinusUtil {

    public static Rarity Rarity(Formatting formatting) {
        return RarityAccessor.rarity_constructor("", 0, formatting);
    }
}
