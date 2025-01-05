package lixir.vminus.vision.visions;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BlockVision {
    String[] blocks;
    float slipperiness;
    float speed_multiplier;
    float jump_multiplier;
    float blast_resistance;
    boolean banned;
    public BlockVision(String[] block, float slipperiness, float speed_multiplier, float jump_multiplier, float blast_resistance, boolean banned){
        this.blocks = block;
        this.slipperiness = slipperiness;
        this.speed_multiplier = speed_multiplier;
        this.jump_multiplier = jump_multiplier;
        this.blast_resistance = blast_resistance;
        this.banned = banned;
    }
    public void addVision(){

    }
    public void processVision() {
        for (String string : blocks) {
            VMinus.LOGGER.info("{}", string);
            if (string.startsWith("#")) {
                String tag = string.substring(1);
                TagKey<Block> blockTagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(tag));
               if (Blocks.BLACK_BED.asItem().getDefaultStack().isIn(ItemTags.BEDS)){
                   VMinus.LOGGER.info("{}", "Mmm this check is working");
               }
                VMinus.LOGGER.info("{}", blockTagKey.toString());
                for (Block block : Registries.BLOCK){
                    if (block.getDefaultState().isIn(blockTagKey)){
                        Visions.BLOCK_DATA.put(Registries.BLOCK.getRawId(block),this);
                        VMinus.LOGGER.info("{}", block.getTranslationKey());
                    }
                }
            }
        }
    }

    public float getSlipperiness() {
        return slipperiness;
    }
    public float getSpeedMultiplier() {
        return speed_multiplier;
    }
    public float getJumpMultiplier() {
        return jump_multiplier;
    }
    public float getBlastResistance() {
        return blast_resistance;
    }
    public boolean getBanned() {
        return banned;
    }
}
