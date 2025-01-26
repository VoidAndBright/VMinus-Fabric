package net.lixir.vminus.datagen;

import net.lixir.vminus.block.VMinusBlocks;
import net.lixir.vminus.item.VMinusItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class VMinusRecipeProvider extends FabricRecipeProvider {
    public VMinusRecipeProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        offerReversibleCompactingRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, VMinusItems.DEFAULTIUM_ITEM, RecipeCategory.MISC,
                VMinusBlocks.DEFAULTIUM_BLOCK);

    }
}
