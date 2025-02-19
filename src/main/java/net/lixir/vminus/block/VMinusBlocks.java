package net.lixir.vminus.block;

import net.lixir.vminus.VMinus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lixir.vminus.block.head.HeadBlock;
import net.lixir.vminus.block.head.VMinusHeadShapes;
import net.lixir.vminus.util.VMinusInstruments;
import net.minecraft.block.Block;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class VMinusBlocks {
    public static final Block DEFAULTIUM_BLOCK = register_block("defaultium_block",new DefaultiumBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.NETHERITE).strength(1f, 10f).luminance(15)));
    public static final Block DEFAULTIUM_HEAD = register_block("defaultium_head",new HeadBlock(VMinusHeadShapes.DEFAULT,FabricBlockSettings.create().instrument(VMinusInstruments.DEFAULTIUM).sounds(BlockSoundGroup.NETHERITE).strength(1f, 10f).luminance(15)));
    private static <T extends Block> T register_block(String name, T block){
        Registry.register(Registries.ITEM, Identifier.of(VMinus.MOD_ID, name), new BlockItem(block,new FabricItemSettings()));
        return Registry.register(Registries.BLOCK,new Identifier(VMinus.MOD_ID,name),block);
    }
    private static void add_building_blocks(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.NETHERITE_BLOCK,DEFAULTIUM_BLOCK,DEFAULTIUM_HEAD);
    }
    public static void register(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(VMinusBlocks::add_building_blocks);
    }
}
