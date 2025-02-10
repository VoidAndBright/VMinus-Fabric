package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.block.BlockVisionBoolean;
import net.lixir.vminus.vision.value.block.BlockVisionFloat;
import net.lixir.vminus.vision.value.block.BlockVisionInteger;
import net.lixir.vminus.vision.value.block.BlockVisionSoundGroup;
import net.lixir.vminus.vision.value.block.BlockVisionString;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import java.util.Vector;


public class BlockVision implements Vision<Block> {
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

    public BlockVision(BlockVision blockVision){
        this.blocks = new String[]{};
        this.slipperiness = blockVision.slipperiness;
        this.speed_multiplier = blockVision.speed_multiplier;
        this.jump_multiplier = blockVision.jump_multiplier;
        this.blast_resistance = blockVision.blast_resistance;
        this.hardness = blockVision.hardness;
        this.luminance = blockVision.luminance;
        this.banned = blockVision.banned;
        this.replacement = blockVision.replacement;
        this.pitch = blockVision.pitch;
        this.sound_group = blockVision.sound_group;
        this.direction = blockVision.direction;
    }
    public BlockVision(String[] blocks, BlockVisionBoolean[] banned, BlockVisionString[] replacement, BlockVisionFloat[] slipperiness, BlockVisionFloat[] speed_multiplier, BlockVisionFloat[] jump_multiplier, BlockVisionFloat[] blast_resistance, BlockVisionFloat[] hardness, BlockVisionInteger[] luminance, BlockVisionInteger[] pitch, BlockVisionSoundGroup[] sound_group, BlockVisionString[] direction) {
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

    public SoundGroup get_sound_group(Block block) {
        return get_value(block,sound_group);
    }

    public Integer get_luminance(Block block) {
        return get_value(block,luminance);
    }

    public Float get_hardness(Block block) {
        return get_value(block,hardness);
    }

    public Float get_blast_resistance(Block block) {
        return get_value(block,blast_resistance);
    }

    public Float get_jump_multiplier(Block block) {
        return get_value(block,jump_multiplier);
    }

    public Float get_speed_multiplier(Block block) {
        return get_value(block,speed_multiplier);
    }

    public Float get_slipperiness(Block block) {
        return get_value(block,slipperiness);
    }

    public String get_replacement(Block block) {
        return get_value(block,replacement);
    }

    public Boolean get_banned(Block block) {
        return get_value(block,banned);
    }

    public String get_direction(Block block) {
        return get_value(block,direction);
    }

    public String[] get_targets() {
        return get_targets(new Vector<>(),blocks,0,Registries.BLOCK,RegistryKeys.BLOCK);
    }
}
