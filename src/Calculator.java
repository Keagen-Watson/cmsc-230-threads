import java.util.Arrays;

/**
 *
 *
 * @author Keagen Watson
 */
public class Calculator {
    static String answer(String line) {
        String[] input = line.split(" ");
        String operator = input[0].toUpperCase();
        int result = 0;
        int first = Integer.parseInt(input[1]), second = Integer.parseInt(input[2]);
        if (operator.equals("ADD")) {
            result = first + second;
        } else if (operator.equals("MUL")) {
            result = first * second;
        }
        return String.valueOf(result);
    }
    private int add(int first, int second) {
        return first + second;
    }
}
