package ex03;

import java.util.Scanner;

public class Programm {

    public static void main(String[] args) {
        String result = drawGraph();
        System.out.println(result);
    }

    public static String drawGraph() {
        Scanner weekScanner = new Scanner(System.in);
        String str = "", result = "";
        int countWeeks = 0;
        while (!str.equals("42") && ++countWeeks <= 18) {
            str = weekScanner.nextLine();
            if (str.equals("42")) continue;
            if (!isValidWeek(str, countWeeks)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            result = buildResult(result, countWeeks, minGrade());
        }
        return result;
    }

    public static int minGrade() {
        Scanner markScanner = new Scanner(System.in);
        int result = 0;
        int minRes = 10;
        for (int i = 0; i < 5; i++) {
            result = markScanner.nextInt();
            if (!isValidMark(result)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            minRes = (result < minRes) ? result : minRes;
        }
        return minRes;
    }

    public static boolean isValidWeek(String str, int countWeeks) {
        return str.equals("Week " + countWeeks);
    }

    public static boolean isValidMark(int mark) {
        return ((mark >= 0) && (mark <= 9));
    }

    public static String buildResult(String str, int countWeek, int grade) {
        String graph = "";
        String result = "";
        graph = switch (grade) {
            case 1 -> "=>";
            case 2 -> "==>";
            case 3 -> "===>";
            case 4 -> "====>";
            case 5 -> "=====>";
            case 6 -> "======>";
            case 7 -> "=======>";
            case 8 -> "========>";
            case 9 -> "=========>";
            default -> graph;
        };
        if (!str.equals(""))
            result = str + "\n" + "Week " + countWeek + " " + graph;
        else
            result = str + "Week " + countWeek + " " + graph;
        return result;
    }

}
