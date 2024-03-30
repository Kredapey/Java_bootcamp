package ex02;

import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {
        int counter = countCoffeeRequests();
        System.out.println("Count of coffee-request – " + counter);
    }

    public static int countCoffeeRequests() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        int number = 0;
        while (number != 42) {
            number = scanner.nextInt();
            int tmpNum = sumDigitNum(number);
            if (isSimple(tmpNum)) {
                counter++;
            }
        }
        return counter;
    }

    public static int sumDigitNum(int num) {
        if ((num / 10) > 0) {
            return (num % 10) + sumDigitNum(num / 10);
        } else {
            return num % 10;
        }
    }

    public static boolean isSimple(int number) {
        boolean result = false;
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            for (int i = 2; i < number; ++i) {
                if (number % i == 0) {
                    break;
                }
                if ((number / i) < i) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
