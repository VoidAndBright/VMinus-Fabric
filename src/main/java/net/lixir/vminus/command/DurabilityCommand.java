package net.lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Hand;

public class DurabilityCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
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
}
