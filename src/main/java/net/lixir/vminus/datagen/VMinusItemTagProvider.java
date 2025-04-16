package net.lixir.vminus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lixir.vminus.item.VMinusItems;
import net.lixir.vminus.util.tag.VMinusItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class VMinusItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public VMinusItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.COMMON)
                .add(VMinusItems.DEFAULTIUM_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.UNCOMMON)
                .add(VMinusItems.DEFAULTIUM_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.RARE)
                .add(VMinusItems.DEFAULTIUM_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.EPIC)
                .add(VMinusItems.DEFAULTIUM_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.ToolTipTags.HIDE_MINING_SPEED)
                .forceAddTag(ItemTags.SWORDS);

        getOrCreateTagBuilder(VMinusItemTags.ALL_CORAL)
                .forceAddTag(VMinusItemTags.CORAL)
                .forceAddTag(VMinusItemTags.DEAD_CORAL);

        getOrCreateTagBuilder(VMinusItemTags.ALL_CORAL_BLOCKS)
                .forceAddTag(VMinusItemTags.CORAL_BLOCKS)
                .forceAddTag(VMinusItemTags.DEAD_CORAL_BLOCKS);

        getOrCreateTagBuilder(VMinusItemTags.ALL_CORAL_FANS)
                .forceAddTag(VMinusItemTags.CORAL_FANS)
                .forceAddTag(VMinusItemTags.DEAD_CORAL_FANS);

        getOrCreateTagBuilder(VMinusItemTags.ALL_MEATS)
                .forceAddTag(VMinusItemTags.RAW_FISH)
                .forceAddTag(VMinusItemTags.COOKED_FISH)
                .forceAddTag(VMinusItemTags.COOKED_MEATS)
                .forceAddTag(VMinusItemTags.RAW_MEATS);

        getOrCreateTagBuilder(VMinusItemTags.ARMOR)
                .forceAddTag(VMinusItemTags.GOLDEN_ARMOR)
                .forceAddTag(VMinusItemTags.IRON_ARMOR)
                .forceAddTag(VMinusItemTags.DIAMOND_ARMOR)
                .forceAddTag(VMinusItemTags.NETHERITE_ARMOR)
                .forceAddTag(VMinusItemTags.LEATHER_ARMOR)
                .forceAddTag(VMinusItemTags.TURTLE_ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.ARMORS_AND_HORSE_ARMOR)
                .forceAddTag(VMinusItemTags.ARMOR)
                .forceAddTag(VMinusItemTags.HORSE_ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.ARROW_FIRING)
                .forceAddTag(VMinusItemTags.BOWS)
                .forceAddTag(VMinusItemTags.CROSSBOWS);

        getOrCreateTagBuilder(VMinusItemTags.ARMORS_AND_HORSE_ARMOR)
                .forceAddTag(VMinusItemTags.ARMOR)
                .forceAddTag(VMinusItemTags.HORSE_ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.BANNER_PATTERNS)
                .add(Items.FLOWER_BANNER_PATTERN)
                .add(Items.CREEPER_BANNER_PATTERN)
                .add(Items.SKULL_BANNER_PATTERN)
                .add(Items.MOJANG_BANNER_PATTERN)
                .add(Items.GLOBE_BANNER_PATTERN)
                .add(Items.PIGLIN_BANNER_PATTERN);

        getOrCreateTagBuilder(VMinusItemTags.BOATS)
                .add(Items.OAK_BOAT)
                .add(Items.SPRUCE_BOAT)
                .add(Items.BIRCH_BOAT)
                .add(Items.JUNGLE_BOAT)
                .add(Items.ACACIA_BOAT)
                .add(Items.DARK_OAK_BOAT)
                .add(Items.MANGROVE_BOAT)
                .add(Items.CHERRY_BOAT)
                .add(Items.BAMBOO_RAFT);

        getOrCreateTagBuilder(VMinusItemTags.BOWS)
                .add(Items.BOW);

        getOrCreateTagBuilder(VMinusItemTags.BOWS_AND_CROSSBOWS)
                .add(Items.BOW)
                .add(Items.CROSSBOW);

        getOrCreateTagBuilder(VMinusItemTags.CHAINMAIL_ARMOR)
                .add(Items.CHAINMAIL_HELMET)
                .add(Items.CHAINMAIL_CHESTPLATE)
                .add(Items.CHAINMAIL_LEGGINGS)
                .add(Items.CHAINMAIL_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.CHEST_BOATS)
                .add(Items.OAK_CHEST_BOAT)
                .add(Items.SPRUCE_CHEST_BOAT)
                .add(Items.BIRCH_CHEST_BOAT)
                .add(Items.JUNGLE_CHEST_BOAT)
                .add(Items.ACACIA_CHEST_BOAT)
                .add(Items.DARK_OAK_CHEST_BOAT)
                .add(Items.MANGROVE_CHEST_BOAT)
                .add(Items.CHERRY_CHEST_BOAT)
                .add(Items.BAMBOO_CHEST_RAFT);

        getOrCreateTagBuilder(VMinusItemTags.CONCRETE)
                .add(Items.WHITE_CONCRETE)
                .add(Items.ORANGE_CONCRETE)
                .add(Items.MAGENTA_CONCRETE)
                .add(Items.LIGHT_BLUE_CONCRETE)
                .add(Items.YELLOW_CONCRETE)
                .add(Items.LIME_CONCRETE)
                .add(Items.PINK_CONCRETE)
                .add(Items.LIGHT_GRAY_CONCRETE)
                .add(Items.CYAN_CONCRETE)
                .add(Items.PURPLE_CONCRETE)
                .add(Items.BLUE_CONCRETE)
                .add(Items.BROWN_CONCRETE)
                .add(Items.GREEN_CONCRETE)
                .add(Items.RED_CONCRETE)
                .add(Items.BLACK_CONCRETE);

        getOrCreateTagBuilder(VMinusItemTags.DYED_CONCRETE_POWDER)
                .add(Items.WHITE_CONCRETE_POWDER)
                .add(Items.ORANGE_CONCRETE_POWDER)
                .add(Items.MAGENTA_CONCRETE_POWDER)
                .add(Items.LIGHT_BLUE_CONCRETE_POWDER)
                .add(Items.YELLOW_CONCRETE_POWDER)
                .add(Items.LIME_CONCRETE_POWDER)
                .add(Items.PINK_CONCRETE_POWDER)
                .add(Items.LIGHT_GRAY_CONCRETE_POWDER)
                .add(Items.CYAN_CONCRETE_POWDER)
                .add(Items.PURPLE_CONCRETE_POWDER)
                .add(Items.BLUE_CONCRETE_POWDER)
                .add(Items.BROWN_CONCRETE_POWDER)
                .add(Items.GREEN_CONCRETE_POWDER)
                .add(Items.RED_CONCRETE_POWDER)
                .add(Items.BLACK_CONCRETE_POWDER);

        getOrCreateTagBuilder(VMinusItemTags.CONTAINERS)
                .add(Items.BARREL)
                .add(Items.CHEST)
                .add(Items.SHULKER_BOX)
                .forceAddTag(VMinusItemTags.CHEST_BOATS);

        getOrCreateTagBuilder(VMinusItemTags.COOKED_FISH)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_SALMON);

        getOrCreateTagBuilder(VMinusItemTags.COOKED_MEATS)
                .add(Items.COOKED_PORKCHOP)
                .add(Items.COOKED_BEEF)
                .add(Items.COOKED_CHICKEN)
                .add(Items.COOKED_RABBIT)
                .add(Items.COOKED_MUTTON);

        getOrCreateTagBuilder(VMinusItemTags.CORAL)
                .add(Items.TUBE_CORAL)
                .add(Items.BRAIN_CORAL)
                .add(Items.BUBBLE_CORAL)
                .add(Items.FIRE_CORAL)
                .add(Items.HORN_CORAL);

        getOrCreateTagBuilder(VMinusItemTags.CORAL_BLOCKS)
                .add(Items.TUBE_CORAL_BLOCK)
                .add(Items.BRAIN_CORAL_BLOCK)
                .add(Items.BUBBLE_CORAL_BLOCK)
                .add(Items.FIRE_CORAL_BLOCK)
                .add(Items.HORN_CORAL_BLOCK);

        getOrCreateTagBuilder(VMinusItemTags.CORAL_FANS)
                .add(Items.TUBE_CORAL_FAN)
                .add(Items.BRAIN_CORAL_FAN)
                .add(Items.BUBBLE_CORAL_FAN)
                .add(Items.FIRE_CORAL_FAN)
                .add(Items.HORN_CORAL_FAN);

        getOrCreateTagBuilder(VMinusItemTags.CROSSBOWS)
                .add(Items.CROSSBOW);

        getOrCreateTagBuilder(VMinusItemTags.DEAD_CORAL)
                .add(Items.DEAD_TUBE_CORAL)
                .add(Items.DEAD_BRAIN_CORAL)
                .add(Items.DEAD_BUBBLE_CORAL)
                .add(Items.DEAD_FIRE_CORAL)
                .add(Items.DEAD_HORN_CORAL);

        getOrCreateTagBuilder(VMinusItemTags.DEAD_CORAL_BLOCKS)
                .add(Items.DEAD_TUBE_CORAL_BLOCK)
                .add(Items.DEAD_BRAIN_CORAL_BLOCK)
                .add(Items.DEAD_BUBBLE_CORAL_BLOCK)
                .add(Items.DEAD_FIRE_CORAL_BLOCK)
                .add(Items.DEAD_HORN_CORAL_BLOCK);

        getOrCreateTagBuilder(VMinusItemTags.DEAD_CORAL_FANS)
                .add(Items.DEAD_TUBE_CORAL_FAN)
                .add(Items.DEAD_BRAIN_CORAL_FAN)
                .add(Items.DEAD_BUBBLE_CORAL_FAN)
                .add(Items.DEAD_FIRE_CORAL_FAN)
                .add(Items.DEAD_HORN_CORAL_FAN);

        getOrCreateTagBuilder(VMinusItemTags.DIAMOND_ARMOR)
                .add(Items.DIAMOND_HELMET)
                .add(Items.DIAMOND_CHESTPLATE)
                .add(Items.DIAMOND_LEGGINGS)
                .add(Items.DIAMOND_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.DIAMOND_EQUIPMENT)
                .forceAddTag(VMinusItemTags.DIAMOND_ARMOR)
                .forceAddTag(VMinusItemTags.DIAMOND_TOOLS);

        getOrCreateTagBuilder(VMinusItemTags.DIAMOND_TOOLS)
                .add(Items.DIAMOND_SWORD)
                .add(Items.DIAMOND_AXE)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.DIAMOND_SHOVEL)
                .add(Items.DIAMOND_HOE);

        getOrCreateTagBuilder(VMinusItemTags.DYED_BEDS)
                .add(Items.WHITE_BED)
                .add(Items.ORANGE_BED)
                .add(Items.MAGENTA_BED)
                .add(Items.LIGHT_BLUE_BED)
                .add(Items.YELLOW_BED)
                .add(Items.LIME_BED)
                .add(Items.PINK_BED)
                .add(Items.LIGHT_BLUE_BED)
                .add(Items.CYAN_BED)
                .add(Items.PURPLE_BED)
                .add(Items.BLUE_BED)
                .add(Items.BROWN_BED)
                .add(Items.GREEN_BED)
                .add(Items.RED_BED)
                .add(Items.BLACK_BED);

        getOrCreateTagBuilder(VMinusItemTags.DYED_CANDLES)
                .add(Items.WHITE_CANDLE)
                .add(Items.ORANGE_CANDLE)
                .add(Items.MAGENTA_CANDLE)
                .add(Items.LIGHT_BLUE_CANDLE)
                .add(Items.YELLOW_CANDLE)
                .add(Items.LIME_CANDLE)
                .add(Items.PINK_CANDLE)
                .add(Items.LIGHT_BLUE_CANDLE)
                .add(Items.CYAN_CANDLE)
                .add(Items.PURPLE_CANDLE)
                .add(Items.BLUE_CANDLE)
                .add(Items.BROWN_CANDLE)
                .add(Items.GREEN_CANDLE)
                .add(Items.RED_CANDLE)
                .add(Items.BLACK_CANDLE);

        getOrCreateTagBuilder(VMinusItemTags.DYED_CONCRETE)
                .add(Items.WHITE_CONCRETE)
                .add(Items.ORANGE_CONCRETE)
                .add(Items.MAGENTA_CONCRETE)
                .add(Items.LIGHT_BLUE_CONCRETE)
                .add(Items.YELLOW_CONCRETE)
                .add(Items.LIME_CONCRETE)
                .add(Items.PINK_CONCRETE)
                .add(Items.LIGHT_BLUE_CONCRETE)
                .add(Items.CYAN_CONCRETE)
                .add(Items.PURPLE_CONCRETE)
                .add(Items.BLUE_CONCRETE)
                .add(Items.BROWN_CONCRETE)
                .add(Items.GREEN_CONCRETE)
                .add(Items.RED_CONCRETE)
                .add(Items.BLACK_CONCRETE);

        getOrCreateTagBuilder(VMinusItemTags.DYED_TERRACOTTA)
                .add(Items.WHITE_TERRACOTTA)
                .add(Items.ORANGE_TERRACOTTA)
                .add(Items.MAGENTA_TERRACOTTA)
                .add(Items.LIGHT_BLUE_TERRACOTTA)
                .add(Items.YELLOW_TERRACOTTA)
                .add(Items.LIME_TERRACOTTA)
                .add(Items.PINK_TERRACOTTA)
                .add(Items.LIGHT_BLUE_TERRACOTTA)
                .add(Items.CYAN_TERRACOTTA)
                .add(Items.PURPLE_TERRACOTTA)
                .add(Items.BLUE_TERRACOTTA)
                .add(Items.BROWN_TERRACOTTA)
                .add(Items.GREEN_TERRACOTTA)
                .add(Items.RED_TERRACOTTA)
                .add(Items.BLACK_TERRACOTTA);

        getOrCreateTagBuilder(VMinusItemTags.DYED_WOOL)
                .add(Items.WHITE_WOOL)
                .add(Items.ORANGE_WOOL)
                .add(Items.MAGENTA_WOOL)
                .add(Items.LIGHT_BLUE_WOOL)
                .add(Items.YELLOW_WOOL)
                .add(Items.LIME_WOOL)
                .add(Items.PINK_WOOL)
                .add(Items.LIGHT_BLUE_WOOL)
                .add(Items.CYAN_WOOL)
                .add(Items.PURPLE_WOOL)
                .add(Items.BLUE_WOOL)
                .add(Items.BROWN_WOOL)
                .add(Items.GREEN_WOOL)
                .add(Items.RED_WOOL)
                .add(Items.BLACK_WOOL);

        getOrCreateTagBuilder(VMinusItemTags.EQUIPMENT)
                .forceAddTag(ItemTags.TOOLS)
                .forceAddTag(VMinusItemTags.ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.FISHING_RODS)
                .add(Items.FISHING_ROD);

        getOrCreateTagBuilder(VMinusItemTags.FROGLIGHTS)
                .add(Items.OCHRE_FROGLIGHT)
                .add(Items.VERDANT_FROGLIGHT)
                .add(Items.PEARLESCENT_FROGLIGHT);

        getOrCreateTagBuilder(VMinusItemTags.FRUITS)
                .add(Items.APPLE)
                .add(Items.GOLDEN_APPLE)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.SWEET_BERRIES)
                .add(Items.GLOW_BERRIES)
                .add(Items.CHORUS_FRUIT);

        getOrCreateTagBuilder(VMinusItemTags.STAINED_GLASS_PANES)
                .add(Items.WHITE_STAINED_GLASS_PANE)
                .add(Items.ORANGE_STAINED_GLASS_PANE)
                .add(Items.MAGENTA_STAINED_GLASS_PANE)
                .add(Items.LIGHT_BLUE_STAINED_GLASS_PANE)
                .add(Items.YELLOW_STAINED_GLASS_PANE)
                .add(Items.LIME_STAINED_GLASS_PANE)
                .add(Items.PINK_STAINED_GLASS_PANE)
                .add(Items.LIGHT_BLUE_STAINED_GLASS_PANE)
                .add(Items.CYAN_STAINED_GLASS_PANE)
                .add(Items.PURPLE_STAINED_GLASS_PANE)
                .add(Items.BLUE_STAINED_GLASS_PANE)
                .add(Items.BROWN_STAINED_GLASS_PANE)
                .add(Items.GREEN_STAINED_GLASS_PANE)
                .add(Items.RED_STAINED_GLASS_PANE)
                .add(Items.BLACK_STAINED_GLASS_PANE);

        getOrCreateTagBuilder(VMinusItemTags.GLASS_PANES)
                .add(Items.GLASS_PANE)
                .forceAddTag(VMinusItemTags.STAINED_GLASS_PANES);

        getOrCreateTagBuilder(VMinusItemTags.GOLDEN_ARMOR)
                .add(Items.GOLDEN_HELMET)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.GOLDEN_LEGGINGS)
                .add(Items.GOLDEN_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.GOLDEN_EQUIPMENT)
                .forceAddTag(VMinusItemTags.GOLDEN_ARMOR)
                .forceAddTag(VMinusItemTags.GOLDEN_TOOLS);

        getOrCreateTagBuilder(VMinusItemTags.GOLDEN_TOOLS)
                .add(Items.GOLDEN_SWORD)
                .add(Items.GOLDEN_AXE)
                .add(Items.GOLDEN_PICKAXE)
                .add(Items.GOLDEN_SHOVEL)
                .add(Items.GOLDEN_HOE);

        getOrCreateTagBuilder(VMinusItemTags.GRAIN)
                .add(Items.BREAD)
                .add(Items.WHEAT)
                .add(Items.WHEAT_SEEDS)
                .add(Items.HAY_BLOCK);

        getOrCreateTagBuilder(VMinusItemTags.HORSE_ARMOR)
                .add(Items.LEATHER_HORSE_ARMOR)
                .add(Items.IRON_HORSE_ARMOR)
                .add(Items.GOLDEN_HORSE_ARMOR)
                .add(Items.DIAMOND_HORSE_ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.IRON_ARMOR)
                .add(Items.IRON_HELMET)
                .add(Items.IRON_CHESTPLATE)
                .add(Items.IRON_LEGGINGS)
                .add(Items.IRON_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.IRON_EQUIPMENT)
                .forceAddTag(VMinusItemTags.IRON_ARMOR)
                .forceAddTag(VMinusItemTags.IRON_TOOLS);

        getOrCreateTagBuilder(VMinusItemTags.IRON_TOOLS)
                .add(Items.IRON_SWORD)
                .add(Items.IRON_AXE)
                .add(Items.IRON_PICKAXE)
                .add(Items.IRON_SHOVEL)
                .add(Items.IRON_HOE);

        getOrCreateTagBuilder(VMinusItemTags.LEATHER_ARMOR)
                .add(Items.LEATHER_HELMET)
                .add(Items.LEATHER_CHESTPLATE)
                .add(Items.LEATHER_LEGGINGS)
                .add(Items.LEATHER_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.LIGHTFOOTED)
                .add(Items.LEATHER_BOOTS)
                .add(Items.LEATHER_HORSE_ARMOR);

        getOrCreateTagBuilder(VMinusItemTags.MINECARTS)
                .add(Items.MINECART)
                .add(Items.CHEST_MINECART)
                .add(Items.FURNACE_MINECART)
                .add(Items.HOPPER_MINECART)
                .add(Items.TNT_MINECART)
                .add(Items.COMMAND_BLOCK_MINECART);

        getOrCreateTagBuilder(VMinusItemTags.MUSHROOMS)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM);

        getOrCreateTagBuilder(VMinusItemTags.NETHER_FUNGUS)
                .add(Items.WARPED_FUNGUS)
                .add(Items.CRIMSON_FUNGUS);

        getOrCreateTagBuilder(VMinusItemTags.NETHER_VINE)
                .add(Items.WEEPING_VINES)
                .add(Items.TWISTING_VINES);

        getOrCreateTagBuilder(VMinusItemTags.NETHERITE_ARMOR)
                .add(Items.NETHERITE_HELMET)
                .add(Items.NETHERITE_CHESTPLATE)
                .add(Items.NETHERITE_LEGGINGS)
                .add(Items.NETHERITE_BOOTS);

        getOrCreateTagBuilder(VMinusItemTags.NETHERITE_EQUIPMENT)
                .forceAddTag(VMinusItemTags.NETHERITE_ARMOR)
                .forceAddTag(VMinusItemTags.NETHERITE_TOOLS);

        getOrCreateTagBuilder(VMinusItemTags.NETHERITE_TOOLS)
                .add(Items.NETHERITE_SWORD)
                .add(Items.NETHERITE_AXE)
                .add(Items.NETHERITE_PICKAXE)
                .add(Items.NETHERITE_SHOVEL)
                .add(Items.NETHERITE_HOE);

        getOrCreateTagBuilder(VMinusItemTags.PICKAXES_AND_SHOVELS)
                .forceAddTag(ItemTags.PICKAXES)
                .forceAddTag(ItemTags.SHOVELS);

        getOrCreateTagBuilder(VMinusItemTags.RAW_FISH)
                .add(Items.COD)
                .add(Items.SALMON);

        getOrCreateTagBuilder(VMinusItemTags.RAW_MEATS)
                .add(Items.PORKCHOP)
                .add(Items.BEEF)
                .add(Items.CHICKEN)
                .add(Items.RABBIT)
                .add(Items.MUTTON);

        getOrCreateTagBuilder(VMinusItemTags.RAW_MEATS)
                .add(Items.PORKCHOP)
                .add(Items.BEEF)
                .add(Items.CHICKEN)
                .add(Items.RABBIT)
                .add(Items.MUTTON);

        getOrCreateTagBuilder(VMinusItemTags.SHULKER_BOXES)
                .add(Items.SHULKER_BOX)
                .forceAddTag(VMinusItemTags.DYED_SHULKER_BOXES);

        getOrCreateTagBuilder(VMinusItemTags.DYED_CARPETS)
                .add(Items.WHITE_CARPET)
                .add(Items.ORANGE_CARPET)
                .add(Items.MAGENTA_CARPET)
                .add(Items.LIGHT_BLUE_CARPET)
                .add(Items.YELLOW_CARPET)
                .add(Items.LIME_CARPET)
                .add(Items.PINK_CARPET)
                .add(Items.LIGHT_BLUE_CARPET)
                .add(Items.CYAN_CARPET)
                .add(Items.PURPLE_CARPET)
                .add(Items.BLUE_CARPET)
                .add(Items.BROWN_CARPET)
                .add(Items.GREEN_CARPET)
                .add(Items.RED_CARPET)
                .add(Items.BLACK_CARPET);

        getOrCreateTagBuilder(VMinusItemTags.DYED_SHULKER_BOXES)
                .add(Items.WHITE_SHULKER_BOX)
                .add(Items.ORANGE_SHULKER_BOX)
                .add(Items.MAGENTA_SHULKER_BOX)
                .add(Items.LIGHT_BLUE_SHULKER_BOX)
                .add(Items.YELLOW_SHULKER_BOX)
                .add(Items.LIME_SHULKER_BOX)
                .add(Items.PINK_SHULKER_BOX)
                .add(Items.LIGHT_BLUE_SHULKER_BOX)
                .add(Items.CYAN_SHULKER_BOX)
                .add(Items.PURPLE_SHULKER_BOX)
                .add(Items.BLUE_SHULKER_BOX)
                .add(Items.BROWN_SHULKER_BOX)
                .add(Items.GREEN_SHULKER_BOX)
                .add(Items.RED_SHULKER_BOX)
                .add(Items.BLACK_SHULKER_BOX);

        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(Items.STONE)
                .add(Items.GRANITE)
                .add(Items.DIORITE)
                .add(Items.ANDESITE)
                .add(Items.DEEPSLATE)
                .add(Items.BASALT)
                .add(Items.TUFF)
                .add(Items.CALCITE)
                .add(Items.SANDSTONE)
                .add(Items.RED_SANDSTONE)
                .add(Items.DRIPSTONE_BLOCK);

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .forceAddTag(ItemTags.STONE_CRAFTING_MATERIALS);

        getOrCreateTagBuilder(VMinusItemTags.ALL_BOATS)
                .forceAddTag(VMinusItemTags.BOATS)
                .forceAddTag(VMinusItemTags.CHEST_BOATS);

        getOrCreateTagBuilder(VMinusItemTags.STONE_TOOLS)
                .add(Items.STONE_SWORD)
                .add(Items.STONE_AXE)
                .add(Items.STONE_PICKAXE)
                .add(Items.STONE_SHOVEL)
                .add(Items.STONE_HOE);

        getOrCreateTagBuilder(VMinusItemTags.STRIPED_LOGS)
                .add(Items.STRIPPED_OAK_LOG)
                .add(Items.STRIPPED_SPRUCE_LOG)
                .add(Items.STRIPPED_BIRCH_LOG)
                .add(Items.STRIPPED_JUNGLE_LOG)
                .add(Items.STRIPPED_ACACIA_LOG)
                .add(Items.STRIPPED_DARK_OAK_LOG)
                .add(Items.STRIPPED_WARPED_STEM)
                .add(Items.STRIPPED_CRIMSON_STEM)
                .add(Items.STRIPPED_MANGROVE_LOG)
                .add(Items.STRIPPED_CHERRY_LOG)
                .add(Items.STRIPPED_BAMBOO_BLOCK)
                .add(Items.STRIPPED_OAK_WOOD)
                .add(Items.STRIPPED_SPRUCE_WOOD)
                .add(Items.STRIPPED_BIRCH_WOOD)
                .add(Items.STRIPPED_JUNGLE_WOOD)
                .add(Items.STRIPPED_ACACIA_WOOD)
                .add(Items.STRIPPED_DARK_OAK_WOOD)
                .add(Items.STRIPPED_WARPED_HYPHAE)
                .add(Items.STRIPPED_CRIMSON_HYPHAE)
                .add(Items.STRIPPED_MANGROVE_WOOD)
                .add(Items.STRIPPED_CHERRY_WOOD);

        getOrCreateTagBuilder(VMinusItemTags.TRIDENTS)
                .add(Items.TRIDENT);

        getOrCreateTagBuilder(VMinusItemTags.TURTLE_ARMOR)
                .add(Items.TURTLE_HELMET);

        getOrCreateTagBuilder(VMinusItemTags.UNENCHANTABLE);

        getOrCreateTagBuilder(VMinusItemTags.VEGETABLES)
                .add(Items.BEETROOT)
                .add(Items.POTATO)
                .add(Items.BAKED_POTATO)
                .add(Items.POISONOUS_POTATO)
                .add(Items.CARROT)
                .add(Items.GOLDEN_CARROT);
        getOrCreateTagBuilder(VMinusItemTags.WOODEN_TOOLS)
                .add(Items.WOODEN_SWORD)
                .add(Items.WOODEN_AXE)
                .add(Items.WOODEN_PICKAXE)
                .add(Items.WOODEN_SHOVEL)
                .add(Items.WOODEN_HOE);

    }
}