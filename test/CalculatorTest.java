import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 * @author Keagen Watson
 */
public class CalculatorTest extends Calculator {
    @Test
    void calculatorAnswers() {
        Calculator calculator = new Calculator();
        assertEquals("5", calculator.answer("ADD 3 2"));
        assertEquals("20", calculator.answer("MUL 5 4"));
    }
}
