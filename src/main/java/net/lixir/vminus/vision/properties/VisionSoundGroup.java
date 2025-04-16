package net.lixir.vminus.vision.properties;

import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class VisionSoundGroup {
    private final Float volume;
    private final Float pitch;
    private final String break_sound;
    private final String step_sound;
    private final String place_sound;
    private final String hit_sound;
    private final String fall_sound;

    public VisionSoundGroup(Float volume, Float pitch, String break_sound, String step_sound, String place_sound, String hit_sound, String fall_sound) {
        this.volume = volume;
        this.pitch = pitch;
        this.break_sound = break_sound;
        this.step_sound = step_sound;
        this.place_sound = place_sound;
        this.hit_sound = hit_sound;
        this.fall_sound = fall_sound;
    }

    public static BlockSoundGroup sound_group(VisionSoundGroup sound_group) {
        if (sound_group == null) return null;
        float sound_event_volume = sound_group.volume != null ? sound_group.volume : 1f;
        float sound_event_pitch = sound_group.pitch != null ? sound_group.pitch : 1f;
        SoundEvent break_sound_event = sound_group.break_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.break_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent step_sound_event = sound_group.step_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.step_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent place_sound_event = sound_group.place_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.place_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent hit_sound_event = sound_group.hit_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.hit_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent fall_sound_event = sound_group.fall_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.fall_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        return new BlockSoundGroup(sound_event_volume, sound_event_pitch, break_sound_event, step_sound_event, place_sound_event, hit_sound_event, fall_sound_event);
    }
}
