package ex04;

import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        numChars(inputString);
    }

    public static void numChars(String str) {
        char[] arr = str.toCharArray();
        int[] numArr = new int[65535];
        char[] chars = new char[65535];
        int index = 0;
        for (char c : arr) {
            index = checkChar(chars, c);
            numArr[index] += 1;
            chars[index] = c;
        }
        sortMas(numArr, chars, index + 1);
        buildGist(numArr, chars, index + 1);
    }

    public static int checkChar(char[] chars, char ch) {
        int result = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == ch || chars[i] == '\u0000') {
                result = i;
                break;
            }
        }
        return result;
    }

    public static void sortMas(int[] nums, char[] chars, int size) {
        for (int i = size - 1; i >= 1; --i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[j + 1]) {
                    int tmp = nums[j];
                    char chTmp = chars[j];
                    nums[j] = nums[j + 1];
                    chars[j] = chars[j + 1];
                    nums[j + 1] = tmp;
                    chars[j + 1] = chTmp;
                } else if (nums[j] == nums[j + 1]) {
                    char compChThis = chars[j];
                    char compChNext = chars[j + 1];
                    if (chars[j] >= 65 && chars[j] <= 90) {
                        compChThis = (char) (chars[j] + 32);
                    }
                    if (chars[j + 1] >= 65 && chars[j + 1] <= 90) {
                        compChNext = (char) (chars[j + 1] + 32);
                    }
                    if (compChThis > compChNext) {
                        char chTmp = chars[j];
                        chars[j] = chars[j + 1];
                        chars[j + 1] = chTmp;
                    }
                }
            }
        }
    }

    public static void buildGist(int[] nums, char[] chars, int size) {
        int len = size >= 10 ? 10 : size;
        double oneCell = nums[0] / 10.0;
        int[] cells = new int[size];
        for (int i = 0; i < len; ++i) {
            cells[i] = (int) (nums[i] / oneCell);
        }
        for (int i = 0; i < 12; i++) {
            printStr(i, nums, chars, cells, len);
            if (i != 11) {
                System.out.println();
            }
        }
    }

    public static void printStr(int numStr, int[] nums, char[] chars,
                                int[] cells, int len) {
        for (int i = 0; i < len; i++) {
            if ((12 - cells[i] - 2) == numStr) {
                if (countDigits(nums[i]) == 1) {
                    System.out.print(nums[i] + "   ");
                } else if (countDigits(nums[i]) == 2) {
                    System.out.print(nums[i] + "  ");
                } else {
                    System.out.print(nums[i] + " ");
                }
            }
            if (numStr > (12 - cells[i] - 2) && numStr < 11) {
                if (i == 0) {
                    if (countDigits(nums[i]) == 2) {
                        System.out.print(" ");
                    } else if (countDigits(nums[i]) == 3) {
                        System.out.print("  ");
                    }
                }
                if (i != len - 1 && (12 - cells[i + 1] - 2) == numStr) {
                    if (countDigits(nums[i + 1]) == 1) {
                        System.out.print("#   ");
                    } else if (countDigits(nums[i + 1]) == 2) {
                        System.out.print("#  ");
                    } else {
                        System.out.print("# ");
                    }
                } else {
                    System.out.print("#   ");
                }
            }
            if (numStr == 11) {
                if (i == 0) {
                    if (countDigits(nums[i]) == 2) {
                        System.out.print(" ");
                    } else if (countDigits(nums[i]) == 3) {
                        System.out.print("  ");
                    }
                }
                System.out.print(chars[i] + "   ");
            }
        }
    }

    public static int countDigits(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count += 1;
        }
        return count;
    }
}
