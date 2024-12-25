package lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class FreezeCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("freeze").requires(source -> source.hasPermissionLevel(3))
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("ticks", IntegerArgumentType.integer(0))
                .executes(arguments -> {
                    try {
                        for (Entity entity : EntityArgumentType.getEntities(arguments, "entities")) {
                            entity.setFrozenTicks(IntegerArgumentType.getInteger(arguments, "ticks"));
                        }
                    } catch (CommandSyntaxException exception) {
                        exception.printStackTrace();
                    }
                    return 0;
                }))));
    }
}
