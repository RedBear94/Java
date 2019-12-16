package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public  static final char DOT_O = 'O';

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, DOTS_TO_WIN)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O, DOTS_TO_WIN)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");

        }

    public static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " \t");
        }
        System.out.println();
        for(int i = 0; i < SIZE; i++){
            System.out.print((i + 1) + " \t");
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Scanner sc = new Scanner(System.in);
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static Random rand = new Random();
    public static void aiTurn() {
        int x, y, f=0;
        // Используя созданные функции получаем возможные координаты для искуственного ителекта
        int[] blockPlayerWin, blockBetweenThree = {0,0,0}, blockTwo, whereMove, aiWin, HaveBetweenThree = {0,0,0}, blockThree, blockBetween, aiWin2, blockOne, whereMove2;
        // Обязательные координаты
        aiWin = checkAiMove(DOT_O, DOTS_TO_WIN-1); // Если есть выйграшная ситуация закончить игру своей победой
        aiWin2 = checkBetween(DOT_O, DOTS_TO_WIN);// Победа компьютера с разрывом в одну клетку
        blockPlayerWin = checkAiMove(DOT_X, DOTS_TO_WIN-1); // Блокирование победы игрока (Когда 1 клетка до победы)
        blockBetween = checkBetween(DOT_X, DOTS_TO_WIN); // Блок побед с разрывом в одну клетку
        // Вспомогательные правила улучшающие интеллект
        blockThree = checkAiMove(DOT_X, 3);// Блокировать 3 в ряд
        whereMove2 = checkAiMove(DOT_O, 3); // Если есть свои три O-ка то добавлять рядом с ними
        blockTwo = checkAiMove(DOT_X, 2); // Блокировать 2 в ряд
        whereMove = checkAiMove(DOT_O, 2); // Если есть свои два O-ка то добавлять рядом с ними
        blockOne = checkAiMove(DOT_X, 1); // Ставить рядом с крестиком (Полезно на 1-м ходе)
        if(DOTS_TO_WIN>3){
            // Имеет смысл только когда играют больше чем до 3-х в ряд
            blockBetweenThree = checkBetween(DOT_X, 3); // Блокировть ситуацию X•X
            HaveBetweenThree = checkBetween(DOT_O, 3); // Если есть ситуация O•O воспользоваться этим
        }

        do {
            // Перебор ситуаций по приоритету
            if(SIZE==3 && map[1][1]==DOT_EMPTY){
                x = 1;
                y = 1;
            }
            else if(aiWin[2] > 0){
                x = aiWin[0];
                y = aiWin[1];
            } else if(aiWin2[2]>0){
                x = aiWin2[0];
                y = aiWin2[1];
            } else if (blockPlayerWin[2] > 0){
                x = blockPlayerWin[0];
                y = blockPlayerWin[1];
            } else if(blockBetween[2]>0){
                x = blockBetween[0];
                y = blockBetween[1];
            } else if(blockBetweenThree[2] > 0){
                x = blockBetweenThree[0];
                y = blockBetweenThree[1];
            }/**/ else if(HaveBetweenThree[2] > 0){
                x = HaveBetweenThree[0];
                y = HaveBetweenThree[1];
            } else if(whereMove2[2]>0) {
                x = whereMove2[0];
                y = whereMove2[1];
            } else if(blockThree[2] > 0){
                x = blockThree[0];
                y = blockThree[1];
            } else if(whereMove[2] > 0){
                x = whereMove[0];
                y = whereMove[1];
            } else if(blockTwo[2] > 0){
                x = blockTwo[0];
                y = blockTwo[1];
            } else if(blockOne[2] > 0 && f==0){
                x = blockOne[0];
                y = blockOne[1];
                f=1;
            }/**/
            else{
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            }
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static int[] checkAiMove2(){
        int[] position;
        position = checkAiMove(DOT_X, DOTS_TO_WIN-2);
        return position;
    }

    public static int[] checkAiMove(char symb, int dots) {
        int symbCounterLine, symbCounterColumn, symbCounterDiagonalRight, symbCounterDiagonalLeft;
        symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;
        int[] position = {0,0,0}; // x, y, d

        for(int y = 0; y < map.length; y++){
            for (int x = 0; x < map.length; x++){
                for (int s = 0; s < dots; s++){
                    // Проверка победы в диогональ (слева направо)
                    if(x + s < map.length && y + s < map.length){
                        if(map[y+s][x+s] == symb){
                            symbCounterDiagonalRight++;
                        }
                        if(symbCounterDiagonalRight == dots){
                            if (x - 1 >= 0 && y - 1 >= 0 && map[y-1][x-1] == DOT_EMPTY) {
                                position[0] = x - 1;
                                position[1] = y - 1;
                                position[2] = 3;
                                return position;
                            } else if (y + dots < map.length && x + dots < map.length && map[y+dots][x+dots] == DOT_EMPTY) {
                                position[0] = x + dots;
                                position[1] = y + dots;
                                position[2] = 3;
                                return position;
                            }
                        }
                    }

                    // Проверка победы в диогональ (справа налево)
                    if (x - s >= 0 && y + s < map.length) {
                        if (map[y + s][x - s] == symb) {
                            symbCounterDiagonalLeft++;
                        }
                        if (symbCounterDiagonalLeft == dots) {
                            if (x - s >= 0 && y + s < map.length) {
                                if (x + 1 < map.length && y - 1 >= 0 && map[y-1][x+1] == DOT_EMPTY) {
                                    position[0] = x + 1;
                                    position[1] = y - 1;
                                    position[2] = 4;
                                    return position;
                                } else if (y + dots < map.length && x - dots >= 0 && map[y+dots][x-dots] == DOT_EMPTY) {
                                    position[0] = x - dots;
                                    position[1] = y + dots;
                                    position[2] = 4;
                                    return position;
                                }
                            }
                        }
                    }

                    if(x + s < map.length) {
                        // Проверка победы в строку
                        if (map[y][x + s] == symb) {
                            symbCounterLine++;
                        }
                        if (symbCounterLine == dots) {
                            if (x - 1 >= 0 && map[y][x - 1] == DOT_EMPTY) {
                                position[0] = x - 1;
                                position[1] = y;
                                position[2] = 1;
                                return position;
                            } else if (x + dots < map.length && map[y][x + dots] == DOT_EMPTY) {
                                position[0] = x + dots;
                                position[1] = y;
                                position[2] = 1;
                                return position;
                            }
                        }

                        // Проверка победы в столбец
                        if (map[x + s][y] == symb) {
                            symbCounterColumn++;
                        }
                        if (symbCounterColumn == dots) {
                            if (x-1 >= 0 && map[x-1][y] == DOT_EMPTY) {
                                position[0] = y;
                                position[1] = x - 1;
                                position[2] = 2;
                                return position;
                            } else if (x + dots < map.length && map[x+dots][y] == DOT_EMPTY) {
                                position[0] = y;
                                position[1] = x + dots;
                                position[2] = 2;
                                return position;
                            }
                        }
                    }
                }
                symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;
            }
        }
        return position;
    }

    public static boolean checkWin(char symb, int dots) {
        int symbCounterLine, symbCounterColumn, symbCounterDiagonalRight, symbCounterDiagonalLeft;
        symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;

        for(int y = 0; y < map.length; y++){
            for (int x = 0; x < map.length; x++){
                for (int s = 0; s < dots; s++){
                    if(x + s < map.length) {
                        // Проверка победы в строку
                        if (map[y][x + s] == symb) {
                            symbCounterLine++;
                        }
                        if (symbCounterLine == dots) {
                            return true;
                        }

                        // Проверка победы в столбец
                        if (map[x + s][y] == symb) {
                            symbCounterColumn++;
                        }
                        if (symbCounterColumn == dots) {
                            return true;
                        }
                    }

                    // Проверка победы в диогональ (слева направо)
                    if(x + s < map.length && y + s < map.length){
                        if(map[y+s][x+s] == symb){
                            symbCounterDiagonalRight++;
                        }
                        if(symbCounterDiagonalRight == dots){
                            return true;
                        }
                    }

                    // Проверка победы в диогональ (справа налево)
                    if (x - s >= 0 && y + s < map.length) {
                        if (map[y + s][x - s] == symb) {
                            symbCounterDiagonalLeft++;
                        }
                        if (symbCounterDiagonalLeft == dots) {
                            return true;
                        }
                    }
                }
                symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;
            }
        }
        return false;
    }

    public static int[] checkBetween(char symb, int dots) {
        int symbCounterLine, symbCounterColumn, symbCounterDiagonalRight, symbCounterDiagonalLeft;
        symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;
        int[] position = {0,0,0}; // x, y, d

        for(int y = 0; y < map.length; y++){
            for (int x = 0; x < map.length; x++){
                for (int s = 0; s < dots; s++){
                    // Проверка победы в диогональ (слева направо)
                    if(x + s < map.length && y + s < map.length){
                        if(map[y+s][x+s] == symb){
                            symbCounterDiagonalRight++;
                        }
                    }

                    // Проверка победы в диогональ (справа налево)
                    if (x - s >= 0 && y + s < map.length) {
                        if (map[y + s][x - s] == symb) {
                            symbCounterDiagonalLeft++;
                        }
                    }

                    if(x + s < map.length) {
                        // Проверка победы в строку
                        if (map[y][x + s] == symb) {
                            symbCounterLine++;
                        }

                        // Проверка победы в столбец
                        if (map[x + s][y] == symb) {
                            symbCounterColumn++;
                        }
                    }
                }
                // Проверка победы в диогональ (слева направо)
                if(y+dots-1 < map.length && x+dots-1 < map.length && symbCounterDiagonalRight==dots-1 && map[y][x]==symb && map[y+dots-1][x+dots-1]==symb) {
                    for (int s = 1; s < dots - 1; s++) {
                        if(map[y+s][x+s]==DOT_EMPTY){
                            position[0] = x + s;
                            position[1] = y + s;
                            position[2] = 3;
                            return position;
                        }
                    }
                }
                // Проверка победы в диогональ (справа налево)
                if(y+dots-1 < map.length && x-dots+1 >= 0 && symbCounterDiagonalLeft==dots-1 && map[y][x]==symb && map[y+dots-1][x-dots+1]==symb) {
                    for (int s = 1; s < dots - 1; s++) {
                        if(map[y+s][x-s]==DOT_EMPTY){
                            position[0] = x - s;
                            position[1] = y + s;
                            position[2] = 4;
                            return position;
                        }
                    }
                }
                // Проверка победы в строку
                if(x+dots-1 < map.length && symbCounterLine==dots-1 && map[y][x]==symb && map[y][x+dots-1]==symb) {
                    for (int s = 1; s < dots - 1; s++) {
                        if(map[y][x+s]==DOT_EMPTY){
                            position[0] = x + s;
                            position[1] = y;
                            position[2] = 1;
                            return position;
                        }
                    }
                }
                // Проверка победы в столбец
                if(x+dots-1 < map.length && symbCounterColumn==dots-1 && map[x][y]==symb && map[x+dots-1][y]==symb) {
                    for (int s = 1; s < dots - 1; s++) {
                        if(map[x+s][y]==DOT_EMPTY){
                            position[0] = y;
                            position[1] = x + s;
                            position[2] = 2;
                            return position;
                        }
                    }
                }
                symbCounterLine = symbCounterColumn = symbCounterDiagonalRight = symbCounterDiagonalLeft = 0;
            }
        }
        return position;
    }
}
