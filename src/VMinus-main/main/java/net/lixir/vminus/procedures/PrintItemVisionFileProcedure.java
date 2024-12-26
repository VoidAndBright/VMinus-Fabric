package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;
import net.lixir.vminus.visions.VisionHandler;

public class PrintItemVisionFileProcedure {
    public static void execute() {
        VMinus.LOGGER.info(("Raw Item Vision File: " + VMinusVariables.main_item_vision));
        VMinus.LOGGER.info(("Raw Item Vision Cache: " + VisionHandler.getItemVisionCache()));
        VMinus.LOGGER.info(("Raw Item Vision Key: " + VisionHandler.getItemVisionKey()));
    }
}
