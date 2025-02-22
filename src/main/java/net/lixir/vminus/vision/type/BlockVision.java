package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.value.block.BlockVisionBoolean;
import net.lixir.vminus.vision.value.block.BlockVisionFloat;
import net.lixir.vminus.vision.value.block.BlockVisionInteger;
import net.lixir.vminus.vision.value.block.BlockVisionSoundGroup;
import net.lixir.vminus.vision.value.block.BlockVisionString;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Vector;


public class BlockVision {
    private final String[] blocks;
    private final BlockVisionBoolean[] banned;
    private final BlockVisionString[] replacement;
    private final BlockVisionFloat[] slipperiness;
    private final BlockVisionFloat[] speed_multiplier;
    private final BlockVisionFloat[] jump_multiplier;
    private final BlockVisionFloat[] blast_resistance;
    private final BlockVisionFloat[] hardness;
    private final BlockVisionInteger[] luminance;
	private final BlockVisionInteger[] pitch;
	private final BlockVisionSoundGroup[] sound_group;
    private final BlockVisionString[] direction;

    public BlockVision(BlockVision left_vision, BlockVision right_vision){
        this.blocks = new String[]{};
        this.slipperiness = VisionHelper.collect_vision_values(left_vision.slipperiness,right_vision.slipperiness,BlockVisionFloat[]::new);
        this.speed_multiplier = VisionHelper.collect_vision_values(left_vision.speed_multiplier,right_vision.speed_multiplier,BlockVisionFloat[]::new);
        this.jump_multiplier = VisionHelper.collect_vision_values(left_vision.jump_multiplier,right_vision.jump_multiplier,BlockVisionFloat[]::new);
        this.blast_resistance = VisionHelper.collect_vision_values(left_vision.blast_resistance,right_vision.blast_resistance,BlockVisionFloat[]::new);
        this.hardness = VisionHelper.collect_vision_values(left_vision.hardness,right_vision.hardness,BlockVisionFloat[]::new);
        this.luminance = VisionHelper.collect_vision_values(left_vision.luminance,right_vision.luminance,BlockVisionInteger[]::new);
        this.banned = VisionHelper.collect_vision_values(left_vision.banned,right_vision.banned,BlockVisionBoolean[]::new);
        this.replacement = VisionHelper.collect_vision_values(left_vision.replacement,right_vision.replacement,BlockVisionString[]::new);
        this.pitch = VisionHelper.collect_vision_values(left_vision.pitch,right_vision.pitch,BlockVisionInteger[]::new);
        this.sound_group = VisionHelper.collect_vision_values(left_vision.sound_group,right_vision.sound_group,BlockVisionSoundGroup[]::new);
        this.direction = VisionHelper.collect_vision_values(left_vision.direction,right_vision.direction,BlockVisionString[]::new);
    }
    public BlockVision(String[] blocks,BlockVisionBoolean[] banned, BlockVisionString[] replacement, BlockVisionFloat[] slipperiness, BlockVisionFloat[] speed_multiplier, BlockVisionFloat[] jump_multiplier, BlockVisionFloat[] blast_resistance, BlockVisionFloat[] hardness, BlockVisionInteger[] luminance, BlockVisionInteger[] pitch, BlockVisionSoundGroup[] sound_group, BlockVisionString[] direction) {
        this.blocks = blocks;
        this.banned = banned;
        this.replacement = replacement;
        this.slipperiness = slipperiness;
        this.speed_multiplier = speed_multiplier;
        this.jump_multiplier = jump_multiplier;
        this.blast_resistance = blast_resistance;
        this.hardness = hardness;
        this.luminance = luminance;
        this.pitch = pitch;
        this.sound_group = sound_group;
        this.direction = direction;
    }

    public BlockSoundGroup get_sound_group(Block block) {
        return VisionHelper.block_sound_group(VisionHelper.vision_value(block,sound_group));
    }

    public Integer get_luminance(Block block) {
        return VisionHelper.vision_value(block,luminance);
    }

    public Float get_hardness(Block block) {
        return VisionHelper.vision_value(block,hardness);
    }

    public Float get_blast_resistance(Block block) {
        return VisionHelper.vision_value(block,blast_resistance);
    }

    public Float get_jump_multiplier(Block block) {
        return VisionHelper.vision_value(block,jump_multiplier);
    }

    public Float get_speed_multiplier(Block block) {
        return VisionHelper.vision_value(block,speed_multiplier);
    }

    public Float get_slipperiness(Block block) {
        return VisionHelper.vision_value(block,slipperiness);
    }

    public Block get_replacement(Block block) {
        return VisionHelper.block(VisionHelper.vision_value(block,replacement));
    }

    public Boolean get_banned(Block block) {
        return VisionHelper.vision_value(block,banned);
    }

    public String get_direction(Block block) {
        return VisionHelper.vision_value(block,direction);
    }

    public Block[] get_blocks(Vector<Block> vector_blocks , int index){
        if (index < blocks.length){
            String block_entry = blocks[index];
            if (block_entry.startsWith("*")){
                return Registries.BLOCK.stream().toArray(Block[]::new);
            }
            else if (block_entry.startsWith("!#")) {
                String block_tag =  block_entry.substring(2);
                TagKey<Block> block_tag_key = TagKey.of(RegistryKeys.BLOCK, new Identifier(block_tag));
                vector_blocks.removeAll(Registries.BLOCK.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (block_entry.startsWith("#")) {
                String block_tag = block_entry.substring(1);
                TagKey<Block> block_tag_key = TagKey.of(RegistryKeys.BLOCK, new Identifier(block_tag));
                vector_blocks.addAll(Registries.BLOCK.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (block_entry.startsWith("!")) vector_blocks.remove(Registries.BLOCK.get(new Identifier(block_entry.substring(1))));
            else vector_blocks.add(Registries.BLOCK.get(new Identifier(block_entry)));
            return get_blocks(vector_blocks,index+1);
        }
        return vector_blocks.toArray(Block[]::new);
    }
}
