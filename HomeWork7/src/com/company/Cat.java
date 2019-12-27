package com.company;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;
    public Cat(String name, int appetite){
        this.name = name;
        this.appetite = appetite;
        satiety = false;
    }
    public void eat(Plate p){
        // 3
        if(p.decreaseFood(appetite)){
            satiety = true;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAppetite(int appetite){
        this.appetite = appetite;
    }

    public void satietyInfo() {
        if(satiety == true){
            System.out.println("Кот по имени " + name + " сыт");
        } else {
            System.out.println("Кот по имени " + name + " голоден");
        }
    }
}
