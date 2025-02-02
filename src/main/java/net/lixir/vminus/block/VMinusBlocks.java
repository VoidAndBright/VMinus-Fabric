package net.lixir.vminus.block;

import net.lixir.vminus.VMinus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class VMinusBlocks {
    public static final Block DEFAULTIUM_BLOCK = registerBlock("defaultium_block",new DefaultiumBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.NETHERITE).strength(1f, 10f).luminance(15)));
    private static <T extends Block> T registerBlock(String name,T block){
        Registry.register(Registries.ITEM, Identifier.of(VMinus.MOD_ID, name), new BlockItem(block,new FabricItemSettings()));
        return Registry.register(Registries.BLOCK,new Identifier(VMinus.MOD_ID,name),block);
    }
    private static void addBuildingBlocks(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.NETHERITE_BLOCK,DEFAULTIUM_BLOCK);
    }
    public static void register(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(VMinusBlocks::addBuildingBlocks);
    }
}
