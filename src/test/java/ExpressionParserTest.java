import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionParserTest {

    @Test
    public void shouldConvertToPostfix() throws Exception {
        ExpressionParser parser = new ExpressionParser();
        String inputExpression = "(2 + 5) * 3 - cos(30)";
        List<String> expectedExpression = Arrays.asList("2", "5", "+", "3", "*", "30", "cos", "-");
        assertThat(parser.fromInfixToPostfix(inputExpression), is(expectedExpression));
    }
}
