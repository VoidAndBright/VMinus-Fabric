package net.lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class FreezeCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("freeze").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("ticks", IntegerArgumentType.integer(0))
                .executes(arguments -> {
                    for (Entity entity : EntityArgumentType.getEntities(arguments, "entities")) {
                        entity.setFrozenTicks(IntegerArgumentType.getInteger(arguments, "ticks"));
                    }
                    return 0;
                }))));
    }
}
