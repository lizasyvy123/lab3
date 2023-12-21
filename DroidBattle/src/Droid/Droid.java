package Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Droid {
    protected String name;  // Назва дроїда
    protected int health;   // Кількість здоров'я дроїда
    protected int damage;   // Кількість пошкоджень, які дроїд може завдати

    public Droid(){  // Конструктор класу Droid

        DroidGeneration();  // Виклик методу для генерації випадкових характеристик дроїда
    };

    public void DroidGeneration(){  // Метод для генерації випадкових характеристик дроїда
        Random rand = new Random();
        this.setHealth(rand.nextInt(20001));  // Встановлення випадкового значення для здоров'я дроїда
        this.setDamage(rand.nextInt(10001));  // Встановлення випадкового значення для пошкоджень дроїда
        Scanner reader = new Scanner(System.in);
        System.out.println("\nВведіть назву дроїда:");  // Запит на введення назви дроїда
        this.setName(reader.nextLine());  // Встановлення назви дроїда
    }

    // Методи для отримання значень полів класу
    public int getDamage() { return damage; }
    public String getName() { return name; }
    public void setHealth(int health) { this.health = health; }  // Встановлення значення здоров'я
    public void setDamage(int damage) { this.damage = damage; }  // Встановлення значення пошкоджень
    public void setName(String name) { this.name = name; }  // Встановлення назви дроїда
    public int getHealth() { return health; }  // Отримання значення здоров'я

    // Метод для атаки дроїда
    public void attack(Droid victim, FileWriter myWriter) {
        System.out.println("\nДроїд " + this.name + " завдав урону " + this.damage);  // Виведення повідомлення про атаку
        try {
            myWriter.write("\nДроїд " + this.name + " завдав урону " + this.damage);  // Запис атаки до файлу
        } catch (IOException e) {
            System.out.println("Помилка під час запису до файлу.");  // Виведення повідомлення про помилку запису
            e.printStackTrace();
        }

        if (victim.getHealth() != 0) {  // Перевірка наявності здоров'я у жертви
            victim.health -= this.damage;  // Зменшення здоров'я жертви на величину урону
            this.damage = this.damage / 2;  // Зменшення поточного урону напів
            if (victim.health < 0) {
                victim.health = 0;
                System.out.println("\nДроїд " + victim.name + " більше не може атакувати.");  // Повідомлення про неможливість атаки жертви
            }
        } else {
            System.out.println("\nЗдоров'я жертви вже 0. Додатковий урон не може бути завданий.");  // Повідомлення про відсутність здоров'я жертви
        }
    }

    // Перевірка наявності здоров'я дроїда
    public boolean isAlive() {
        return health > 0;  // Повертає true, якщо дроїд живий
    }

    // Перевизначений метод toString для виведення характеристик дроїда
    @Override
    public String toString() {
        return "\nНазва дроїда: " + this.name + "\nКількість здоров'я: " + this.health + "\nКількість пошкоджень: " + this.damage;
    }

}
