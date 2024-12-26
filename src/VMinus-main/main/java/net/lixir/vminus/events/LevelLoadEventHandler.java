package net.lixir.vminus.events;

import net.lixir.vminus.visions.ResourceVisionLoader;
import net.lixir.vminus.visions.VisionHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.world.level.World;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LevelLoadEventHandler {
    (priority = EventPriority.NORMAL)
    public static void onWorldLoad(LevelEvent.Load event) {
        World world = event.getLevel();
        VisionHandler.clearCaches();
        if (world instanceof ServerWorld _ServerWorld) {
            ResourceVisionLoader.generateItemVisionsFile(_ServerWorld);
            ResourceVisionLoader.generateBlockVisionsFile(_ServerWorld);
            ResourceVisionLoader.generateEntityVisionsFile(_ServerWorld);
            ResourceVisionLoader.generateEffectVisionsFile(_ServerWorld);
            ResourceVisionLoader.generateEnchantmentVisionsFile(_ServerWorld);
            // Required for vision recipe changes.
            MinecraftServer server = _ServerWorld.getServer();
            if (server != null) {
                server.reloadResources(server.getPackRepository().getSelectedIds());
            }
        }
        VisionHandler.loadVisions();
    }
}
