package lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.command.argument.EntityArgumentType;

public class BurnCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("burn").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("seconds", IntegerArgumentType.integer(0))
                .executes(arguments -> {
                    try {
                        for (Entity entity : EntityArgumentType.getEntities(arguments, "entities")) {
                            entity.setOnFireFor(IntegerArgumentType.getInteger(arguments, "seconds"));
                        }
                    } catch (CommandSyntaxException exception) {
                        exception.printStackTrace();
                    }
                    return 0;
                }))));
    }
}
