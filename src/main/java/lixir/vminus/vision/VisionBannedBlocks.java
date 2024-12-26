package lixir.vminus.vision;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VisionBannedBlocks {
    String[] blocks;
    boolean banned;
    VisionBannedBlocks(String[] blocks, boolean banned){
        this.blocks = blocks;
        this.banned = banned;
    }
    public void getBlockTags() {
        for (String blockKey: blocks){
            if (blockKey.startsWith("#")){
                TagKey<Block> blockTagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(blockKey.substring(1)));
                VMinus.LOGGER.info("--------------------------------------{}",blockTagKey);
            }
        }
    }
}
