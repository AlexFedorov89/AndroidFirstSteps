package com.fedorov.alex.whattheweatheristoday;

import java.util.ArrayList;
import java.util.List;

// Обработчик подписок
public class Publisher {

    private List<Observer> observers;   // Все обозреватели

    Publisher() {
        observers = new ArrayList<>();
    }

    // Подписать
    void subscribe(Observer observer) {
        observers.add(observer);
    }

    // Отписать
    void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    // Разослать событие
    void sendCityToClients(String text) {
        for (Observer observer : observers) {
            observer.updateCity(text);
        }
    }
}