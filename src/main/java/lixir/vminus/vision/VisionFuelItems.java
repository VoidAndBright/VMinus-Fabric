package lixir.vminus.vision;

import lixir.vminus.VMinus;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VisionFuelItems {
    String[] items;
    boolean banned;
    VisionFuelItems(String[] items, boolean banned){
        this.items = items;
        this.banned = banned;
    }
    public void getItemTags() {
        for (String blockKey: items){
            if (blockKey.startsWith("#")){
                TagKey<Item> tagKey = TagKey.of(RegistryKeys.ITEM, Identifier.tryParse(blockKey.substring(1)));
                VisionData.FUEL_ITEM_VISION_TAG_CACHE.add(tagKey);
                VMinus.LOGGER.info("--------------------------------------{}",tagKey);
            }
        }
    }
}
