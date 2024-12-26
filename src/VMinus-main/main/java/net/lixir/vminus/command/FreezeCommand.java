package net.lixir.vminus.command;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FreezeCommand {
    
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("freeze").requires(s -> s.hasPermission(3)).then(Commands.argument("entities", EntityArgument.entities()).then(Commands.argument("ticks", DoubleArgumentType.doubleArg(0)).executes(arguments -> {
            for (Entity entity : EntityArgument.getEntities(arguments, "entities")) {
                entity.setTicksFrozen((int) DoubleArgumentType.getDouble(arguments, "ticks"));
            }
            return 0;
        }))));
    }
}
