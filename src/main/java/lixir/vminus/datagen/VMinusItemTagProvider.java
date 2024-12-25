package lixir.vminus.datagen;

import lixir.vminus.item.VMinusItems;
import lixir.vminus.tag.VMinusItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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
                .add(VMinusItems.DEFAULT_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.UNCOMMON)
                .add(VMinusItems.DEFAULT_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.RARE)
                .add(VMinusItems.DEFAULT_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.RarityTags.EPIC)
                .add(VMinusItems.DEFAULT_ITEM);

        getOrCreateTagBuilder(VMinusItemTags.ToolTipTags.HIDE_MINING_SPEED)
                .forceAddTag(ItemTags.SWORDS);

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
    }
}