package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinusMod;
import net.lixir.vminus.network.VMinusModVariables;

public class PrintEffectVisionFileProcedure {
    public static void execute() {
        VMinusMod.LOGGER.debug(("Raw Effect Vision File: " + VMinusModVariables.main_effect_vision));
    }
}
