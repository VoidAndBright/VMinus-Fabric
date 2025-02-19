package net.lixir.vminus.mixin.util;

import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(Formatting.class)
public interface FormattingAccessor {
    @Invoker(value = "<init>")
    static Formatting formatting_constructor(String internal_name, int internal_identifier, String name, char code, int color_index, @Nullable Integer color_value) {
        throw new AssertionError();
    }
}
