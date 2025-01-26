package net.lixir.vminus.vision.properties;

import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class SoundGroup {
    public Float volume;
    public Float pitch;
    public String break_sound;
    public String step_sound;
    public String place_sound;
    public String hit_sound;
    public String fall_sound;
    public BlockSoundGroup to_block_sound_group() {
        float sound_event_volume = volume != null ? volume : 1f;
        float sound_event_pitch = pitch != null ? pitch : 1f;
        SoundEvent break_sound_event = break_sound != null ? Registries.SOUND_EVENT.get(new Identifier(break_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent step_sound_event = step_sound != null ? Registries.SOUND_EVENT.get(new Identifier(step_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent place_sound_event = place_sound != null ? Registries.SOUND_EVENT.get(new Identifier(place_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent hit_sound_event = hit_sound != null ? Registries.SOUND_EVENT.get(new Identifier(hit_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent fall_sound_event = fall_sound != null ? Registries.SOUND_EVENT.get(new Identifier(fall_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        return new BlockSoundGroup(sound_event_volume, sound_event_pitch, break_sound_event, step_sound_event, place_sound_event, hit_sound_event, fall_sound_event);
    }
}
