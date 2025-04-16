package net.lixir.vminus.command;

public enum Operation {
    SET,
    ADD,
    MINUS;
    public static Operation from_string(String string){
        return switch (string){
            case "set" -> SET;
            case "minus" -> MINUS;
            default -> ADD;
        };
    }
}
