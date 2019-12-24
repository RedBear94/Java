package com.company;

public class Main {

    public static void main(String[] args) {
        /*Test Methods*/
        Cat cat = new Cat("Barsik", 5);
        Plate plate = new Plate(100);
        plate.info();
        cat.eat(plate);
        plate.info();
        plate.info();
        Cat cat2 = new Cat("Barsik", 200);
        cat2.eat(plate);
        plate.info();
        plate.addFood(110);
        cat2.eat(plate);
        plate.info();
        System.out.println();
        /**/

        // 5
        // Создание массива котов и тарелки
        Cat [] cats = new Cat[3];
        cats[0] = new Cat ("Barsik", 20);
        cats[1] = new Cat ("Pushok", 30);
        cats[2] = new Cat ("Kitty", 40);
        Plate newPlate = new Plate(60);
        // Каждый кот пробует поесть из тарелки
        for(int i = 0; i < cats.length ; i++){
            cats[i].eat(newPlate);
        }
        // Вывод информации о всех котах из массива
        for(int i = 0; i < cats.length ; i++){
            cats[i].satietyInfo();
        }
    }
}
