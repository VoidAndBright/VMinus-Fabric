package net.lixir.vminus.mixin.util;

import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.*;

import java.util.List;
import java.util.Vector;

@Mixin(Instrument.class)
public class InstrumentMixin {
    @Shadow @Final @Mutable
    private static Instrument[] field_12652;
    static {
        new_instrument("DEFAULTIUM", SoundEvents.BLOCK_NOTE_BLOCK_IMITATE_CREEPER,Instrument.Type.MOB_HEAD);
    }
    @Unique
    private static void new_instrument(String name, RegistryEntry<SoundEvent> sound_event, Instrument.Type type) {
        Vector<Instrument> instruments = new Vector<>(List.of(field_12652));
        instruments.add(InstrumentAccessor.instrument_constructor(name, instruments.size(), name.toLowerCase() ,sound_event,type));
        field_12652 = instruments.toArray(Instrument[]::new);
    }
}
