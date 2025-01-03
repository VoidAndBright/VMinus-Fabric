package lixir.vminus.tag;

import lixir.vminus.VMinus;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusItemTags {
    public static class RarityTags{
        public static final TagKey<Item> COMMON = registerTag("rarity/common");
        public static final TagKey<Item> UNCOMMON = registerTag("rarity/uncommon");
        public static final TagKey<Item> RARE = registerTag("rarity/rare");
        public static final TagKey<Item> EPIC = registerTag("rarity/epic");

        private static TagKey<Item> registerTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(VMinus.MOD_ID, name));
        }
    }
    public static class ToolTipTags {
        public static final TagKey<Item> HIDE_MINING_SPEED = registerTag("tooltip/hide_mining_speed");

        private static TagKey<Item> registerTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(VMinus.MOD_ID, name));
        }
    }
    public static final TagKey<Item> ALL_BOATS = registerTag("all_boats");
    public static final TagKey<Item> ALL_CORAL = registerTag("all_coral");
    public static final TagKey<Item> ALL_CORAL_BLOCKS = registerTag("all_coral_blocks");
    public static final TagKey<Item> ALL_CORAL_FANS = registerTag("all_coral_fans");
    public static final TagKey<Item> ALL_MEATS = registerTag("all_meats");
    public static final TagKey<Item> ARMOR = registerTag("armor");
    public static final TagKey<Item> ARMORS_AND_HORSE_ARMOR = registerTag("armor_and_horse_armor");
    public static final TagKey<Item> ARROW_FIRING = registerTag("arrow_firing");
    public static final TagKey<Item> BANNER_PATTERNS = registerTag("banner_patterns");
    public static final TagKey<Item> BOATS = registerTag("boats");
    public static final TagKey<Item> BOWS = registerTag("bows");
    public static final TagKey<Item> BOWS_AND_CROSSBOWS = registerTag("bows_and_crossbows");
    public static final TagKey<Item> CHAINMAIL_ARMOR = registerTag("chainmail_armor");
    public static final TagKey<Item> CHEST_BOATS = registerTag("chest_boats");
    public static final TagKey<Item> CONCRETE = registerTag("concrete");
    public static final TagKey<Item> DYED_CONCRETE_POWDER = registerTag("dyed_concrete_powder");
    public static final TagKey<Item> CONTAINERS = registerTag("containers");
    public static final TagKey<Item> COOKED_FISH = registerTag("cooked_fish");
    public static final TagKey<Item> COOKED_MEATS = registerTag("cooked_meats");
    public static final TagKey<Item> CORAL = registerTag("coral");
    public static final TagKey<Item> CORAL_BLOCKS = registerTag("coral_blocks");
    public static final TagKey<Item> CORAL_FANS = registerTag("coral_fans");
    public static final TagKey<Item> CROSSBOWS = registerTag("crossbows");
    public static final TagKey<Item> DEAD_CORAL = registerTag("dead_coral");
    public static final TagKey<Item> DEAD_CORAL_BLOCKS = registerTag("dead_coral_blocks");
    public static final TagKey<Item> DEAD_CORAL_FANS = registerTag("dead_coral_fans");
    public static final TagKey<Item> DIAMOND_ARMOR = registerTag("diamond_armor");
    public static final TagKey<Item> DIAMOND_EQUIPMENT = registerTag("diamond_equipment");
    public static final TagKey<Item> DIAMOND_TOOLS = registerTag("diamond_tools");
    public static final TagKey<Item> DYED_BEDS = registerTag("dyed_beds");
    public static final TagKey<Item> DYED_CANDLES = registerTag("dyed_candles");
    public static final TagKey<Item> DYED_CARPETS = registerTag("dyed_carpets");
    public static final TagKey<Item> DYED_CONCRETE = registerTag("dyed_concrete");
    public static final TagKey<Item> DYED_TERRACOTTA = registerTag("dyed_terracotta");
    public static final TagKey<Item> DYED_WOOL = registerTag("dyed_wools");
    public static final TagKey<Item> EQUIPMENT = registerTag("equipment");
    public static final TagKey<Item> FISHING_RODS = registerTag("fishing_rods");
    public static final TagKey<Item> FROGLIGHTS = registerTag("froglights");
    public static final TagKey<Item> FRUITS = registerTag("fruits");
    public static final TagKey<Item> GLASS_PANES = registerTag("glass_panes");
    public static final TagKey<Item> STAINED_GLASS_PANES = registerTag("stained_glass_panes");
    public static final TagKey<Item> GOLDEN_ARMOR = registerTag("golden_armor");
    public static final TagKey<Item> GOLDEN_EQUIPMENT = registerTag("golden_equipment");
    public static final TagKey<Item> GOLDEN_TOOLS = registerTag("golden_tools");
    public static final TagKey<Item> GRAIN = registerTag("grain");
    public static final TagKey<Item> HORSE_ARMOR = registerTag("horse_armor");
    public static final TagKey<Item> IRON_ARMOR = registerTag("iron_armor");
    public static final TagKey<Item> IRON_EQUIPMENT = registerTag("iron_equipment");
    public static final TagKey<Item> IRON_TOOLS = registerTag("iron_tools");
    public static final TagKey<Item> LEATHER_ARMOR = registerTag("leather_armor");
    public static final TagKey<Item> LIGHTFOOTED = registerTag("lightfooted");
    public static final TagKey<Item> MINECARTS = registerTag("minecarts");
    public static final TagKey<Item> MUSHROOMS = registerTag("mushrooms");
    public static final TagKey<Item> NETHER_FUNGUS = registerTag("nether_fungus");
    public static final TagKey<Item> NETHER_VINE = registerTag("nether_vine");
    public static final TagKey<Item> NETHERITE_ARMOR = registerTag("netherite_armor");
    public static final TagKey<Item> NETHERITE_EQUIPMENT = registerTag("netherite_equipment");
    public static final TagKey<Item> NETHERITE_TOOLS = registerTag("netherite_tools");
    public static final TagKey<Item> PICKAXES_AND_SHOVELS = registerTag("pickaxes_and_shovels");
    public static final TagKey<Item> RAW_FISH = registerTag("raw_fish");
    public static final TagKey<Item> RAW_MEATS = registerTag("raw_meats");
    public static final TagKey<Item> SHULKER_BOXES = registerTag("shulker_boxes");
    public static final TagKey<Item> DYED_SHULKER_BOXES = registerTag("dyed_shulker_boxes");
    public static final TagKey<Item> STONE_TOOLS = registerTag("stone_tools");
    public static final TagKey<Item> STRIPED_LOGS = registerTag("striped_logs");
    public static final TagKey<Item> TRIDENTS = registerTag("tridents");
    public static final TagKey<Item> TURTLE_ARMOR = registerTag("turtle_armor");
    public static final TagKey<Item> UNENCHANTABLE = registerTag("unenchantable");
    public static final TagKey<Item> VEGETABLES = registerTag("vegetables");
    public static final TagKey<Item> WOODEN_TOOLS = registerTag("wooden_tools");

    private static TagKey<Item> registerTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(VMinus.MOD_ID, name));
    }
}
