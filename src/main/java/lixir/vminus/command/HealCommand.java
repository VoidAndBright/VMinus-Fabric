package lixir.vminus.command;

import com.mojang.brigadier.CommandDispatcher;
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

public class HealCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("heal").requires(VMinusCommands::permission_level_3)
                .then(CommandManager.argument("entities", EntityArgumentType.entities())
                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                .executes(arguments -> {
                    try {
                        for (Entity entity_iterate : EntityArgumentType.getEntities(arguments, "entities")) {
                            if (entity_iterate instanceof LivingEntity livingEntity) {
                                int amount = IntegerArgumentType.getInteger(arguments, "amount");
                                livingEntity.heal(amount);
                            }
                        }
                    }
                    catch (CommandSyntaxException exception) { exception.printStackTrace();}
                    return 0;
                }))));
    }
}
