package lixir.vminus.vision;

public class NumberUtil {
    public static double modifyNumber(double existingNumber, double number, String operation) {
        return switch (operation.toLowerCase()) {
            case "addition" -> existingNumber + number;
            case "subtraction" -> existingNumber - number;
            case "exponent" -> Math.pow(existingNumber, number);
            case "divide" -> existingNumber / number;
            case "multiply" -> existingNumber * number;
            default -> number;
        };
    }
}
