package lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DurabilityCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("durability").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("operation", StringArgumentType.word())
                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                .executes(arguments -> {
                    try {
                        for (Entity entity_iterate : EntityArgumentType.getEntities(arguments, "entities")) {
                            ItemStack main_hand_stack = entity_iterate instanceof LivingEntity livingEntity ? livingEntity.getStackInHand(Hand.MAIN_HAND) : ItemStack.EMPTY;
                            String operation = StringArgumentType.getString(arguments, "operation");
                            int amount = IntegerArgumentType.getInteger(arguments, "amount");
                            switch (operation) {
                                case "set" ->
                                        main_hand_stack.setDamage(Math.max(0, main_hand_stack.getMaxDamage() - amount));
                                case "decrease" ->
                                        main_hand_stack.setDamage(Math.max(main_hand_stack.getDamage() + amount, 0));
                                case "increase" ->
                                        main_hand_stack.setDamage(Math.min(main_hand_stack.getDamage() - amount, main_hand_stack.getMaxDamage()));
                                default -> arguments.getSource().sendFeedback(() -> Text.literal("That isn't a Operation"), false);

                            }
                        }
                    } catch (CommandSyntaxException exception) { exception.printStackTrace();}
                    return 0;
                })))));
    }
}
