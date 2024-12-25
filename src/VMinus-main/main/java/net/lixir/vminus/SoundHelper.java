package net.lixir.vminus;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class SoundHelper {
    public static SoundType CreateBlockSoundType(@Nullable String breakSound, @Nullable String stepSound, @Nullable String placeSound, @Nullable String hitSound, @Nullable String fallSound) {
        if (breakSound == null || breakSound.isEmpty())
            breakSound = "minecraft:block.stone.break";
        if (stepSound == null || stepSound.isEmpty())
            stepSound = "minecraft:block.stone.step";
        if (placeSound == null || placeSound.isEmpty())
            placeSound = "minecraft:block.stone.place";
        if (hitSound == null || hitSound.isEmpty())
            hitSound = "minecraft:block.stone.hit";
        if (fallSound == null || fallSound.isEmpty())
            fallSound = "minecraft:block.stone.fall";
        SoundEvent breakEvent = ForgeRegistries.SOUND_EVENTS.getValue(new Identifier(breakSound));
        SoundEvent stepEvent = ForgeRegistries.SOUND_EVENTS.getValue(new Identifier(stepSound));
        SoundEvent placeEvent = ForgeRegistries.SOUND_EVENTS.getValue(new Identifier(placeSound));
        SoundEvent hitEvent = ForgeRegistries.SOUND_EVENTS.getValue(new Identifier(hitSound));
        SoundEvent fallEvent = ForgeRegistries.SOUND_EVENTS.getValue(new Identifier(fallSound));
        return new SoundType(1.0F, 1.0F, breakEvent, stepEvent, placeEvent, hitEvent, fallEvent);
    }

    public static @Nullable SoundEvent getSoundEventFromString(String soundName) {
        Identifier soundLocation = new Identifier(soundName);
        if (ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation)) {
            return ForgeRegistries.SOUND_EVENTS.getValue(soundLocation);
        }
        return null;
    }
}
