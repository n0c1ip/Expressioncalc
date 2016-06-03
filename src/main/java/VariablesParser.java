import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class VariablesParser {

    public static HashMap<String,Double> variablesMap = new HashMap<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static boolean isVariableExist(String varName){
        return variablesMap.containsKey(varName);
    }

    public static String switchNameToValue(String varName){
        return String.valueOf(variablesMap.get(varName));
    }

    public static void parseVariable() throws IOException {
        String inputExpression = reader.readLine();
        inputExpression = inputExpression.replaceAll(" ", "");
        String varAndValue = inputExpression.substring(inputExpression.indexOf("=")-1);
        String[] varParts = varAndValue.split("=");
        variablesMap.put(varParts[0],Double.valueOf(varParts[1]));
    }


}
