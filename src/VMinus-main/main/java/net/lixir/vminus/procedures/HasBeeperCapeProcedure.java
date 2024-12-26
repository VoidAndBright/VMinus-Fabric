package net.lixir.vminus.procedures;

import net.lixir.vminus.network.VMinusVariables;
import net.minecraft.world.entity.Entity;

public class HasBeeperCapeProcedure {
    public static boolean execute(Entity entity) {
        if (entity == null)
            return false;
        return ((entity.getCapability(VMinusVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VMinusVariables.PlayerVariables())).cape_id).equals("beeper");
    }
}
