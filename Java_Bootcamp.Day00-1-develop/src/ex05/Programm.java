package ex05;

import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {
        drawSchedule();
    }

    public static void drawSchedule() {
        String[] names = new String[10];
        String[] classes = new String[10];
        String[] schedule = new String[50];
        String[] attendance = new String[500];
        fillNamesArr(names);
        fillClasses(classes);
        fillSchedule(classes, schedule);
        fillAttendance(attendance);
        printSchedule(names, schedule, attendance);
    }

    public static void fillClasses(String[] classes) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while (count < 10 && !name.equals(".")) {
            name = scanner.nextLine();
            if (!name.equals(".")) {
                char[] charArr = new char[7];
                char[] nameArr = name.toCharArray();
                int counter = 0;
                for (int i = 0; i < name.length(); i++) {
                    if (i == 0) {
                        charArr[counter++] = nameArr[i];
                        charArr[counter++] = ':';
                        charArr[counter++] = '0';
                        charArr[counter++] = '0';
                    } else {
                        charArr[counter++] = nameArr[i];
                    }
                }
                String tmp = new String(charArr);
                classes[count++] = tmp;
            }
        }
    }

    public static void fillNamesArr(String[] names) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while (count < 10 && !name.equals(".")) {
            name = scanner.nextLine();
            if (!name.equals(".")) {
                names[count++] = name;
            }
        }
    }

    public static void fillSchedule(String[] classes, String[] schedule) {
        int countSch = 0;
        for (String aClass : classes) {
            if (aClass != null) {
                int lastDay = calcFirstDay(aClass);
                while (lastDay <= 30) {
                    if (lastDay < 10) {
                        schedule[countSch++] = aClass + "  " + lastDay;
                    } else {
                        schedule[countSch++] = aClass + " " + lastDay;
                    }
                    lastDay += 7;
                }
            }
        }
        sortSchedule(schedule);
    }

    public static int calcFirstDay(String cls) {
        int date = 0;
        char[] chCls = cls.toCharArray();
        switch (chCls[5]) {
            case 'M':
                date = 7;
                break;
            case 'T':
                if (chCls[6] == 'U') date = 1;
                else if (chCls[6] == 'H') date = 3;
                break;
            case 'W':
                date = 2;
                break;
            case 'F':
                date = 4;
                break;
            case 'S':
                if (chCls[6] == 'A') {
                    date = 5;
                } else if (chCls[6] == 'U') {
                    date = 6;
                }
                break;
            default:
        }
        return date;
    }

    public static void sortSchedule(String[] schedule) {
        for (int i = schedule.length - 1; i >= 1; --i) {
            for (int j = 0; j < i; ++j) {
                if (schedule[j] != null && schedule[j + 1] != null) {
                    char[] firstArr = schedule[j].toCharArray();
                    char[] secondArr = schedule[j + 1].toCharArray();
                    if (firstArr.length == secondArr.length) {
                        if (firstArr.length == 9) {
                            if (firstArr[8] > secondArr[8]) {
                                replaceLogic(schedule, j);
                            } else if (firstArr[8] == secondArr[8]) {
                                if (firstArr[0] > secondArr[0]) {
                                    replaceLogic(schedule, j);
                                }
                            }
                        } else if (firstArr.length == 10) {
                            if (firstArr[8] > secondArr[8]) {
                                replaceLogic(schedule, j);
                            } else if (firstArr[8] == secondArr[8]) {
                                if (firstArr[9] > secondArr[9]) {
                                    replaceLogic(schedule, j);
                                } else if (firstArr[9] == secondArr[9]) {
                                    if (firstArr[0] > secondArr[0]) {
                                        replaceLogic(schedule, j);
                                    }
                                }
                            }
                        }
                    } else if (firstArr.length > secondArr.length) {
                        replaceLogic(schedule, j);
                    }
                }
            }
        }
    }

    public static void replaceLogic(String[] schedule, int j) {
        String tmp = schedule[j + 1];
        schedule[j + 1] = schedule[j];
        schedule[j] = tmp;
    }

    public static void fillAttendance(String[] attendance) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        String tmp = "";
        while (count < attendance.length && !tmp.equals(".")) {
            tmp = scanner.nextLine();
            if (!tmp.equals(".")) {
                attendance[count++] = tmp;
            }
        }
    }

    public static void printSchedule(String[] names, String[] schedule,
                                     String[] attendance) {
        System.out.print("          ");
        for (String sch : schedule) {
            if (sch != null) {
                System.out.print(sch + "|");
            }
        }
        for (String name : names) {
            if (name != null) {
                System.out.println();
                for (int i = 0; i < 10 - name.length(); ++i) {
                    System.out.print(" ");
                }
                System.out.print(name);
                for (String sch : schedule) {
                    if (sch != null) {
                        char[] schArr = sch.toCharArray();
                        int match = attendanceLogic(name, attendance, schArr);
                        System.out.print("        ");
                        if (match == 0) {
                            System.out.print("  |");
                        } else {
                            if (match == -1) {
                                System.out.print("-1|");
                            } else {
                                System.out.print(" 1|");
                            }
                        }
                    }
                }
            }
        }
    }

    public static int attendanceLogic(String name, String[] attendance,
                                      char[] schArr) {
        int match = 0;
        for (String att : attendance) {
            if (att == null) {
                break;
            }
            char[] attArr = att.toCharArray();
            char[] attName = new char[10];
            if (compareNames(name, attName, attArr)) {
                if (schArr[0] == attArr[name.length() + 1]) {
                    boolean here = attArr.length == name.length() + 9
                            || attArr.length == name.length() + 10;
                    boolean notHere = attArr.length == name.length() + 13
                            || attArr.length == name.length() + 14;
                    if (attArr[name.length() + 4] != ' ') {
                        if (schArr[8] == attArr[name.length() + 3]
                                && schArr[9] == attArr[name.length() + 4]) {
                            if (notHere) {
                                match = -1;
                            } else if (here) {
                                match = 1;
                            }
                        }
                    } else {
                        if (schArr[9] == attArr[name.length() + 3]) {
                            if (notHere) {
                                match = -1;
                            } else if (here) {
                                match = 1;
                            }
                        }
                    }
                }
            }
        }
        return match;
    }

    public static boolean compareNames(String name, char[] attName,
                                       char[] attArr) {
        boolean result = true;
        char[] chName = name.toCharArray();
        for (int i = 0; attArr[i] != ' '; ++i) {
            attName[i] = attArr[i];
        }
        for (int i = 0; i < attName.length; ++i) {
            if (i >= chName.length) {
                if (attName[i] != '\u0000') {
                    result = false;
                }
                break;
            }
            if (chName[i] != attName[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
