package lixir.vminus.datagen;

import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.tag.VMinusBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class VMinusBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public VMinusBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(Blocks.CACTUS)
                .forceAddTag(BlockTags.BEDS);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(Blocks.HONEYCOMB_BLOCK)
                .add(Blocks.BIG_DRIPLEAF)
                .add(Blocks.BIG_DRIPLEAF_STEM)
                .add(Blocks.SMALL_DRIPLEAF)
                .add(Blocks.VINE)
                .forceAddTag(VMinusBlockTags.FROGLIGHTS);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(Blocks.TURTLE_EGG)
                .add(Blocks.SNIFFER_EGG)
                .add(Blocks.REDSTONE_LAMP)
                .add(Blocks.GLOWSTONE)
                .add(Blocks.GLASS)
                .add(Blocks.BEACON)
                .add(Blocks.CANDLE)
                .add(Blocks.END_ROD)
                .forceAddTag(VMinusBlockTags.MOB_HEADS)
                .add(Blocks.LEVER);

        getOrCreateTagBuilder(VMinusBlockTags.CONCRETE_POWDER)
                .add(Blocks.WHITE_CONCRETE_POWDER)
                .add(Blocks.ORANGE_CONCRETE_POWDER)
                .add(Blocks.MAGENTA_CONCRETE_POWDER)
                .add(Blocks.LIGHT_BLUE_CONCRETE_POWDER)
                .add(Blocks.YELLOW_CONCRETE_POWDER)
                .add(Blocks.LIME_CONCRETE_POWDER)
                .add(Blocks.PINK_CONCRETE_POWDER)
                .add(Blocks.GRAY_CONCRETE_POWDER)
                .add(Blocks.LIGHT_GRAY_CONCRETE_POWDER)
                .add(Blocks.CYAN_CONCRETE_POWDER)
                .add(Blocks.PURPLE_CONCRETE_POWDER)
                .add(Blocks.BLUE_CONCRETE_POWDER)
                .add(Blocks.BROWN_CONCRETE_POWDER)
                .add(Blocks.GREEN_CONCRETE_POWDER)
                .add(Blocks.RED_CONCRETE_POWDER)
                .add(Blocks.BLACK_CONCRETE_POWDER);

        getOrCreateTagBuilder(VMinusBlockTags.COPPER)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.CUT_COPPER)
                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.CORAL_BLOCKS)
                .add(Blocks.TUBE_CORAL_BLOCK)
                .add(Blocks.BRAIN_CORAL_BLOCK)
                .add(Blocks.BUBBLE_CORAL_BLOCK)
                .add(Blocks.FIRE_CORAL_BLOCK)
                .add(Blocks.HORN_CORAL_BLOCK);

        getOrCreateTagBuilder(VMinusBlockTags.EMISSIVE_RENDERING)
                .add(VMinusBlocks.DEFAULTIUM_BLOCK);

        getOrCreateTagBuilder(VMinusBlockTags.EXPOSED_COPPER)
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.EXPOSED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.LARGE_PLANTS)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.SUNFLOWER)
                .add(Blocks.LILAC)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.TALL_SEAGRASS)
                .add(Blocks.ROSE_BUSH)
                .add(Blocks.PEONY);

        getOrCreateTagBuilder(VMinusBlockTags.MOB_HEADS)
                .add(Blocks.SKELETON_SKULL)
                .add(Blocks.SKELETON_WALL_SKULL)
                .add(Blocks.WITHER_SKELETON_SKULL)
                .add(Blocks.WITHER_SKELETON_WALL_SKULL)
                .add(Blocks.ZOMBIE_HEAD)
                .add(Blocks.ZOMBIE_WALL_HEAD)
                .add(Blocks.PLAYER_HEAD)
                .add(Blocks.PLAYER_WALL_HEAD)
                .add(Blocks.CREEPER_HEAD)
                .add(Blocks.CREEPER_WALL_HEAD)
                .add(Blocks.DRAGON_HEAD)
                .add(Blocks.DRAGON_WALL_HEAD)
                .add(Blocks.PIGLIN_HEAD)
                .add(Blocks.PIGLIN_WALL_HEAD);

        getOrCreateTagBuilder(VMinusBlockTags.OXIDIZED_COPPER)
                .add(Blocks.OXIDIZED_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.OXIDIZED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.REDSTONE_TORCHES)
                .add(Blocks.REDSTONE_TORCH)
                .add(Blocks.REDSTONE_WALL_TORCH);

        getOrCreateTagBuilder(VMinusBlockTags.SOUL_TORCHES)
                .add(Blocks.SOUL_TORCH)
                .add(Blocks.SOUL_WALL_TORCH);

        getOrCreateTagBuilder(VMinusBlockTags.STONE_REPLACEABLE)
                .add(Blocks.DEEPSLATE)
                .add(Blocks.STONE);

        getOrCreateTagBuilder(VMinusBlockTags.TORCHES)
                .add(Blocks.TORCH)
                .add(Blocks.WALL_TORCH);

        getOrCreateTagBuilder(VMinusBlockTags.WAXED_COPPER)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.WAXED_CUT_COPPER)
                .add(Blocks.WAXED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.WAXED_EXPOSED_COPPER)
                .add(Blocks.WAXED_EXPOSED_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.WAXED_OXIDIZED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.WAXED_WEATHERED_COPPER)
                .add(Blocks.WAXED_WEATHERED_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB);

        getOrCreateTagBuilder(VMinusBlockTags.WEATHERED_COPPER)
                .add(Blocks.WEATHERED_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB);
        getOrCreateTagBuilder(VMinusBlockTags.ALL_COPPER)
                .forceAddTag(VMinusBlockTags.COPPER)
                .forceAddTag(VMinusBlockTags.WAXED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.ALL_COPPER)
                .forceAddTag(VMinusBlockTags.COPPER)
                .forceAddTag(VMinusBlockTags.WAXED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.ALL_EXPOSED_COPPER)
                .forceAddTag(VMinusBlockTags.EXPOSED_COPPER)
                .forceAddTag(VMinusBlockTags.WAXED_EXPOSED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.ALL_OXIDIZED_COPPER)
                .forceAddTag(VMinusBlockTags.OXIDIZED_COPPER)
                .forceAddTag(VMinusBlockTags.WAXED_OXIDIZED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.ALL_WEATHERED_COPPER)
                .forceAddTag(VMinusBlockTags.WEATHERED_COPPER)
                .forceAddTag(VMinusBlockTags.WAXED_WEATHERED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.All_OF_COPPER_SET)
                .forceAddTag(VMinusBlockTags.ALL_COPPER)
                .forceAddTag(VMinusBlockTags.ALL_WEATHERED_COPPER)
                .forceAddTag(VMinusBlockTags.ALL_EXPOSED_COPPER)
                .forceAddTag(VMinusBlockTags.ALL_OXIDIZED_COPPER);

        getOrCreateTagBuilder(VMinusBlockTags.FROGLIGHTS)
                .add(Blocks.OCHRE_FROGLIGHT)
                .add(Blocks.VERDANT_FROGLIGHT)
                .add(Blocks.PEARLESCENT_FROGLIGHT);

    }
}