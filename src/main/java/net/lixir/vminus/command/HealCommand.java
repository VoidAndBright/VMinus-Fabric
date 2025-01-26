package net.lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class HealCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("heal").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                .executes(arguments -> {
                    for (Entity entity_iterate : EntityArgumentType.getEntities(arguments, "entities")) {
                        if (entity_iterate instanceof LivingEntity livingEntity) {
                            int amount = IntegerArgumentType.getInteger(arguments, "amount");
                            livingEntity.heal(amount);
                        }
                    }
                    return 0;
                }))));
    }
}
