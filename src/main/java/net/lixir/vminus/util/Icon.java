package net.lixir.vminus.util;


import net.minecraft.util.Formatting;

public enum Icon implements VMinusIcon {
    BLAST_PROTECTION(Formatting.RESET,'\uEFE5'),
    INSPECT_BAUBLE(Formatting.DARK_GREEN,'\uEEE7');
    private final String character;
    Icon(Formatting format,char character){
        this.character = "§"+format.getCode()+character;
    }
    @Override
    public String get_icon() {
        return character;
    }
}
