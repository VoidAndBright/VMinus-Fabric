package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.VisionValue;
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

    public BlockVision(Block block,BlockVision blockVision){
        this.blocks = new String[]{};
        //this.block = block;
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

    public SoundGroup get_sound_group() {
        return get_value(sound_group);
    }

    public Integer get_luminance() {
        return get_value(luminance);
    }

    public Float get_hardness() {
        return get_value(hardness);
    }

    public Float get_blast_resistance() {
        return get_value(blast_resistance);
    }

    public Float get_jump_multiplier() {
        return get_value(jump_multiplier);
    }

    public Float get_speed_multiplier() {
        return get_value(speed_multiplier);
    }

    public Float get_slipperiness() {
        return get_value(slipperiness);
    }

    public String get_replacement() {
        return get_value(replacement);
    }

    public Boolean get_banned() {
        return get_value(banned);
    }

    public String get_direction() {
        return get_value(direction);
    }

    public String[] get_targets() {
        return refine_entries(new Vector<>(),blocks,0,Registries.BLOCK,RegistryKeys.BLOCK);
    }

    public Block get_vision_type() {
        return null;
    }
}
