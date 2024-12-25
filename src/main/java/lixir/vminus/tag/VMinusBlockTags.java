package lixir.vminus.tag;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusBlockTags {
    public static final TagKey<Block> ALL_COPPER = registerTag("all_copper");
    public static final TagKey<Block> ALL_EXPOSED_COPPER = registerTag("all_exposed_copper");
    public static final TagKey<Block> ALL_OXIDIZED_COPPER = registerTag("all_oxidized_copper");
    public static final TagKey<Block> ALL_WEATHERED_COPPER = registerTag("all_weathered_copper");
    public static final TagKey<Block> CONCRETE_POWDER = registerTag("concrete_powder");
    public static final TagKey<Block> COPPER = registerTag("copper");
    public static final TagKey<Block> CORAL_BLOCKS = registerTag("coral_blocks");
    public static final TagKey<Block> EMISSIVE_RENDERING = registerTag("emissive_rendering");
    public static final TagKey<Block> All_OF_COPPER_SET = registerTag("all_of_copper_set");
    public static final TagKey<Block> EXPOSED_COPPER = registerTag("exposed_copper");
    public static final TagKey<Block> LARGE_PLANTS = registerTag("large_plants");
    public static final TagKey<Block> MOB_HEADS = registerTag("mob_heads");
    public static final TagKey<Block> OXIDIZED_COPPER = registerTag("oxidized_copper");
    public static final TagKey<Block> REDSTONE_TORCHES = registerTag("redstone_torches");
    public static final TagKey<Block> SOUL_TORCHES = registerTag("soul_torches");
    public static final TagKey<Block> STONE_REPLACEABLE = registerTag("stone_replaceable");
    public static final TagKey<Block> TORCHES = registerTag("torches");
    public static final TagKey<Block> WAXED_COPPER = registerTag("waxed_copper");
    public static final TagKey<Block> WAXED_EXPOSED_COPPER = registerTag("waxed_exposed_copper");
    public static final TagKey<Block> WAXED_OXIDIZED_COPPER = registerTag("waxed_oxidized_copper");
    public static final TagKey<Block> WAXED_WEATHERED_COPPER = registerTag("waxed_weathered_copper");
    public static final TagKey<Block> WEATHERED_COPPER = registerTag("weathered_copper");
    public static final TagKey<Block> FROGLIGHTS = registerTag("froglights");

    private static TagKey<Block> registerTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(VMinus.MOD_ID, name));
    }
}
