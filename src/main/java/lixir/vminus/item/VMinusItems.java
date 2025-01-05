package lixir.vminus.item;

import lixir.vminus.VMinus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.transfer.context.CreativeInteractionContainerItemContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

public class VMinusItems {
    public static final Item DEFAULTIUM_ITEM = registerItem("defaultium_item",new DefaultiumItem());
    static <T extends Item> T registerItem(String name,T item){
        return Registry.register(Registries.ITEM,new Identifier(VMinus.MOD_ID,name),item);
    }
    public static void register(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(VMinusItems::addIngredients);
    }

    private static void addIngredients(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.GOLD_INGOT,DEFAULTIUM_ITEM);
    }
}
