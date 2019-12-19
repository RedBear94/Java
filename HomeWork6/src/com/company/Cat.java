package com.company;

public class Cat extends Animal {
    public static int CatCount;

    public Cat(String name){
        this.name = name;
        CatCount++;
    }

    @Override
    public void swim(int distance){
        System.out.println("Коты не умеют плавать");
    }

    @Override
    public void run(int distance){
        if(distance > 200){
            System.out.println(name + " не может пробежать дистанцию в " + distance + " м");
        } else{
            super.run(distance);
        }
    }

    @Override
    public void jump(float distance){
        if(distance > 2){
            System.out.println(name + " не может прыгать на " + distance + " м");
        } else{
            super.jump(distance);
        }
    }
}
