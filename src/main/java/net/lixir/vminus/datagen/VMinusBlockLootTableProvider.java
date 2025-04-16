package net.lixir.vminus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.lixir.vminus.block.VMinusBlocks;

public class VMinusBlockLootTableProvider extends FabricBlockLootTableProvider {
    public VMinusBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generate() {
        addDrop(VMinusBlocks.DEFAULTIUM_BLOCK);
    }
}
