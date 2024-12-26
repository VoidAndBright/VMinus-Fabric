package lixir.vminus.vision;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CopyOnWriteArrayList;

public class VisionData {
    public static final CopyOnWriteArrayList<TagKey<?>> BANNED_ITEM_VISION_CACHE = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<TagKey<Item>> FUEL_ITEM_VISION_TAG_CACHE = new CopyOnWriteArrayList<>();
    public static void load_fuels(){
        for (TagKey<Item> tagKey : FUEL_ITEM_VISION_TAG_CACHE) {
            FuelRegistry.INSTANCE.add(tagKey,20);
        }
    }
}
