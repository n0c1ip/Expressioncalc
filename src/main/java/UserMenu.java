import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserMenu {

    private int choose;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ExpressionParser parser = new ExpressionParser();
    PostfixCalculator calculator = new PostfixCalculator();

    public void showMenu() throws IOException {
        while (choose != 4){
            printMenu();
            choose = Integer.parseInt(reader.readLine());
            switch (choose){
                case 1:
                    menuItem1();
                    break;
                case 2:
                    menuItem2();
                    break;
                case 3:
                    menuItem3();
                    break;
                default:
                    break;
            }
        }

    }

    private void printMenu() {
        System.out.println("Выберите действие");
        System.out.println(
                  "1. Задать переменные." + "\n"
                + "2. Ввести выражение." + "\n"
                + "3. Очистить экран" + "\n"
                + "4. Выход"
        );
    }

    private void menuItem1(){
        System.out.println("entered vars");
    }

    private void menuItem2() throws IOException {
        System.out.println("Enter expression: ");
        String expression = reader.readLine();
        Double result = calculator.calculatePostfix(parser.fromInfixToPostfix(expression));
        System.out.println("Result is: " + result);
    }

    private void menuItem3(){
        clearScreen();
    }

    private void clearScreen()  {
        final String os = System.getProperty("os.name");
        try{
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e){
            //something wrong with cmd
        }

    }

}
