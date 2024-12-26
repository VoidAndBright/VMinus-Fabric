package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;

public class PrintEffectVisionFileProcedure {
    public static void execute() {
        VMinus.LOGGER.debug(("Raw Effect Vision File: " + VMinusVariables.main_effect_vision));
    }
}
