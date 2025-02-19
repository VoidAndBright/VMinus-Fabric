package net.lixir.vminus.mixin.util;

import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Instrument.class)
public interface InstrumentAccessor {
    @Invoker(value = "<init>")
    static Instrument instrument_constructor(String internal_name, int internal_identifier, String name, RegistryEntry<SoundEvent> sound, Instrument.Type type) {
        throw new AssertionError();
    }
}
