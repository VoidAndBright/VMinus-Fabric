package net.lixir.vminus.vision.deserializer;

import com.google.gson.*;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.vision.condition.ItemCondition;
import net.lixir.vminus.vision.value.item.ItemVisionBoolean;
import net.lixir.vminus.vision.type.ItemVision;

import java.lang.reflect.Type;

public class ItemVisionDeserializer implements JsonDeserializer<ItemVision> {
    public ItemVision deserialize(JsonElement json_element, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json_object = json_element.getAsJsonObject();
        String[] entries = json_object.get("items").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toArray(String[]::new);
        ItemVisionBoolean[] banned = item_vision_elements(json_object,"banned");
        VMinus.LOGGER.info("this is triggering");
        return new ItemVision(entries,banned,null,null,null,null,null,null,null,null,null,null);
    }
    public static ItemVisionBoolean[] item_vision_elements(JsonObject json_object, String name){
        return json_object.get(name).getAsJsonArray().asList().stream().map(json_element_banned -> item_vision_element(json_object,json_element_banned)).toArray(ItemVisionBoolean[]::new);
    }
    public static ItemVisionBoolean item_vision_element(JsonObject json_object, JsonElement json_element){
        String name = json_element.getAsString();
        Boolean value = json_object.get(name).getAsBoolean();
        String name_priority = name+"/priority";
        int priority = json_object.has(name_priority) ? json_object.get(name_priority).getAsInt() : 0;
        String name_conditions = name+"/conditions";
        ItemCondition[] conditions = json_object.has(name_conditions) ? json_object.get(name_conditions).getAsJsonArray().asList().stream().map(ItemVisionDeserializer::item_condition).toArray(ItemCondition[]::new):null;
        return new ItemVisionBoolean(value,priority,conditions);
    }
    public static ItemCondition item_condition(JsonElement json_element){
        JsonObject json_object = json_element.getAsJsonObject();
        boolean inverted = json_object.has("inverted") && json_object.get("inverted").getAsBoolean();
        String is_mod_loaded = json_object.has("is_mod_loaded") ? json_object.get("is_mod_loaded").getAsString():null;
        return new ItemCondition(inverted,is_mod_loaded);
    }
}
