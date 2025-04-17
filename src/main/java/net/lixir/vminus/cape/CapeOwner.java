package net.lixir.vminus.cape;

import net.minecraft.entity.player.PlayerEntity;

public interface CapeOwner {
    void set_cape(Cape cape);
    Cape get_cape();
    static <T extends PlayerEntity> CapeOwner cast(T player){
        return (CapeOwner)player;
    }
}
