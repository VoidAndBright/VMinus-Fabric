package net.lixir.vminus.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.util.VMinusRarities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusItems {
    public static final Item DEFAULTIUM_ITEM = register_item("defaultium_item",new DefaultiumItem(new FabricItemSettings().rarity(VMinusRarities.UNOBTAINABLE)));

    static <T extends Item> T register_item(String name, T item){
        return Registry.register(Registries.ITEM,new Identifier(VMinus.MOD_ID,name),item);
    }

    public static void register(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(VMinusItems::addIngredients);
    }

    private static void addIngredients(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.BLAZE_ROD,DEFAULTIUM_ITEM);
    }
}
