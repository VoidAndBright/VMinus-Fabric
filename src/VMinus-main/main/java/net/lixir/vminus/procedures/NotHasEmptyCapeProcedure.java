package net.lixir.vminus.procedures;

import net.lixir.vminus.network.VMinusModVariables;
import net.minecraft.world.entity.Entity;

public class NotHasEmptyCapeProcedure {
    public static boolean execute(Entity entity) {
        if (entity == null)
            return false;
        return !((entity.getCapability(VMinusModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VMinusModVariables.PlayerVariables())).cape_id).isEmpty();
    }
}
