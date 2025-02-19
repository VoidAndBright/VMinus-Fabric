package net.lixir.vminus.util.tag;

import net.lixir.vminus.VMinus;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusItemTags {
    public static class RarityTags{
        public static final TagKey<Item> COMMON = register_tag("rarity/common");
        public static final TagKey<Item> UNCOMMON = register_tag("rarity/uncommon");
        public static final TagKey<Item> RARE = register_tag("rarity/rare");
        public static final TagKey<Item> EPIC = register_tag("rarity/epic");
    }
    public static class ToolTipTags {
        public static final TagKey<Item> HIDE_MINING_SPEED = register_tag("tooltip/hide_mining_speed");
    }
    public static final TagKey<Item> ALL_BOATS = register_tag("all_boats");
    public static final TagKey<Item> ALL_CORAL = register_tag("all_coral");
    public static final TagKey<Item> ALL_CORAL_BLOCKS = register_tag("all_coral_blocks");
    public static final TagKey<Item> ALL_CORAL_FANS = register_tag("all_coral_fans");
    public static final TagKey<Item> ALL_MEATS = register_tag("all_meats");
    public static final TagKey<Item> ARMOR = register_tag("armor");
    public static final TagKey<Item> ARMORS_AND_HORSE_ARMOR = register_tag("armor_and_horse_armor");
    public static final TagKey<Item> ARROW_FIRING = register_tag("arrow_firing");
    public static final TagKey<Item> BANNER_PATTERNS = register_tag("banner_patterns");
    public static final TagKey<Item> BOATS = register_tag("boats");
    public static final TagKey<Item> BOWS = register_tag("bows");
    public static final TagKey<Item> BOWS_AND_CROSSBOWS = register_tag("bows_and_crossbows");
    public static final TagKey<Item> CHAINMAIL_ARMOR = register_tag("chainmail_armor");
    public static final TagKey<Item> CHEST_BOATS = register_tag("chest_boats");
    public static final TagKey<Item> CONCRETE = register_tag("concrete");
    public static final TagKey<Item> DYED_CONCRETE_POWDER = register_tag("dyed_concrete_powder");
    public static final TagKey<Item> CONTAINERS = register_tag("containers");
    public static final TagKey<Item> COOKED_FISH = register_tag("cooked_fish");
    public static final TagKey<Item> COOKED_MEATS = register_tag("cooked_meats");
    public static final TagKey<Item> CORAL = register_tag("coral");
    public static final TagKey<Item> CORAL_BLOCKS = register_tag("coral_blocks");
    public static final TagKey<Item> CORAL_FANS = register_tag("coral_fans");
    public static final TagKey<Item> CROSSBOWS = register_tag("crossbows");
    public static final TagKey<Item> DEAD_CORAL = register_tag("dead_coral");
    public static final TagKey<Item> DEAD_CORAL_BLOCKS = register_tag("dead_coral_blocks");
    public static final TagKey<Item> DEAD_CORAL_FANS = register_tag("dead_coral_fans");
    public static final TagKey<Item> DIAMOND_ARMOR = register_tag("diamond_armor");
    public static final TagKey<Item> DIAMOND_EQUIPMENT = register_tag("diamond_equipment");
    public static final TagKey<Item> DIAMOND_TOOLS = register_tag("diamond_tools");
    public static final TagKey<Item> DYED_BEDS = register_tag("dyed_beds");
    public static final TagKey<Item> DYED_CANDLES = register_tag("dyed_candles");
    public static final TagKey<Item> DYED_CARPETS = register_tag("dyed_carpets");
    public static final TagKey<Item> DYED_CONCRETE = register_tag("dyed_concrete");
    public static final TagKey<Item> DYED_TERRACOTTA = register_tag("dyed_terracotta");
    public static final TagKey<Item> DYED_WOOL = register_tag("dyed_wools");
    public static final TagKey<Item> EQUIPMENT = register_tag("equipment");
    public static final TagKey<Item> FISHING_RODS = register_tag("fishing_rods");
    public static final TagKey<Item> FROGLIGHTS = register_tag("froglights");
    public static final TagKey<Item> FRUITS = register_tag("fruits");
    public static final TagKey<Item> GLASS_PANES = register_tag("glass_panes");
    public static final TagKey<Item> STAINED_GLASS_PANES = register_tag("stained_glass_panes");
    public static final TagKey<Item> GOLDEN_ARMOR = register_tag("golden_armor");
    public static final TagKey<Item> GOLDEN_EQUIPMENT = register_tag("golden_equipment");
    public static final TagKey<Item> GOLDEN_TOOLS = register_tag("golden_tools");
    public static final TagKey<Item> GRAIN = register_tag("grain");
    public static final TagKey<Item> HORSE_ARMOR = register_tag("horse_armor");
    public static final TagKey<Item> IRON_ARMOR = register_tag("iron_armor");
    public static final TagKey<Item> IRON_EQUIPMENT = register_tag("iron_equipment");
    public static final TagKey<Item> IRON_TOOLS = register_tag("iron_tools");
    public static final TagKey<Item> LEATHER_ARMOR = register_tag("leather_armor");
    public static final TagKey<Item> LIGHTFOOTED = register_tag("lightfooted");
    public static final TagKey<Item> MINECARTS = register_tag("minecarts");
    public static final TagKey<Item> MUSHROOMS = register_tag("mushrooms");
    public static final TagKey<Item> NETHER_FUNGUS = register_tag("nether_fungus");
    public static final TagKey<Item> NETHER_VINE = register_tag("nether_vine");
    public static final TagKey<Item> NETHERITE_ARMOR = register_tag("netherite_armor");
    public static final TagKey<Item> NETHERITE_EQUIPMENT = register_tag("netherite_equipment");
    public static final TagKey<Item> NETHERITE_TOOLS = register_tag("netherite_tools");
    public static final TagKey<Item> PICKAXES_AND_SHOVELS = register_tag("pickaxes_and_shovels");
    public static final TagKey<Item> RAW_FISH = register_tag("raw_fish");
    public static final TagKey<Item> RAW_MEATS = register_tag("raw_meats");
    public static final TagKey<Item> SHULKER_BOXES = register_tag("shulker_boxes");
    public static final TagKey<Item> DYED_SHULKER_BOXES = register_tag("dyed_shulker_boxes");
    public static final TagKey<Item> STONE_TOOLS = register_tag("stone_tools");
    public static final TagKey<Item> STRIPED_LOGS = register_tag("striped_logs");
    public static final TagKey<Item> TRIDENTS = register_tag("tridents");
    public static final TagKey<Item> TURTLE_ARMOR = register_tag("turtle_armor");
    public static final TagKey<Item> UNENCHANTABLE = register_tag("unenchantable");
    public static final TagKey<Item> VEGETABLES = register_tag("vegetables");
    public static final TagKey<Item> WOODEN_TOOLS = register_tag("wooden_tools");

    private static TagKey<Item> register_tag(String name) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(VMinus.MOD_ID, name));
    }
}
