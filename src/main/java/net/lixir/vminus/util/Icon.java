package net.lixir.vminus.util;


import net.minecraft.util.Formatting;

public enum Icon implements VMinusIcon {
    ATTACK_DAMAGE(Formatting.RESET,'\uFFF2'),
    ATTACK_SPEED(Formatting.RESET,'\uFFF1'),
    ATTACK_RANGE(Formatting.RESET,'\uFFF3'),
    ARMOUR_TOUGHNESS(Formatting.RESET,'\uEFF0'),
    CRITICAL_DAMAGE(Formatting.RESET,'\uEFE3'),
    LUCK(Formatting.RESET,'\uEEE1'),
    KNOCKBACK_RESISTANCE(Formatting.RESET,'\uEEE0'),
    JUMP_STRENGTH(Formatting.RESET,'\uEEE3'),
    HEALTH(Formatting.RESET,'\uEEE2'),
    SPEED(Formatting.RESET,'\uEFE9'),
    HELMET(Formatting.RESET,'\uEFF1'),
    CHESTPLATE(Formatting.RESET,'\uEFF2'),
    LEGGINGS(Formatting.RESET,'\uEFF3'),
    BOOTS(Formatting.RESET,'\uEFF4'),
    HORSE_ARMOUR(Formatting.RESET,'\uEFF5'),
    SHIELD(Formatting.RESET,'\uEEE8'),
    SWORD(Formatting.RESET,'\uFFF4'),
    PICKAXE(Formatting.RESET,'\uFFF5'),
    AXE(Formatting.RESET,'\uFFF6'),
    SHOVEL(Formatting.RESET,'\uFFF7'),
    HOE(Formatting.RESET,'\uFFF8'),
    BOW(Formatting.RESET,'\uEFF6'),
    CROSSBOW(Formatting.RESET,'\uEFF7'),
    REPEATER(Formatting.RESET,'\uEFF9'),
    FISHING_ROD(Formatting.RESET,'\uEFF8'),
    ANVIL(Formatting.RESET,'\uEFE0'),
    RUNE(Formatting.RESET,'\uEFE1'),
    REINFORCED(Formatting.RESET,'\uEFE2'),
    SHIMMERED(Formatting.RESET,'\uEEE6'),
    INSPECT(Formatting.RESET,'\uEEE4'),
    INSPECT_HELD(Formatting.RESET,'\uEEE5'),
    INSPECT_BAUBLE(Formatting.RESET,'\uEEE7'),
    SATURATION(Formatting.RESET,'\uEEE9'),
    HUNGER_SHANK(Formatting.RESET,'\uEF0E'),
    FAST_HUNGER_SHANK(Formatting.RESET,'\uEF1E'),
    SLOW_HUNGER_SHANK(Formatting.RESET,'\uEF2E'),
    EATING_DURATION(Formatting.RESET,'\uEF3E'),
    POSITIVE_EFFECT(Formatting.RESET,'\uEF4E'),
    NEGATIVE_EFFECT(Formatting.RESET,'\uEF5E'),
    FIRE_PROTECTION(Formatting.RESET,'\uEFE4'),
    BLAST_PROTECTION(Formatting.RESET,'\uEFE5'),
    MAGIC_PROTECTION(Formatting.RESET,'\uEFE6'),
    FALL_PROTECTION(Formatting.RESET,'\uEFE7'),
    MOB_DETECTION_RANGE(Formatting.RESET,'\uEF7E'),
    BLOCK_REACH(Formatting.RESET,'\uEF6E'),
    LUMINANCE(Formatting.RESET,'\uEF8E'),
    DEATH_DURABILITY(Formatting.RESET,'\uEF01'),
    HEALTH_LOST_STAT_BOOST(Formatting.RESET,'\uEF01');

    private final String character;
    Icon(Formatting format,char character){
        this.character = "§"+format.getCode()+character;
    }
    public String get_icon() {
        return character;
    }
}
