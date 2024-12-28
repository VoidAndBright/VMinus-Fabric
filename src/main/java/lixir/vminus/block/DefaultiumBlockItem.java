package lixir.vminus.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class DefaultiumBlockItem extends BlockItem {
    public DefaultiumBlockItem(Block block) {
        super(block, new FabricItemSettings());
    }
}
