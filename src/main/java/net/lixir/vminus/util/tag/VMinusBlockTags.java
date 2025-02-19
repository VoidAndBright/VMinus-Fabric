package net.lixir.vminus.util.tag;

import net.lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusBlockTags {
    public static final TagKey<Block> ALL_COPPER = register_tag("all_copper");
    public static final TagKey<Block> ALL_EXPOSED_COPPER = register_tag("all_exposed_copper");
    public static final TagKey<Block> ALL_OXIDIZED_COPPER = register_tag("all_oxidized_copper");
    public static final TagKey<Block> ALL_WEATHERED_COPPER = register_tag("all_weathered_copper");
    public static final TagKey<Block> CONCRETE_POWDER = register_tag("concrete_powder");
    public static final TagKey<Block> COPPER = register_tag("copper");
    public static final TagKey<Block> CORAL_BLOCKS = register_tag("coral_blocks");
    public static final TagKey<Block> EMISSIVE_RENDERING = register_tag("emissive_rendering");
    public static final TagKey<Block> All_OF_COPPER_SET = register_tag("all_of_copper_set");
    public static final TagKey<Block> EXPOSED_COPPER = register_tag("exposed_copper");
    public static final TagKey<Block> LARGE_PLANTS = register_tag("large_plants");
    public static final TagKey<Block> MOB_HEADS = register_tag("mob_heads");
    public static final TagKey<Block> OXIDIZED_COPPER = register_tag("oxidized_copper");
    public static final TagKey<Block> REDSTONE_TORCHES = register_tag("redstone_torches");
    public static final TagKey<Block> SOUL_TORCHES = register_tag("soul_torches");
    public static final TagKey<Block> STONE_REPLACEABLE = register_tag("stone_replaceable");
    public static final TagKey<Block> TORCHES = register_tag("torches");
    public static final TagKey<Block> WAXED_COPPER = register_tag("waxed_copper");
    public static final TagKey<Block> WAXED_EXPOSED_COPPER = register_tag("waxed_exposed_copper");
    public static final TagKey<Block> WAXED_OXIDIZED_COPPER = register_tag("waxed_oxidized_copper");
    public static final TagKey<Block> WAXED_WEATHERED_COPPER = register_tag("waxed_weathered_copper");
    public static final TagKey<Block> WEATHERED_COPPER = register_tag("weathered_copper");
    public static final TagKey<Block> FROGLIGHTS = register_tag("froglights");

    private static TagKey<Block> register_tag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(VMinus.MOD_ID, name));
    }
}
