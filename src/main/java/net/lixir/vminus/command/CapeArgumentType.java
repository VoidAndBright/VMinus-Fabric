package net.lixir.vminus.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.lixir.vminus.cape.Cape;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class CapeArgumentType implements ArgumentType<String> {
    private static final Collection<String> CAPE_OPTIONS = Arrays.asList("none","beeper","shroud","ghost","marrow","prototype","troll","photon");

    public String parse(StringReader stringReader) throws CommandSyntaxException {
        return stringReader.readString();
    }
    public static CapeArgumentType cape(){
        return new CapeArgumentType();
    }
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        for (Cape cape:Cape.values()){
            if (Cape.to_string(cape).startsWith(builder.getRemainingLowerCase())) {
                builder.suggest(Cape.to_string(cape));
            }
        }
        return builder.buildFuture();
    }
    public static Cape get_cape(final CommandContext<?> context, final String name) {
        return Cape.from_string(context.getArgument(name, String.class));
    }
    public Collection<String> getExamples() {
        return CAPE_OPTIONS;
    }
}
