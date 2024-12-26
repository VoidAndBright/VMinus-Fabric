package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;
import net.lixir.vminus.visions.VisionHandler;

public class PrintEntityVisionFileProcedure {
    public static void execute() {
        VMinus.LOGGER.info(("Raw Entity Vision File: " + VMinusVariables.main_entity_vision));
        VMinus.LOGGER.info(("Raw Entity Vision Cache: " + VisionHandler.getEntityVisionCache()));
        VMinus.LOGGER.info(("Raw Entity Vision Key: " + VisionHandler.getEntityVisionKey()));
    }
}
