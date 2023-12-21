package Droid;

import java.util.Random;
import java.util.Scanner;

public class ArmedDroid extends Droid {  // Похідний клас ArmedDroid від базового класу Droid

    protected int[] gun = {5, 10, 50, 100};  // Масив з можливими значеннями для зброї

    public ArmedDroid() {  // Конструктор класу ArmedDroid

        droidGeneration();  // Виклик методу для генерації характеристик озброєного дроїда
    }

    public void droidGeneration() {  // Метод для генерації випадкових характеристик озброєного дроїда
        Random rand = new Random();

        // Генерація випадкових значень здоров'я та пошкоджень, враховуючи базові характеристики дроїда
        int randomHealth = rand.nextInt(20001) + (int) (getHealth() * 0.1);
        int randomDamage = rand.nextInt(15001) + gun[rand.nextInt(gun.length)];

        this.setHealth(randomHealth);  // Встановлення випадкового значення для здоров'я дроїда
        this.setDamage(randomDamage);  // Встановлення випадкового значення для пошкоджень дроїда

        Scanner reader = new Scanner(System.in);
        System.out.println("\nВведіть назву дроїда:");  // Запит на введення назви дроїда
        String droidName = reader.nextLine();
        this.setName(droidName);  // Встановлення назви дроїда
    }

}
