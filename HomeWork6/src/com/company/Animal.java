package com.company;

public class Animal {
    String name;

    public static int AnimalCount;

    public Animal(){
        AnimalCount++;
    }

    public Animal(String name){
        this();
        this.name = name;
    }

    public void run(int distance){
        System.out.println(name + " пробежал " + distance + " м");
    }

    public void swim(int distance){
        System.out.println(name + " пропылл " + distance + " м");
    }
}