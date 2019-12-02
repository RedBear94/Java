package com.company;

public class JavaApp {
    // 1
    public static void main(String[] args) {
        // 2
        byte b = 105;
        short s = -24569;
        int i = 20400;
        long l = 40000000L;
        float f = 445.34f;
        double d = 2167.4313;
        char c = 'G';
        boolean bl = true ;

        // 3
        System.out.println(calculateExpression(5f,3f,4f,2f));

        // 4
        System.out.println(checkSegmentFrom10to20(3,5)); // false
        System.out.println(checkSegmentFrom10to20(5,5)); // true
        System.out.println(checkSegmentFrom10to20(25,5)); // false

        // 5
        isPositiveOrNegative(-5);
        isPositiveOrNegative(0);
        isPositiveOrNegative(6);

        // 6
        System.out.println(isNegative(-3));
        System.out.println(isNegative(3));

        // 7
        printHello("Павел");

        // 8
        isThisYearLeap(400);
        isThisYearLeap(4);
        isThisYearLeap(100);
        isThisYearLeap(500);
        isThisYearLeap(1200);
        isThisYearLeap(2020);
    }

    // 3
    public static float calculateExpression(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    // 4
    public static boolean checkSegmentFrom10to20 (int a, int b){
        int sum = a + b;
        return (sum >= 10 && sum <= 20);
    }

    // 5
    public static void  isPositiveOrNegative(int a){
        if (a >= 0) {
            System.out.println(a + " - Положительное число");
        } else {
            System.out.println(a + " - Отрицательное");
        }
    }

    // 6
    public static boolean isNegative(int a){
        if (a < 0) {
            return true;
        } else {
           return false;
        }
    }

    // 7
    public static void printHello(String Name) {
        System.out.println("Привет, " + Name);
    }

    // 8
    public static void isThisYearLeap(int year) {
        if ( (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
        {
            System.out.println( year + " - Високосный");
        }
        else {
            System.out.println( year + " - Не Високосный");
        }
    }
}
