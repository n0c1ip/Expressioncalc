import java.util.*;

public class ExpressionParser {

    private final String OPERATORS = "+-*/";
    private final String PARENTHESES = "()";
    private final String NONOPERANDS = OPERATORS.concat(PARENTHESES);
    private final Set<String> FUNCTIONS =
            new HashSet<String>(Arrays.asList("sin", "cos", "pow", "log", "abs"));

    private List<String> postfix = new ArrayList<String>();
    private Stack<String> stack = new Stack<String>();

    private boolean isOperator(String token){
        return OPERATORS.contains(token);
    }

    private boolean isDelimiter(String token){
        return NONOPERANDS.contains(token);
    }

    private boolean isFunction(String token){
      return FUNCTIONS.contains(token);
    }

    private boolean isParentheses(String token){
      return PARENTHESES.contains(token);
    }

    private int getPriority(String token){
        int priority = 0;
        if (token.equals("+") || token.equals("-")) priority = 1;
        if (token.equals("*") || token.equals("/")) priority = 2;
        return priority;
    }

    public List<String> fromInfixToPostfix(String infix){
        String currentToken = "";
        StringTokenizer tokenizer = new StringTokenizer(prepareExpression(infix), NONOPERANDS, true);
        while (tokenizer.hasMoreTokens()){

            currentToken = tokenizer.nextToken();

            //check exponential number
            if(currentToken.contains("E")){
                currentToken += tokenizer.nextToken() + tokenizer.nextToken();
            }
            if(isFunction(currentToken)){
                stack.push(currentToken);
            } else if (isOperator(currentToken)){
                processingOperators(currentToken);
            } else if (isParentheses(currentToken)){
                processingParentheses(currentToken);
            } else {
                postfix.add(currentToken);
            }
        }

        while (!stack.isEmpty()){
            postfix.add(stack.pop());
        }
        return postfix;
    }

    private void processingParentheses(String token){
        if(token.equals("(")){
            stack.push(token);
        } else {
            while(!stack.peek().equals("(")){
                postfix.add(stack.pop());
            }
            stack.pop(); //remove open bracket
        }
        //если осталась функция переносим в выходную строку
        if (!stack.isEmpty() && isFunction(stack.peek())) {
            postfix.add(stack.pop());
        }
    }

    private void processingOperators(String token){
        while (!stack.isEmpty() && (getPriority(token) <= getPriority(stack.peek()))) {
            postfix.add(stack.pop());
        }
        stack.push(token);
    }

    private String prepareExpression(String expression){
        expression = expression.replace(" ", "");
        expression = expression.replace(",",".");
        expression = expression.replace("e", "E");
        return expression;
    }

}
