import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class UserMenu {

    private int choose;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ExpressionParser parser = new ExpressionParser();
    PostfixCalculator calculator = new PostfixCalculator();

    public void showMenu() throws IOException {
        while (choose != 4){
            printMenu();
            try {
                choose = Integer.parseInt(reader.readLine());
            } catch (Exception e){
                System.out.println("Введите цифру из списка");
            }
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
        reader.close();

    }

    private void printMenu() {
        System.out.println("Выберите действие");
        if(!VariablesParser.variablesMap.isEmpty()){
            System.out.println("Введенные переменные: ");
            for (Map.Entry<String, Double> stringDoubleEntry : VariablesParser.variablesMap.entrySet()) {
                System.out.println(stringDoubleEntry.getKey() + " = " + stringDoubleEntry.getValue());
            }
            System.out.println("_____________________");
        }
        System.out.println(
                  "1. Задать переменные." + "\n"
                + "2. Ввести выражение." + "\n"
                + "3. Очистить экран" + "\n"
                + "4. Выход"
        );
    }

    private void menuItem1() throws IOException {
        System.out.println("Введите переменную: ");
        VariablesParser.parseVariable();
    }

    private void menuItem2() throws IOException {
        System.out.println("Введите выражение: ");
        String expression = reader.readLine();
        Double result = calculator.calculatePostfix(parser.fromInfixToPostfix(expression));
        System.out.println("Результат выражения: " + result);
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
