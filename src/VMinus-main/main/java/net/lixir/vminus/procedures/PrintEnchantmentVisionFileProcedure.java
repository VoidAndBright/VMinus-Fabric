package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinusMod;
import net.lixir.vminus.network.VMinusModVariables;

public class PrintEnchantmentVisionFileProcedure {
    public static void execute() {
        VMinusMod.LOGGER.debug(("Raw Enchantment Vision File: " + VMinusModVariables.main_enchantment_vision));
    }
}
