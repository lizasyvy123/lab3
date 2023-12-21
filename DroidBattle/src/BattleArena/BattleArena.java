package BattleArena;
import Droid.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleArena {
    // Функція для створення файлу
    public static void createFile() {
        try {
            File myFile = new File("C:\\file.txt");
            if (myFile.createNewFile()) {
                System.out.println("Файл створено: " + myFile.getName() + "\n");
            } else {
                System.out.println("Файл вже існує.\n");
                myFile.delete();
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Помилка знайдена.\n");
            e.printStackTrace();
        }
    }

    // Головний метод програми, що запускається при старті
    public static void main(String[] args) {
        createFile(); // Виклик функції для створення файлу
        List<Droid> droidList = new ArrayList<>(); // Створення списку дроїдів
        menu(droidList); // Виклик головного меню програми
    }

    // Генерація команди дроїдів
    public static Droid[] TeamGeneration(int count) {
        // Створення масиву для дроїдів певної кількості
        Droid[] droids = new Droid[count];

        // Створення генератора випадкових чисел
        Random rand = new Random();

        // Проходження по кількості дроїдів, які потрібно згенерувати
        for (int i = 0; i < count; i++) {
            // Вибір випадкового числа в межах від 0 до 2
            int randIndex = rand.nextInt(3);

            // В залежності від отриманого випадкового числа вибирається тип дроїда для створення
            switch (randIndex) {
                case 0:
                    // Створення звичайного дроїда
                    droids[i] = new Droid();
                    break;
                case 1:
                    // Створення озброєного дроїда
                    droids[i] = new ArmedDroid();
                    break;
                case 2:
                    // Створення дроїда-щита
                    droids[i] = new ShieldDroid();
                    break;
            }
        }

        // Повідомлення про успішне створення команди
        System.out.println("Команду згенеровано!");

        // Повернення масиву згенерованих дроїдів
        return droids;
    }


    // Головне меню програми
    public static void menu(List<Droid> droidList) {
        Scanner reader = new Scanner(System.in);
        int choice;

        do {
            // Виведення опцій головного меню
            System.out.println("Вітаю на арені битви долі дроїдів!\nЦя програма має декілька функцій:");
            System.out.println("1 - Створення дроїда обраного типу");
            System.out.println("2 - Виведення списку створених дроїдів");
            System.out.println("3 - Битва один на один дроїдами будь-якого типу");
            System.out.println("4 - Битва випадково згенерованих команд");
            System.out.println("5 - Вихід");

            choice = reader.nextInt(); // Отримання вибору користувача

            switch (choice) {
                case 1:
                    GenerationByChoice(droidList); // Виклик функції для створення дроїда за вибором користувача
                    break;
                case 2:
                    for (Object droid : droidList)
                        System.out.println(droid); // Виведення списку створених дроїдів
                    break;
                case 3:
                    OneByOneBattle(droidList); // Виклик функції для битви один на один
                    break;
                case 4:
                    System.out.println("\n\tВведіть кількість учасників у кожній команді: ");
                    int count = reader.nextInt();
                    teamByTeamBattle(count); // Виклик функції для битви командами
                    break;
                case 5:
                    System.out.println("Дякую за гру! Ви вийшли з програми."); // Завершення програми
                    break;
                default:
                    System.out.println("Схоже, ви ввели некоректні дані!");
                    break;
            }
        } while (choice != 5); // Повторення циклу, поки користувач не вибере вихід з програми
    }

    // Функція створення дроїда за вибором користувача
    public static <T extends Droid> T GenerationByChoice(List<T> droidList) {
        // Повідомлення для вибору типу дроїда
        System.out.println("Якщо ви бажаєте створити звичайного дроїда натисніть 1, якщо озброєного 2, а дроїда-щита - 3");

        // Ініціалізація сканера для отримання вводу від користувача
        Scanner reader = new Scanner(System.in);

        // Отримання вибору користувача
        int choice = reader.nextInt();
        T droid = null;

        // Логіка для створення обраного типу дроїда
        switch (choice) {
            case 1:
                droid = (T) new Droid(); // Створення звичайного дроїда
                droidList.add(droid); // Додавання дроїда до списку
                System.out.println("Створено звичайного дроїда!");
                break;
            case 2:
                droid = (T) new ArmedDroid(); // Створення озброєного дроїда
                droidList.add(droid); // Додавання дроїда до списку
                System.out.println("Створено озброєного дроїда!");
                break;
            case 3:
                droid = (T) new ShieldDroid(); // Створення дроїда-щита
                droidList.add(droid); // Додавання дроїда до списку
                System.out.println("Створено дроїда-щита!");
                break;
            default:
                System.out.println("Схоже ви ввели некорректне значення!");
        }
        return droid;
    }


    // Проведення битви один на один між дроїдами
    public static <T extends Droid> void OneByOneBattle(List<T> droidList) {
        try (FileWriter writer = new FileWriter("file.txt")) { // Використання FileWriter для запису у файл
            Random rand = new Random();
            T player1 = GenerationByChoice(droidList); // Створення першого гравця
            T player2;
            do {
                player2 = GenerationByChoice(droidList); // Створення другого гравця
            } while (player1.equals(player2)); // Перевірка на рівність гравців

            boolean attackerIndex = rand.nextBoolean(); // Випадкове визначення, хто атакує першим

            while (player1.isAlive() && player2.isAlive()) { // Поки обидва гравці живі
                if (attackerIndex) {
                    player1.attack(player2, writer); // Атака першого гравця на другого
                } else {
                    player2.attack(player1, writer); // Атака другого гравця на першого
                }
                attackerIndex = !attackerIndex; // Зміна хто атакує у наступному раунді
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Функція для проведення битви командами
    public static void teamByTeamBattle(int count) {
        Random rnd = new Random();
        Droid[] team1 = TeamGeneration(count); // Створення першої команди
        Droid[] team2 = TeamGeneration(count); // Створення другої команди

        int round = 0;
        while (areTeamsAlive(team1, team2)) {
            System.out.println("Раунд " + (++round)); // Виведення номеру раунду
            teamBattleRound(team1, team2, rnd); // Функція для проведення одного раунду битви
        }
        // Визначення переможця або нічії
        if (areTeamsAlive(team1, team2)) {
            System.out.println("Команда 1 перемагає!"); // Перша команда перемогла
        } else if (areTeamsAlive(team2, team1)) {
            System.out.println("Команда 2 перемагає!"); // Друга команда перемогла
        } else {
            System.out.println("Нічия!!"); // Нічия
        }
    }

    // Функція для проведення одного раунду битви командами
    public static <T extends Droid> void teamBattleRound(T[] team1, T[] team2, Random rnd) {
        // Ініціалізація змінних для обчислення урону та здоров'я команд
        int totalTeam1Damage = 0;
        int totalTeam2Damage = 0;
        int totalTeam1Health = 0;
        int totalTeam2Health = 0;

        // Цикл, що перебирає кожного дроїда в обох командах
        for (int i = 0; i < team1.length; i++) {
            // Перевірка чи дроїди можуть атакувати
            if (team1[i].getHealth() > 0 && team2[i].getHealth() > 0) {
                // Випадкове визначення, яка команда атакує
                boolean team1Attack = rnd.nextBoolean();
                T attacker = team1Attack ? team1[i] : team2[i]; // Визначення атакуючого дроїда
                T target = team1Attack ? team2[i] : team1[i]; // Визначення цілі атаки

                // Випадковий урон атакуючого дроїда
                int damage = rnd.nextInt(attacker.getDamage());
                int health = target.getHealth(); // Отримання здоров'я цілі

                // Перевірка, чи є атакуючий дроїд щитом, і відображення події
                if (attacker instanceof ShieldDroid) {
                    System.out.println(attacker.getName() + " Не може використовувати щит у цьому раунді.");
                } else {
                    // Розподіл урону в залежності від атакуючої команди
                    if (team1Attack) {
                        totalTeam1Damage += damage;
                        totalTeam2Health -= damage;
                    } else {
                        totalTeam2Damage += damage;
                        totalTeam1Health -= damage;
                    }
                    // Відображення події атаки у консолі
                    System.out.println(attacker.getName() + " атакує " + target.getName() + " наносячи " + damage + " damage.");
                }
            }
        }

        // Обчислення та відображення загального урону та здоров'я команд після раунду
        for (T droid : team1) {
            if (droid.getHealth() > 0) {
                droid.setHealth(droid.getHealth() - totalTeam1Damage);
                totalTeam1Health += droid.getHealth();
            }
        }
        for (T droid : team2) {
            if (droid.getHealth() > 0) {
                droid.setHealth(droid.getHealth() - totalTeam2Damage);
                totalTeam2Health += droid.getHealth();
            }
        }

        // Відображення у консолі загального урону та здоров'я обох команд
        System.out.println("Загальний урон команди 1: " + totalTeam1Damage);
        System.out.println("Загальний урон команди 2: " + totalTeam1Health);
        System.out.println("Загальний рівень здоров'я команди 1: " + totalTeam2Damage);
        System.out.println("Загальний рівень здоров'я команди 2: " + totalTeam2Health);
    }

    // Перевірка наявності живих дроїдів у командах
    public static <T extends Droid> boolean areTeamsAlive(T[] team1, T[] team2) {
        boolean anyTeam1Alive = false; // Прапорець для першої команди
        boolean anyTeam2Alive = false; // Прапорець для другої команди

        for (int i = 0; i < team1.length; i++) {
            if (team1[i].getHealth() > 0) {
                anyTeam1Alive = true; // Встановлюється, якщо є живі дроїди у першій команді
            }
            if (team2[i].getHealth() > 0) {
                anyTeam2Alive = true; // Встановлюється, якщо є живі дроїди у другій команді
            }
        }

        // Повертається, якщо хоча б у одній команді є живі дроїди
        return anyTeam1Alive && anyTeam2Alive;
    }
}

