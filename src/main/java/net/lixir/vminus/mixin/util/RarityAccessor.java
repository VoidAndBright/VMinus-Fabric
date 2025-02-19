package net.lixir.vminus.mixin.util;

import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Rarity.class)
public interface RarityAccessor {
    @Invoker("<init>")
    static Rarity rarity_constructor(String internal_name, int internal_identifier, Formatting formatting) {
        throw new AssertionError();
    }
}
