package net.lixir.vminus.mixin;

import net.lixir.vminus.vision.VisionHelper;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Vector;

@Mixin(Formatting.class)
public class FormattingMixin {
    @Shadow @Final @Mutable
    private static Formatting[] field_1072;
    @Unique
    private static final Formatting INDIGO = formatting_constructor("INDIGO", 'g', VisionHelper.hex("8653fc"));
    @Unique
    private static final Formatting LIGHT_PINK = formatting_constructor("LIGHT_PINK", 't',  VisionHelper.hex("f99dca"));
    @Unique
    private static final Formatting PINK = formatting_constructor("PINK", 'q',  VisionHelper.hex("f771b2"));
    @Unique
    private static final Formatting ORANGE = formatting_constructor("ORANGE", 'i', VisionHelper.hex("fc702a"));
    @Unique
    private static final Formatting CORAL = formatting_constructor("CORAL", 'p', VisionHelper.hex("fc8c5f"));
    @Unique
    private static final Formatting SKY_BLUE = formatting_constructor("SKY_BLUE", 'v', VisionHelper.hex("7badfc"));
    @Unique
    private static final Formatting PINE = formatting_constructor("PINE", 'n', VisionHelper.hex("629646"));
    @Unique
    private static final Formatting PLUM = formatting_constructor("PLUM", 'z', VisionHelper.hex("4d3b7f"));
    @Unique
    private static final Formatting TOOTHPASTE = formatting_constructor("TOOTHPASTE", 'h', VisionHelper.hex("2afcd2"));
    @Unique
    private static final Formatting NEON_YELLOW = formatting_constructor("NEON_YELLOW", 'y', VisionHelper.hex("fce305"));
    @Unique
    private static final Formatting BROWN = formatting_constructor("BROWN", 'j', VisionHelper.hex("82522e"));
    @Unique
    private static final Formatting DARK_BROWN = formatting_constructor("DARK_BROWN", 'u', VisionHelper.hex("3a2b1f"));
    @Unique
    private static final Formatting NEON_RED = formatting_constructor("NEON_RED", 'x', VisionHelper.hex("fc0521"));
    @Unique
    private static final Formatting COBALT = formatting_constructor("COBALT", 'y', VisionHelper.hex("0059ff"));
    @Invoker(value = "<init>")
    private static Formatting formatting_constructor(String internal_name, int internal_id, String name, char code, int colorIndex, @Nullable Integer colorValue) {
        throw new AssertionError();
    }
    @Unique
    private static Formatting formatting_constructor(String name, char character, int color) {
        Vector<Formatting> formats = new Vector<>(List.of(field_1072));
        int identifier = formats.size();
        Formatting formatting = formatting_constructor(name, identifier, name, character, identifier, color);
        formats.add(formatting);
        field_1072 = formats.toArray(Formatting[]::new);
        return formatting;
    }
}
