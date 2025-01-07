package lixir.vminus.block;

import lixir.vminus.VMinus;
import lixir.vminus.item.VMinusItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusBlocks {
    public static final Block DEFAULTIUM_BLOCK = registerBlock("defaultium_block",new DefaultiumBlock());
    static <T extends Block> T registerBlock(String name,T block){
        Registry.register(Registries.ITEM, Identifier.of(VMinus.MOD_ID, name), new DefaultiumBlockItem(block));

        return Registry.register(Registries.BLOCK,new Identifier(VMinus.MOD_ID,name),block);
    }
    private static void addBuildingBlocks(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.NETHERITE_BLOCK,DEFAULTIUM_BLOCK);
    }
    public static void register(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(VMinusBlocks::addBuildingBlocks);
    }
}
