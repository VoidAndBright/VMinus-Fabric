package net.lixir.vminus.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class OperationArgumentType implements ArgumentType<String> {
    private static final Collection<String> EXAMPLES = Arrays.asList("add","minus","set");
    public String parse(StringReader stringReader) throws CommandSyntaxException {
        return stringReader.readString();
    }
    public static OperationArgumentType operation(){
        return new OperationArgumentType();
    }
    public static String getOperation(final CommandContext<?> context, final String name) {
        return switch (context.getArgument(name, String.class)){
            case "set" -> "set";
            case "minus" -> "minus";
            default -> "add";
        };
    }
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        if ("add".startsWith(builder.getRemainingLowerCase())) {
            builder.suggest("add");
        }
        if ("minus".startsWith(builder.getRemainingLowerCase())) {
            builder.suggest("minus");
        }
        if ("set".startsWith(builder.getRemainingLowerCase())) {
            builder.suggest("set");
        }
        return builder.buildFuture();
    }
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
