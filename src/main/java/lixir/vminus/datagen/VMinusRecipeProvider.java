package lixir.vminus.datagen;

import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.item.VMinusItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

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
