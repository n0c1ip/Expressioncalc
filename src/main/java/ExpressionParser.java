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
        if (token.equals("+") || token.equals("-")) {
            priority = 1;
        }
        if (token.equals("*") || token.equals("/")){
            priority = 2;
        }
        if (token.equals("u-")){
            priority = 3;
        }
        return priority;
    }

    /**
     * Метод для перевода входящей строки из инфиксной формы в постфиксную
     * по алгоритму сортировочной станции
     * @param infix строка в инфиксной форме
     * @return Список строк с токенами в постфиксной форме
     */
    public List<String> fromInfixToPostfix(String infix){
        String prevToken="";
        String currentToken = "";
        StringTokenizer tokenizer = new StringTokenizer(prepareExpression(infix), NONOPERANDS, true);
        while (tokenizer.hasMoreTokens()){

            currentToken = tokenizer.nextToken();

            //проверка на экспонциональную запись
            if(currentToken.contains("E")){
                currentToken += tokenizer.nextToken() + tokenizer.nextToken();
            }

            if(isFunction(currentToken)){
                stack.push(currentToken);
            } else if (isOperator(currentToken)){
                processingOperators(currentToken, prevToken);
            } else if (isParentheses(currentToken)){
                processingParentheses(currentToken);
            } else {
                //проверяем что перед нами переменная, и меняем ее на значение
                if(VariablesParser.isVariableExist(currentToken)){
                   currentToken = VariablesParser.switchNameToValue(currentToken);
                }
                postfix.add(currentToken);
            }
            prevToken = currentToken;
        }

        while (!stack.isEmpty()){
            postfix.add(stack.pop());
        }
        return postfix;
    }


    //обраотка токенов в зависимости от их принадлежности
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
    private void processingOperators(String token, String prevToken){
        //проверяем минус, унарный или нет
        if(token.equals("-") && prevToken.isEmpty() || prevToken.equals("(")){
            stack.push("u-");
        } else{
            while (!stack.isEmpty() && (getPriority(token) <= getPriority(stack.peek()))) {
                postfix.add(stack.pop());
            }
            stack.push(token);
        }
    }

    //подготовка строки к парсингу
    private String prepareExpression(String expression){
        expression = expression.replace(" ", "");
        expression = expression.replace(",",".");
        expression = expression.replace("e", "E");
        return expression;
    }

    public void clear(){
        postfix.clear();
        stack.clear();
    }


}
