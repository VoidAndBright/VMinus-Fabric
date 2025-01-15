package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;
import lixir.vminus.vision.properties.SoundGroup;

public class BlockVision {
    public String[] blocks;
    private final VisionElement<Boolean>[] banned;
    private final VisionElement<String>[] replace;
    private final VisionElement<Float>[] slipperiness;
    private final VisionElement<Float>[] speed_multiplier;
    private final VisionElement<Float>[] jump_multiplier;
    private final VisionElement<Float>[] blast_resistance;
    private final VisionElement<Float>[] hardness;
    private final VisionElement<Integer>[] luminance;
	private final VisionElement<Integer>[] volume;
	private final VisionElement<Integer>[] pitch;
	private final VisionElement<SoundGroup>[] sound_group;
    public BlockVision(BlockVision blockVision){
        this.blocks = null;
        this.slipperiness = blockVision.slipperiness;
        this.speed_multiplier = blockVision.speed_multiplier;
        this.jump_multiplier = blockVision.jump_multiplier;
        this.blast_resistance = blockVision.blast_resistance;
        this.hardness = blockVision.hardness;
        this.luminance = blockVision.luminance;
        this.banned = blockVision.banned;
        this.replace = blockVision.replace;
        this.volume = blockVision.volume;
        this.pitch = blockVision.pitch;
        this.sound_group = blockVision.sound_group;
    }

    public SoundGroup get_sound_group() {
        if(sound_group == null) return null;

        return VisionElement.get_value(sound_group);
    }

    public Integer get_pitch() {
        if(pitch == null) return null;

        return VisionElement.get_value(pitch);
    }

    public Integer get_volume() {
        if(volume == null) return null;

        return VisionElement.get_value(volume);
    }

    public Integer get_luminance() {
        if(luminance == null) return null;

        return VisionElement.get_value(luminance);
    }

    public Float get_hardness() {
        if(hardness == null) return null;

        return VisionElement.get_value(hardness);
    }

    public Float get_blast_resistance() {
        if(blast_resistance == null) return null;

        return VisionElement.get_value(blast_resistance);
    }

    public Float get_jump_multiplier() {
        if(jump_multiplier == null) return null;

        return VisionElement.get_value(jump_multiplier);
    }

    public Float get_speed_multiplier() {
        if(speed_multiplier == null) return null;

        return VisionElement.get_value(speed_multiplier);
    }

    public Float get_slipperiness() {
        if(slipperiness == null) return null;

        return VisionElement.get_value(slipperiness);
    }

    public String get_replace() {
        if(replace == null) return null;

        return VisionElement.get_value(replace);
    }

    public Boolean get_banned() {
        if(banned == null) return null;

        return VisionElement.get_value(banned);
    }

    public String[] get_blocks() {
        if(blocks == null) return null;

        return blocks;
    }
}
