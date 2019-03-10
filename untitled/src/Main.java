import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;







public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int num1 = 0;
        int num2 = 0;

        boolean bu = getRo();
        if(bu) {
            int num10 = R2D();
            int num20 = R2D();
            num1 = num10;
            num2 = num20;
        }else {
            int num10 = getInt();
            int num20 = getInt();
            num1 = num10;
            num2 = num20;

        }
        char operation = getOperation();
        int result = calc(num1,num2,operation);
        System.out.println("Результат: "+result);
    }

    public static boolean getRo() {
        System.out.println("Введите 1 если операции с арабскими цифрами, введите 2 если с римскими:");
        int bol = 0;
        if (scanner.hasNextInt()) {
            bol = scanner.nextInt();
        } else {
            System.out.println("Вы ввели некорректное значение, попробуйте еще раз.");
            scanner.next();//рекурсия
            getRo();
        }
        if(bol == 1){
            return false;
        }else{
            return true;
        }
    }

    public static int getInt(){
        System.out.println("Введите число:");
        int num;
        if(scanner.hasNextInt()){
            num = scanner.nextInt();
        } else {
            System.out.println("Вы ввели некорректное значение, попробуйте еще раз.");
            scanner.next();//рекурсия
            num = getInt();
        }
        return num;
    }

    public static int R2D(){
        System.out.println("Введите римскую цифру:");
        String rom = "";
        int value = 0;
        if(scanner.hasNext()){
            value = convertFromR(rom);
        }else {
            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
            scanner.next();//рекурсия
            R2D();
        }
        return value;
    }

    public static int convertFromR(String roman) {
        Map<String, Integer> v = new HashMap<String, Integer>();
        v.put("IV", 4);
        v.put("IX", 9);
        v.put("XL", 40);
        v.put("CD", 400);
        v.put("CM", 900);
        v.put("C", 100);
        v.put("M", 1000);
        v.put("I", 1);
        v.put("V", 5);
        v.put("X", 10);
        v.put("L", 50);
        v.put("D", 500);
        int result = 0;
        for (String s : v.keySet()) {
            result += countO(roman, s) * v.get(s);
            roman = roman.replaceAll(s, "");
        }

        return result;
    }



    public static int countO(String main, String sub) {
        return (main.length() - main.replace(sub, "").length()) / sub.length();
    }

    public static char getOperation(){
        System.out.println("Введите операцию:");
        char operation;
        if(scanner.hasNext()){
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
            scanner.next();//рекурсия
            operation = getOperation();
        }
        return operation;
    }

    public static int calc(int num1, int num2, char operation){
        int result;
        switch (operation){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num1-num2;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num1/num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(num1, num2, getOperation());//рекурсия
        }
        return result;
    }
}
