import java.util.*;

public class PostfixCalculator {

    private final String OPERATORS = "+u-*/";
    private final Set<String> FUNCTIONS =
            new HashSet<String>(Arrays.asList("sin", "cos", "pow", "log", "abs"));
    private Stack<Double> stack = new Stack<Double>();

    public Double calculatePostfix(List<String> postfixExpression){
        for (String token : postfixExpression) {
            if(OPERATORS.contains(token)){
                calculateOperators(token);
            } else if(FUNCTIONS.contains(token)){
                calculateFunctions(token);
            } else stack.push(Double.valueOf(token));
        }
        Double result = stack.peek();
        stack.clear();
        return result;
    }

    private void calculateFunctions(String token){
        if(token.equals("sin")){
            stack.push(Math.sin(stack.pop()));
        }
        if(token.equals("cos")){
            stack.push(Math.cos(stack.pop()));
        }
        if(token.equals("pow")){
            String[] powParam = String.valueOf(stack.pop()).split("\\.");
            stack.push(Math.pow(Double.valueOf(powParam[0]),Double.valueOf(powParam[1])));
        }
        if(token.equals("log")){
            stack.push(Math.log(stack.pop()));
        }
        if(token.equals("abs")){
            stack.push(Math.abs(stack.pop()));
        }
    }

    private void calculateOperators(String token){
        if(token.equals("+")){
            stack.push(stack.pop() + stack.pop());
        }
        if(token.equals("-")){
            double second = stack.pop(), first = stack.pop();
            stack.push(first - second);
        }
        if(token.equals("*")){
            stack.push(stack.pop() * stack.pop());
        }
        if(token.equals("/")){
            double second = stack.pop(), first = stack.pop();
            stack.push(first / second);
        }
        if(token.equals("u-")){
            stack.push(-stack.pop());
        }
    }


}
