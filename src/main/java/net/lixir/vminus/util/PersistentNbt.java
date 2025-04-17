package net.lixir.vminus.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;


public interface PersistentNbt {
    NbtCompound get_nbt();
    static <T extends Entity> NbtCompound get(T entity){
        return ((PersistentNbt)entity).get_nbt();
    }
}
