package lixir.vminus.datagen;

import lixir.vminus.block.DefaultBlock;
import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.item.VMinusItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class VMinusModelProvider extends FabricModelProvider {
    public VMinusModelProvider(FabricDataOutput output) {
        super(output);
    }
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(VMinusBlocks.DEFAULT_BLOCK);
    }
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(VMinusItems.DEFAULT_ITEM, Models.GENERATED);
    }
}
