package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.properties.VisionSoundGroup;
import net.lixir.vminus.vision.value.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Vector;


public class BlockVision {
    private final String[] blocks;
    private final Vector<BlockVisionBoolean> banned;
    private final Vector<BlockVisionString> replacement;
    private final Vector<BlockVisionFloat> slipperiness;
    private final Vector<BlockVisionFloat> speed_multiplier;
    private final Vector<BlockVisionFloat> jump_multiplier;
    private final Vector<BlockVisionFloat> blast_resistance;
    private final Vector<BlockVisionFloat> hardness;
    private final Vector<BlockVisionInteger> luminance;
	private final Vector<BlockVisionInteger> pitch;
	private final Vector<BlockVisionSoundGroup> sound_group;
    private final Vector<BlockVisionString> direction;

    public BlockVision(BlockVision base,BlockVision added) {
        this.blocks = new String[]{};
        this.banned = Vision.sum(base.banned,added.banned);
        this.replacement = Vision.sum(base.replacement,added.replacement);
        this.slipperiness = Vision.sum(base.slipperiness,added.slipperiness);
        this.speed_multiplier = Vision.sum(base.speed_multiplier,added.speed_multiplier);
        this.jump_multiplier = Vision.sum(base.jump_multiplier,added.jump_multiplier);
        this.blast_resistance = Vision.sum(base.blast_resistance,added.blast_resistance);
        this.hardness = Vision.sum(base.hardness,added.hardness);
        this.luminance = Vision.sum(base.luminance,added.luminance);
        this.pitch = Vision.sum(base.pitch,added.pitch);
        this.sound_group = Vision.sum(base.sound_group,added.sound_group);
        this.direction = Vision.sum(base.direction,added.direction);
    }


    public BlockSoundGroup get_sound_group(Block block) {
        return VisionSoundGroup.sound_group(Vision.unwrap(block,sound_group));
    }

    public Integer get_luminance(Block block) {
        return Vision.unwrap(block,luminance);
    }

    public Float get_hardness(Block block) {
        return Vision.unwrap(block,hardness);
    }

    public Float get_blast_resistance(Block block) {
        return Vision.unwrap(block,blast_resistance);
    }

    public Float get_jump_multiplier(Block block) {
        return Vision.unwrap(block,jump_multiplier);
    }

    public Float get_speed_multiplier(Block block) {
        return Vision.unwrap(block,speed_multiplier);
    }

    public Float get_slipperiness(Block block) {
        return Vision.unwrap(block,slipperiness);
    }

    public Block get_replacement(Block block) {
        String string = Vision.unwrap(block,replacement);
        if (string == null) return null;
        return Registries.BLOCK.get(new Identifier(string));
    }

    public Boolean get_banned(Block block) {
        return Vision.unwrap(block,banned);
    }

    public Direction get_direction(Block block) {
        String string = Vision.unwrap(block,direction);
        if (string == null) return null;
        return switch (string){
            case "west" -> Direction.WEST;
            case "east" -> Direction.EAST;
            case "up" -> Direction.UP;
            case "down" -> Direction.DOWN;
            case "south" -> Direction.SOUTH;
            default -> Direction.NORTH;
        };
    }

    public Vector<Block> get_blocks(){
        Vector<Block> vector_blocks = new Vector<>(0);
        for (String block_target:blocks) {
            if (block_target.startsWith("*")) {
                String string_wild_card = block_target.substring(1);
                vector_blocks.addAll(Registries.BLOCK.stream().filter(block -> Registries.BLOCK.getId(block).toString().endsWith(string_wild_card)).toList());
            } else if (block_target.startsWith("!#")) {
                String block_tag = block_target.substring(2);
                TagKey<Block> block_tag_key = TagKey.of(RegistryKeys.BLOCK, new Identifier(block_tag));
                Registries.BLOCK.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList().forEach(vector_blocks::remove);
            } else if (block_target.startsWith("#")) {
                String block_tag = block_target.substring(1);
                TagKey<Block> block_tag_key = TagKey.of(RegistryKeys.BLOCK, new Identifier(block_tag));
                vector_blocks.addAll(Registries.BLOCK.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList());
            } else if (block_target.startsWith("!"))
                vector_blocks.remove(Registries.BLOCK.get(new Identifier(block_target.substring(1))));
            else vector_blocks.add(Registries.BLOCK.get(new Identifier(block_target)));
        }
        vector_blocks.remove(Blocks.AIR);
        return vector_blocks;
    }
}
