package lixir.vminus.mixin;

import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Formatting.class)
public class FormattingMixin {
    @Shadow @Final @Mutable private static Formatting[] field_1072;

    @Unique
    private static final Formatting INDIGO = formatting_constructor("INDIGO", 'g', hex_to_int("8653fc"));
    @Unique
    private static final Formatting LIGHT_PINK = formatting_constructor("LIGHT_PINK", 't',  hex_to_int("f99dca"));
    @Unique
    private static final Formatting PINK = formatting_constructor("PINK", 'q',  hex_to_int("f771b2"));
    @Unique
    private static final Formatting ORANGE = formatting_constructor("ORANGE", 'i', hex_to_int("fc702a"));
    @Unique
    private static final Formatting CORAL = formatting_constructor("CORAL", 'p', hex_to_int("fc8c5f"));
    @Unique
    private static final Formatting SKY_BLUE = formatting_constructor("SKY_BLUE", 'v', hex_to_int("7badfc"));
    @Unique
    private static final Formatting PINE = formatting_constructor("PINE", 'n', hex_to_int("629646"));
    @Unique
    private static final Formatting PLUM = formatting_constructor("PLUM", 'z', hex_to_int("4d3b7f"));
    @Unique
    private static final Formatting TOOTHPASTE = formatting_constructor("TOOTHPASTE", 'h', hex_to_int("2afcd2"));
    @Unique
    private static final Formatting NEON_YELLOW = formatting_constructor("NEON_YELLOW", 'y', hex_to_int("fce305"));
    @Unique
    private static final Formatting BROWN = formatting_constructor("BROWN", 'j', hex_to_int("82522e"));
    @Unique
    private static final Formatting DARK_BROWN = formatting_constructor("DARK_BROWN", 'u', hex_to_int("3a2b1f"));
    @Unique
    private static final Formatting NEON_RED = formatting_constructor("NEON_RED", 'x', hex_to_int("fc0521"));
    @Unique
    private static final Formatting COBALT = formatting_constructor("COBALT", 'y', hex_to_int("0059ff"));

    @Invoker(value = "<init>")
    public static Formatting formatting_constructor(String internalName, int internalId, String name, char code, int colorIndex, @Nullable Integer colorValue) {
        throw new AssertionError();
    }

    @Unique
    private static Formatting formatting_constructor(String name, char character, int color) {
        ArrayList<Formatting> formats = new ArrayList<>(Arrays.asList(field_1072));
        int id = formats.size();
        Formatting formatting = formatting_constructor(name, id, name, character, id, color);
        formats.add(formatting);
        field_1072 = formats.toArray(new Formatting[0]);
        return formatting;
    }

    @Unique
    private static int hex_to_int(String hex) {
        if (hex.startsWith("#")) {
            return hex_to_int(hex.substring(1));
        }
        return Integer.parseInt(hex, 16);
    }
}
