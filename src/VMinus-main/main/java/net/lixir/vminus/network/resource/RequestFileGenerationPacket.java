package net.lixir.vminus.network.resource;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.network.VMinusVariables;
import net.lixir.vminus.visions.ResourceVisionLoader;
import net.lixir.vminus.visions.VisionHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class RequestFileGenerationPacket {
    public RequestFileGenerationPacket() {
    }

    public static void handle(RequestFileGenerationPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        ServerPlayer player = contextSupplier.get().getSender();
        contextSupplier.get().enqueueWork(() -> {
            World world = player.level();
            if (VMinusVariables.main_item_vision.entrySet().isEmpty())
                ResourceVisionLoader.generateItemVisionsFile(world);
            if (VMinusVariables.main_block_vision.entrySet().isEmpty())
                ResourceVisionLoader.generateBlockVisionsFile(world);
            if (VMinusVariables.main_entity_vision.entrySet().isEmpty())
                ResourceVisionLoader.generateEntityVisionsFile(world);
            if (VMinusVariables.main_effect_vision.entrySet().isEmpty())
                ResourceVisionLoader.generateEffectVisionsFile(world);
            if (VMinusVariables.main_enchantment_vision.entrySet().isEmpty())
                ResourceVisionLoader.generateEnchantmentVisionsFile(world);
            sendFilesToClient(player);
        });
        contextSupplier.get().setPacketHandled(true);
    }

    private static void sendFilesToClient(ServerPlayer player) {
        String itemJson = VMinusVariables.main_item_vision.toString();
        String blockJson = VMinusVariables.main_block_vision.toString();
        String entityJson = VMinusVariables.main_entity_vision.toString();
        String effectJson = VMinusVariables.main_effect_vision.toString();
        String enchantmentJson = VMinusVariables.main_enchantment_vision.toString();
        sendJsonInChunks(player, itemJson, blockJson, entityJson, effectJson, enchantmentJson);
    }

    private static void sendJsonInChunks(ServerPlayer player, String itemJson, String blockJson, String entityJson, String effectJson, String enchantmentJson) {
        int chunkSize = 32767;
        for (int i = 0; i < itemJson.length(); i += chunkSize) {
            String chunk = itemJson.substring(i, Math.min(itemJson.length(), i + chunkSize));
            boolean isLastChunk = (i + chunkSize) >= itemJson.length();
            VMinus.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SendJsonFilesPacket(chunk, "", "", "", "", isLastChunk, VisionHandler.ITEM_TYPE));
        }
        for (int i = 0; i < blockJson.length(); i += chunkSize) {
            String chunk = blockJson.substring(i, Math.min(blockJson.length(), i + chunkSize));
            boolean isLastChunk = (i + chunkSize) >= blockJson.length();
            VMinus.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SendJsonFilesPacket("", chunk, "", "", "", isLastChunk, VisionHandler.BLOCK_TYPE));
        }
        for (int i = 0; i < entityJson.length(); i += chunkSize) {
            String chunk = entityJson.substring(i, Math.min(entityJson.length(), i + chunkSize));
            boolean isLastChunk = (i + chunkSize) >= entityJson.length();
            VMinus.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SendJsonFilesPacket("", "", chunk, "", "", isLastChunk, VisionHandler.ENTITY_TYPE));
        }
        for (int i = 0; i < effectJson.length(); i += chunkSize) {
            String chunk = effectJson.substring(i, Math.min(effectJson.length(), i + chunkSize));
            boolean isLastChunk = (i + chunkSize) >= entityJson.length();
            VMinus.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SendJsonFilesPacket("", "", "", chunk, "", isLastChunk, VisionHandler.EFFECT_TYPE));
        }
        for (int i = 0; i < enchantmentJson.length(); i += chunkSize) {
            String chunk = enchantmentJson.substring(i, Math.min(enchantmentJson.length(), i + chunkSize));
            boolean isLastChunk = (i + chunkSize) >= enchantmentJson.length();
            VMinus.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SendJsonFilesPacket("", "", "", "", chunk, isLastChunk, VisionHandler.ENCHANTMENT_TYPE));
        }
    }

    public static void encode(RequestFileGenerationPacket message, FriendlyByteBuf buffer) {
    }

    public static RequestFileGenerationPacket decode(FriendlyByteBuf buffer) {
        return new RequestFileGenerationPacket();
    }

    
    public static void registerMessage(FMLCommonSetupEvent event) {
        VMinus.addNetworkMessage(RequestFileGenerationPacket.class, RequestFileGenerationPacket::encode, RequestFileGenerationPacket::decode, RequestFileGenerationPacket::handle);
    }
}
