package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;

public class PrintEnchantmentVisionFileProcedure {
    public static void execute() {
        VMinus.LOGGER.debug(("Raw Enchantment Vision File: " + VMinusVariables.main_enchantment_vision));
    }
}
