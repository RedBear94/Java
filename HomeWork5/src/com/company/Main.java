package com.company;

public class Main {
    public static void main(String[] args) {
        Employee[] employeesArray = new Employee[5];
        employeesArray[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com",
                "892312312", 50000, 25);
        employeesArray[1] = new Employee("Smirnov Pavel", "Engineer", "pavel@mailbox.com",
                "892416372", 60000, 35);
        employeesArray[2] = new Employee("Popova Inna", "Engineer", "inna@mailbox.com",
                "894512612", 40000, 40);
        employeesArray[3] = new Employee("Volkov Alexander", "Engineer", "alex@mailbox.com",
                "892216372", 70000, 55);
        employeesArray[4] = new Employee("Andreev Georgiy", "Engineer", "georgiy@mailbox.com",
                "891114322", 65000, 65);
        for(int i = 0; i<employeesArray.length; i++){
            if (employeesArray[i].getAge() > 40 ) {
                employeesArray[i].printInfo();
            }
        }
    }
}

