package ex01;

import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        isSimple(number);
    }

    public static void isSimple(int number) {
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            for (int i = 2; i < number; ++i) {
                if (number % i == 0) {
                    System.out.println("false" + " " + (i - 1));
                    break;
                }
                if (number / i < i) {
                    System.out.println("true" + " " + (i - 1));
                    break;
                }
            }
        }
    }
}
