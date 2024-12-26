package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.helpers.CapeHelper;
import net.minecraft.world.entity.Entity;

public class OwnsGhostCapeProcedure {
    public static boolean execute(Entity entity) {
        if (entity == null)
            return false;
        if (false) {
            VMinus.LOGGER.info(entity);
        }
        return !CapeHelper.ownsCape(entity, "ghost");
    }
}
