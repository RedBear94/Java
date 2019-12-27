package com.company;

public class Plate {
    private int food;
    public Plate(int food){
        this.food = food;
    }
    public void info(){
        System.out.println("plate: " + food);
    }
    public boolean decreaseFood(int n){
        // 2 и 4
        if(n<food) {
            food -=n;
            return true;
        } else{
            System.out.println("В тарелке не достаточно еды");
            return false;
        }
    }

    // 6
    public void addFood(int f){
        food += f;
    }
}
