package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.*;

public class BlockVision implements Vision {
    public String[] blocks;
    private final VisionElement<Boolean>[] banned;
    private final VisionElement<String>[] replacement;
    private final VisionElement<Float>[] slipperiness;
    private final VisionElement<Float>[] speed_multiplier;
    private final VisionElement<Float>[] jump_multiplier;
    private final VisionElement<Float>[] blast_resistance;
    private final VisionElement<Float>[] hardness;
    private final VisionElement<Integer>[] luminance;
	private final VisionElement<Integer>[] pitch;
	private final VisionElement<SoundGroup>[] sound_group;
    private final VisionElement<String>[] direction;

    public BlockVision(BlockVision blockVision){
        this.blocks = null;
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
        return processed_entries(new ArrayList<>(),blocks,0,Registries.BLOCK,RegistryKeys.BLOCK);
    }
}
