package lixir.vminus.vision.visions;

import lixir.vminus.VMinus;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BlockVision {
    String[] blocks;
    float slipperiness;
    float speed_multiplier;
    float jump_multiplier;
    float blast_resistance;
    boolean banned;
    BlockVision(String[] block, float slipperiness, float speed_multiplier, float jump_multiplier, float blast_resistance, boolean banned){
        this.blocks = block;
        this.slipperiness = slipperiness;
        this.speed_multiplier = speed_multiplier;
        this.jump_multiplier = jump_multiplier;
        this.blast_resistance = blast_resistance;
        this.banned = banned;
    }
    public void addVision() {
        for (String entry : this.blocks) {
            if (entry.startsWith("#")) {
                entry = entry.substring(1);
                TagKey<Block> tagKey = TagKey.of(RegistryKeys.BLOCK, Identifier.splitOn(entry, ':'));
                for (RegistryEntry<Block> block : Registries.BLOCK.iterateEntries(tagKey)) {
                    Visions.BLOCK_VISIONS.put(block.value(), this);
                    VMinus.LOGGER.info("Hey why this no work{}", block.value().getTranslationKey());
                }
                VMinus.LOGGER.info("{}", entry);
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
