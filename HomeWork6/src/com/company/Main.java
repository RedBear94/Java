package com.company;

public class Main {

    public static void main(String[] args) {
	Animal p = new Cat("Пушок");
	p.swim(400);
	p.run(500);
	p.run(150);

	Animal b = new Dog("Бобик");
	b.swim(200);
	b.swim(5);
	b.run(300);

	System.out.println("Число созданных животных: " + Animal.AnimalCount);
	System.out.println("Число созданных котов: " + Dog.DogCount);
	System.out.println("Число созданных собак: " + Cat.CatCount);
    }
}
