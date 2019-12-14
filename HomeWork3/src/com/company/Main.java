package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 1
        startGame();

        // 2
        guessTheWord();
    }

    // 2
    public static void guessTheWord() {
        Scanner sc = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];
        String userAnswer;
        char a;

        // System.out.println(word);
        System.out.println("Угадайте слово");
        while(true){
            userAnswer = sc.nextLine();
            if(userAnswer.equals(word)){
                System.out.println("Вы отгадали слово");
                break;
            } else{
                System.out.println("Попробуйте снова");
                for(int i = 0; i < 15; i++) {
                    if (userAnswer.length() > i && word.length() > i) {
                        if (userAnswer.charAt(i) == word.charAt(i)) {
                            System.out.print(userAnswer.charAt(i));
                        } else {
                            System.out.print('#');
                        }
                    } else{
                        System.out.print('#');
                    }
                }
                System.out.println();
            }
        }
    }


    // 1
    public static void scannerIsInt(){
        while (!sc.hasNextInt()) {
            System.out.println("Вы ввели не целое число");
            sc.next();
        }
    }

    public static void startGame(){
        playGame();
        int userAnswer = 1;
        while(userAnswer != 0) {
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            scannerIsInt();
            userAnswer = sc.nextInt();
            if (userAnswer == 1) {
                playGame();
            }
        }
    }

    public static void playGame(){
        Random rand = new Random();
        int x = rand.nextInt(10);
        int userAnswer = -1;
        int tryCount = 0;
        while(tryCount < 3) {
            do {
                System.out.println("Введите число от 0 до 9");
                scannerIsInt();
                userAnswer = sc.nextInt();
            } while (userAnswer < 0 || userAnswer > 9);

            if (userAnswer == x) {
                System.out.println("Вы угадали число");
                break;
            } else if (userAnswer > x) {
                System.out.println("Вы назвали число которое больше чем загаданое");
            } else {
                System.out.println("Вы назвали число которое меньше чем загаданое");
            }
            tryCount++;
            if (tryCount == 3) {
                System.out.println("Ваши попытки кончились. \nx = " + x);
            }
        }
    }
}
