package com.company;

public class Dog extends Animal {
    public static int DogCount;

    public Dog(String name){
        DogCount++;
        this.name = name;
    }

    @Override
    public void swim(int distance){
        if(distance > 10){
            System.out.println(name + " не может проплыть дистанцию в " + distance + " м");
        } else{
            super.swim(distance);
        }
    }

    @Override
    public void run(int distance){
        if(distance > 500){
            System.out.println(name + " не может пробежать дистанцию в " + distance + " м");
        } else{
            super.run(distance);
        }
    }
}
