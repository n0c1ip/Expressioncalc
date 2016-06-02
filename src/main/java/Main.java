import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static String expression = "2+3*4";
    private static int currentChar, position = -1;

    public static void main(String[] args) {

        ExpressionParser ep = new ExpressionParser();
        List<String> list = ep.fromInfixToPostfix("0 ,5 5e+19+pow(2)");

//        System.out.println(Eparser.parse("10+pow(2)"));

        for (String s : list) {
            System.out.print(s + " ");
        }
    }




}
