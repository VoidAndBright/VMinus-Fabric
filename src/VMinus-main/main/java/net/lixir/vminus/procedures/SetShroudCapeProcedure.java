package net.lixir.vminus.procedures;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.capes.SetCapePacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class SetShroudCapeProcedure {
    public static void execute(Entity entity) {
        if (entity == null || !(entity instanceof Player player))
            return;
        if (!entity.level().isClient())
            return;
        UUID playerUUID = player.getUUID();
        VMinus.PACKET_HANDLER.sendToServer(new SetCapePacket("shroud", playerUUID));
    }
}
