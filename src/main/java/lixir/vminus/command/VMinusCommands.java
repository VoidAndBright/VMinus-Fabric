package lixir.vminus.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class VMinusCommands {
    public static boolean permission_level_3(ServerCommandSource source){
        return source.hasPermissionLevel(3);
    }
    public static void register(){
        CommandRegistrationCallback.EVENT.register(new BurnCommand());
        CommandRegistrationCallback.EVENT.register(new FreezeCommand());
        CommandRegistrationCallback.EVENT.register(new DurabilityCommand());
        CommandRegistrationCallback.EVENT.register(new HealCommand());
    }
}
