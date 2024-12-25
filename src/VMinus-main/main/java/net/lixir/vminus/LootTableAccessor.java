package net.lixir.vminus;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootTable;

import java.lang.reflect.Field;

public class LootTableAccessor {
    public static Identifier getRandomSequence(LootTable lootTable) {
        try {
            Field field = LootTable.class.getDeclaredField("randomSequence");
            field.setAccessible(true);
            return (Identifier) field.get(lootTable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
