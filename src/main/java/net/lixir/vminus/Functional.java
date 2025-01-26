package net.lixir.vminus;

import java.util.Iterator;
import java.util.function.Consumer;

public class Functional {
    public static <T> void for_each(Iterator<T> iterator, Consumer<T> action) {
        if (iterator.hasNext()){
            action.accept(iterator.next());
            for_each(iterator,action);
        }
    }
}
