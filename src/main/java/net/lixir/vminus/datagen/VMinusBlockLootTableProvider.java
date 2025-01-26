package net.lixir.vminus.datagen;

import net.lixir.vminus.block.VMinusBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class VMinusBlockLootTableProvider extends FabricBlockLootTableProvider {
    public VMinusBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generate() {
        addDrop(VMinusBlocks.DEFAULTIUM_BLOCK);
    }
}
