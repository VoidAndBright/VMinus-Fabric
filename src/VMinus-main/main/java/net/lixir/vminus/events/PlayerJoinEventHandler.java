package net.lixir.vminus.events;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;
import net.lixir.vminus.network.capes.SetCapePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;

@Mod.EventBusSubscriber
public class PlayerJoinEventHandler {
    
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer serverPlayer = (ServerPlayer) event.getEntity();
        for (ServerPlayer otherPlayer : serverPlayer.server.getPlayerList().getPlayers()) {
            otherPlayer.getCapability(VMinusVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                VMinus.PACKET_HANDLER.sendTo(new SetCapePacket(capability.cape_id, otherPlayer.getUUID()), serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
            });
        }
    }
}
