package net.lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.lixir.vminus.VMinus;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class VMinusCommands {
    private static void burn_command(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("burn").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                        .then(CommandManager.argument("seconds", IntegerArgumentType.integer(0))
                                .executes(arguments -> {
                                    for (Entity entity : EntityArgumentType.getEntities(arguments, "entities")) {
                                        entity.setOnFireFor(IntegerArgumentType.getInteger(arguments, "seconds"));
                                    }
                                    return 0;
                                }))));
    }
    private static void durability_command(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("durability").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                        .then(CommandManager.argument("operation", OperationArgumentType.operation())
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(arguments -> {
                                            for (Entity entity_iterate : EntityArgumentType.getEntities(arguments, "entities")) {
                                                ItemStack main_hand_stack = entity_iterate instanceof LivingEntity livingEntity ? livingEntity.getStackInHand(Hand.MAIN_HAND) : ItemStack.EMPTY;
                                                int amount = IntegerArgumentType.getInteger(arguments, "amount");
                                                switch (OperationArgumentType.getOperation(arguments,"operation")){
                                                    case "set": main_hand_stack.setDamage(Math.max(Math.min(main_hand_stack.getMaxDamage() - amount,main_hand_stack.getMaxDamage()), 0));
                                                    case "add": main_hand_stack.setDamage(main_hand_stack.getDamage() - amount);
                                                    case "minus": main_hand_stack.setDamage(main_hand_stack.getDamage() + amount);
                                                }
                                            }
                                            return 0;
                                        })))));
    }
    private static void freeze_command(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
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
    private static void health_command(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
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
    public static boolean permission_level_3(ServerCommandSource source){
        return source.hasPermissionLevel(3);
    }
    public static void register(){
        CommandRegistrationCallback.EVENT.register(VMinusCommands::burn_command);
        CommandRegistrationCallback.EVENT.register(VMinusCommands::freeze_command);
        CommandRegistrationCallback.EVENT.register(VMinusCommands::durability_command);
        CommandRegistrationCallback.EVENT.register(VMinusCommands::health_command);
        ArgumentTypeRegistry.registerArgumentType(new Identifier(VMinus.MOD_ID, "operation"), OperationArgumentType.class, ConstantArgumentSerializer.of(OperationArgumentType::operation));
    }
}
