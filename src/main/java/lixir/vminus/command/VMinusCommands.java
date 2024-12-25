package lixir.vminus.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class VMinusCommands {
    public static void register(){
        CommandRegistrationCallback.EVENT.register(new BurnCommand());
        CommandRegistrationCallback.EVENT.register(new FreezeCommand());
        CommandRegistrationCallback.EVENT.register(new DurabilityCommand());
        CommandRegistrationCallback.EVENT.register(new HealCommand());
    }
}
