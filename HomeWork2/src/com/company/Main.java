package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // 1
        System.out.println("№1");
        invertArray();
        // 2
        System.out.println("№2");
        fillArray();
        // 3
        System.out.println("№3");
        changeArray();
        // 4
        System.out.println("№4");
        fillDiagonal();
        // 5
        System.out.println("№5");
        findMaxMin();
        // 6
        System.out.println("№6");
        int[] arr1 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arr1));
        int[] arr2 = {1, 1, 1, 2, 3};
        System.out.println(checkBalance(arr2));
        System.out.println();
        // 7
        System.out.println("№7");
        System.out.println(Arrays.toString(moveArrayСyclic(arr1, 0)));
        System.out.println(Arrays.toString(moveArrayСyclic(arr1, -5)));
        System.out.println(Arrays.toString(moveArrayСyclic(arr1, 1)));
        System.out.println(Arrays.toString(moveArrayСyclic(arr1, -4)));
        System.out.println();
        System.out.println(Arrays.toString(moveArray(arr1, 1)));
        System.out.println(Arrays.toString(moveArray(arr1, -3)));
    }

    // 1
    public static void invertArray() {
        int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        System.out.println(Arrays.toString(arr));

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0)
                arr[i] = 1;
            else
                arr[i]=0;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // 2
    public static void fillArray() {
        int[] arr = new int[8];
        int massValue = 0;
        for(int i = 0; i < arr.length; i++){
            arr[i] = massValue;
            massValue += 3;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // 3
    public static void changeArray() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println(Arrays.toString(arr));
        for(int i = 0; i < arr.length; i++){
            if (arr[i] < 6)
                arr[i] *= 2;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // 4
    public static void fillDiagonal() {
        int size = (int) (Math.random() * 10 + 1);
        int[][] arr = new int[size][size];
        for(int i = 0; i < size; i++){
            arr[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 5
    public static void findMaxMin() {
        int[] arr = { 6, 12, 8, 24, 15, 13, 5, 2, 14, 19, 2, 21 };
        System.out.println(Arrays.toString(arr));
        int max = arr[0], min = arr[0];
        for(int i = 0; i < arr.length; i++){
            if (arr[i]>max)
                max = arr[i];
            else if(arr[i] < min)
                min = arr[i];
        }
        System.out.println("max = " + max + " min = " + min);
        System.out.println();
    }

    // 6
    public static boolean checkBalance(int[] arr){
        int leftSum = 0, rightSum = 0;
        for(int i = 0; i < arr.length - 1; i++){
            leftSum += arr[i];
            for(int j = i + 1; j < arr.length; j++){
                rightSum += arr[j];
            }

            if(leftSum == rightSum)
                return true;
            rightSum = 0;
        }
        return false;
    }

    // 7
    // Цикличный сдвиг
    public static int[] moveArrayСyclic(int[] arr, int n){
        int a = 0;
        int size = arr.length;
        if(n > 0) {
            for (int j = 0; j < n; j++) {
                for (int i = size - 1; i > 0; i--) {
                    if (i == size - 1) {
                        a = arr[size - 1];
                    }
                    arr[i] = arr[i - 1];
                }
                arr[0] = a;
            }
        } else {
            n = Math.abs(n);
            for (int j = 0; j < n; j++) {
                for (int i = 0; i <= size - 2; i++) {
                    if (i == 0) {
                        a = arr[i];
                    }
                    arr[i] = arr[i + 1];
                }
                arr[size - 1] = a;
            }
        }
        return arr;
    }
    // Нецикличный сдвиг
    public static int[] moveArray(int[] arr, int n){
        int size = arr.length;
        if(n > 0) {
            for (int i = size - 1; i >= 0; i--) {
                if(i - n >= 0 ){
                    arr[i] = arr[i-n];
                }
                else{
                    arr[i] = 0;
                }
            }
        } else {
            n = Math.abs(n);
            for (int i = 0; i <= size - 1; i++) {
                if(i + n <= size - 1){
                    arr[i] = arr[i+n];
                }
                else{
                    arr[i] = 0;
                }
            }
        }
        return arr;
    }

}
