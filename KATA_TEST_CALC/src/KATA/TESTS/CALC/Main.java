package KATA.TESTS.CALC;
import java.util.Scanner;
public class Main {
    private static boolean exitLoop;
    private static boolean stringHasErrors;
    private static final char[] arithmeticOperators = {'+', '-', '*', '/'};
    private static final String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static int num1;
    private static int num2;
    private static String Operator;
    private static int indexOfOperator;
    public static void main(String[] args) {
        runCalc();
    }
    private static void runCalc() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет! Я калькулятор. Я умею выполнять арифметические действия" +
                " над целыми числами от 1 до 10 включительно. \nКроме того, Я поддерживаю римские цифры.");
        while (!exitLoop) {
            System.out.println("Введите Ваши числа:");
            String input = scanner.nextLine();
            System.out.println(calc(input)); // пример использования метода, который нужно было реализовать
        }

    }
    public static String calc(String input) {
        String result = "ОШИБКА ВВОДА";
        stringErrorChecking(input);
        if (!stringHasErrors) {
            result = calculate(input);
        } else {
            exitLoop = true;
        }
        return result;
    }
    private static String calculate(String input) {
        int result = 0;
        switch (Operator) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
        }

        if (isRoman(input)) {
            return convertIntToRoman(result);
        } else {
            return String.valueOf(result);

        }
    }
    private static void stringErrorChecking(String input) {
        if (input.length() >= 3) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == arithmeticOperators[0] || c == arithmeticOperators[1] ||
                        c == arithmeticOperators[2] || c == arithmeticOperators[3]) {
                    indexOfOperator = i;
                }
            }

            num1 = convertStringNumToInt(input.substring(0, indexOfOperator));
            num2 = convertStringNumToInt(input.substring(indexOfOperator + 1));
            Operator = input.substring(indexOfOperator, indexOfOperator + 1);

            if (num1 < 1 || num2 < 1 || num1 > 10 || num2 > 10) {
                stringHasErrors = true;
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (input.contains(roman[i]) && input.contains(digits[j])) {
                        stringHasErrors = true;
                        exitLoop = true;
                        break;
                    }
                }

            }

        } else {
            stringHasErrors = true;
        }
    }
    private static int convertStringNumToInt(String numAsString) {
        int result;
        switch (numAsString) {
            case "I", "1" -> result = 1;
            case "II", "2" -> result = 2;
            case "III", "3" -> result = 3;
            case "IV", "4" -> result = 4;
            case "V", "5" -> result = 5;
            case "VI", "6" -> result = 6;
            case "VII", "7" -> result = 7;
            case "VIII", "8" -> result = 8;
            case "IX", "9" -> result = 9;
            case "X", "10" -> result = 10;
            default -> result = -1;
        }
        return result;
    }
    private static String convertIntToRoman(int num) {
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();

        if (num < 1 || num > 3999) {
            System.out.println("ОШИБКА РЕЗУЛЬТАТ НЕ МОЖЕТ БЫТЬ ОТРИЦАТЕЛЬНЫМ");
            stringHasErrors = true;
            exitLoop = true;
        }

        int i = 0;
        while (num > 0) {
            if (num >= values[i]) {
                result.append(romanSymbols[i]);
                num -= values[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }
    private static boolean isRoman(String input) {
        boolean isRoman = false;
        for (String s : roman) {
            if (input.contains(s)) {
                isRoman = true;
                break;
            }

        }
        return isRoman;
    }
}
