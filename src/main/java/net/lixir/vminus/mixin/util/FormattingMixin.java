package net.lixir.vminus.mixin.util;

import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.*;

import java.util.List;
import java.util.Vector;

@Mixin(Formatting.class)
public class FormattingMixin {
    @Shadow @Final @Mutable
    private static Formatting[] field_1072;
    static {
        new_formatting("INDIGO", 'g', hex("8653fc"));
        new_formatting("LIGHT_PINK", 't', hex("f99dca"));
        new_formatting("PINK", 'q', hex("f771b2"));
        new_formatting("ORANGE", 'i', hex("fc702a"));
        new_formatting("CORAL", 'p', hex("fc8c5f"));
        new_formatting("SKY_BLUE", 'v', hex("7badfc"));
        new_formatting("PINE", 'n', hex("629646"));
        new_formatting("PLUM", 'z', hex("4d3b7f"));
        new_formatting("TOOTHPASTE", 'h', hex("2afcd2"));
        new_formatting("NEON_YELLOW", 'y', hex("fce305"));
        new_formatting("BROWN", 'j', hex("82522e"));
        new_formatting("DARK_BROWN", 'u', hex("3a2b1f"));
        new_formatting("NEON_RED", 'x', hex("fc0521"));
        new_formatting("COBALT", 'y', hex("0059ff"));
    }
    @Unique
    private static void new_formatting(String name, char character, int color) {
        Vector<Formatting> formats = new Vector<>(List.of(field_1072));
        int identifier = formats.size();
        formats.add(FormattingAccessor.formatting_constructor(name, identifier, name, character, identifier, color));
        field_1072 = formats.toArray(Formatting[]::new);
    }
    @Unique
    private static int hex(String hex) {
        return Integer.parseInt(hex, 16);
    }
}
