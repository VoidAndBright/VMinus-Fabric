package net.lixir.vminus.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;


public interface PersistentNbt {
    NbtCompound vminus$get_nbt();
    static <T extends Entity> NbtCompound get(T entity){
        return ((PersistentNbt)entity).vminus$get_nbt();
    }
}
