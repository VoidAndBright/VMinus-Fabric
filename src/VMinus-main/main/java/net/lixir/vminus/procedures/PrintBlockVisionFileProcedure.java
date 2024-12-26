package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;
import net.lixir.vminus.visions.VisionHandler;

public class PrintBlockVisionFileProcedure {
    public static void execute() {
        VMinus.LOGGER.debug(("Raw Block Vision File: " + VMinusVariables.main_block_vision));
        VMinus.LOGGER.info(("Raw Block Vision Cache: " + VisionHandler.getBlockVisionCache()));
        VMinus.LOGGER.info(("Raw Block Vision Key: " + VisionHandler.getBlockVisionKey()));
    }
}
