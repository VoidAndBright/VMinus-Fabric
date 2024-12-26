package net.lixir.vminus.events;

import com.google.gson.JsonObject;
import net.lixir.vminus.network.VMinusVariables;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class FMLCommonSetupEventHandler {
    
    public static void init(FMLCommonSetupEvent event) {
        VMinusVariables.main_item_vision = new JsonObject();
        VMinusVariables.main_block_vision = new JsonObject();
        VMinusVariables.main_entity_vision = new JsonObject();
        VMinusVariables.main_enchantment_vision = new JsonObject();
        VMinusVariables.main_effect_vision = new JsonObject();
    }
}
