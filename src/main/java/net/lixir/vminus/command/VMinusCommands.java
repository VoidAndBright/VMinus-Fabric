package net.lixir.vminus.command;

import net.lixir.vminus.VMinus;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class VMinusCommands {
    public static boolean permission_level_3(ServerCommandSource source){
        return source.hasPermissionLevel(3);
    }
    public static void register(){
        CommandRegistrationCallback.EVENT.register(new BurnCommand());
        CommandRegistrationCallback.EVENT.register(new FreezeCommand());
        CommandRegistrationCallback.EVENT.register(new DurabilityCommand());
        CommandRegistrationCallback.EVENT.register(new HealCommand());
        ArgumentTypeRegistry.registerArgumentType(
                new Identifier(VMinus.MOD_ID, "operation"),
                OperationArgumentType.class, ConstantArgumentSerializer.of(OperationArgumentType::operation));
    }
}
