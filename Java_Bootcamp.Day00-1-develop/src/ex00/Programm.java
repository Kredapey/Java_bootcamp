package ex00;

public class Programm {
    public static void main(String[] args) {
        int number = 123456;
        System.out.println(sumDigitNum(number));
    }

    public static int sumDigitNum(int num) {
        if (num / 10 > 0) {
            return (num % 10) + sumDigitNum(num / 10);
        } else {
            return num % 10;
        }
    }
}
