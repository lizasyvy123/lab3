package Droid; // Оголошення пакету, до якого належить клас

public class ShieldDroid extends Droid { // Оголошення класу ShieldDroid, який успадковує клас Droid

    private boolean shield = false; // Приватне поле (змінна) для зберігання інформації про стан щита

    // Конструктор класу, який викликає конструктор класу-батька (Droid)
    public ShieldDroid() {
        super();
    }

    // Метод для отримання інформації про стан щита
    public boolean getShield(boolean shield) {
        return shield; // Повертає поточний стан щита
    }

    // Метод для встановлення стану щита
    public void setShield(boolean shield) {
        // Перевірка умови: якщо здоров'я дроїда менше 500, то вмикається щит
        if (this.health < 500) {
            this.shield = true; // Встановлюється стан щита на true
        }
    }
}
