package net.lixir.vminus.cape;

import net.lixir.vminus.VMinus;
import net.minecraft.util.Identifier;

public enum Cape {
    NONE,
    BEEPER,
    SHROUD,
    GHOST,
    MARROW,
    PROTOTYPE,
    TROLL,
    PHOTON;

    public static final Identifier BEEPER_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/beeper_cape.png");
    public static final Identifier SHROUD_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/shroud_cape.png");
    public static final Identifier GHOST_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/ghost_cape.png");
    public static final Identifier MARROW_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/marrow_cape.png");
    public static final Identifier PROTOTYPE_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/prototype_cape.png");
    public static final Identifier TROLL_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/troll_cape.png");
    public static final Identifier PHOTON_CAPE_TEXTURE = new Identifier(VMinus.MOD_ID,"textures/cape/photon_cape.png");


    public static String to_string(Cape cape){
        return switch (cape){
            case BEEPER -> "beeper";
            case SHROUD -> "shroud";
            case GHOST -> "ghost";
            case MARROW -> "marrow";
            case PROTOTYPE -> "prototype";
            case TROLL -> "troll";
            case PHOTON -> "photon";
            default -> "none";
        };
    }
    public static Cape from_string(String string){
        return switch (string){
            case "beeper" -> BEEPER;
            case "shroud" -> SHROUD;
            case "ghost" -> GHOST;
            case "marrow" -> MARROW;
            case "prototype" -> PROTOTYPE;
            case "troll" -> TROLL;
            case "photon" -> PHOTON;
            default -> NONE;
        };
    }
}