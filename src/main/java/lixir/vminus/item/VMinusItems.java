package lixir.vminus.item;

import lixir.vminus.VMinus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusItems {
    public static final Item DEFAULT_ITEM = registerItem("defaultium_item",new DefaultiumItem());
    static <T extends Item> T registerItem(String name,T item){
        return Registry.register(Registries.ITEM,new Identifier(VMinus.MOD_ID,name),item);
    }
    public static void register(){}
}
