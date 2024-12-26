package net.lixir.vminus.command;

import net.lixir.vminus.procedures.PrintEffectVisionFileProcedure;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EffectvisionrawCommand {
    
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("effectvisionraw")

                .executes(arguments -> {
                    Level world = arguments.getSource().getUnsidedLevel();
                    double x = arguments.getSource().getPosition().x();
                    double y = arguments.getSource().getPosition().y();
                    double z = arguments.getSource().getPosition().z();
                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null && world instanceof ServerWorld _servLevel)
                        entity = FakePlayerFactory.getMinecraft(_servLevel);
                    Direction direction = Direction.DOWN;
                    if (entity != null)
                        direction = entity.getDirection();

                    PrintEffectVisionFileProcedure.execute();
                    return 0;
                }));
    }
}
