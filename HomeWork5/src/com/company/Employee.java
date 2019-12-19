package com.company;

public class Employee {
    private String FirstLastName, Position, Email, Phone;
    private int Salary, Age;
    public Employee(String FirstLastName,
                    String Position,
                    String Email,
                    String Phone,
                    int Salary,
                    int Age){
        this.FirstLastName = FirstLastName;
        this.Position = Position;
        this.Email = Email;
        this.Phone = Phone;
        this.Salary = Salary;
        this.Age = Age;
    }

    public void printInfo(){
        System.out.println(
                "Имя и Фамилия: " + FirstLastName + "\n" +
                        "Работа: " + Position + "\n" +
                        "Почта: " + Email + "\n" +
                        "Телефон: " + Phone + "\n" +
                        "Зарплата: " + Salary + "\n" +
                        "Возраст: " + Age + "\n"
        );
    }

    public int getAge() {
        return Age;
    }
}
