package com.company.observer.code;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {

    private final List<Observer> observers;
    private double temperature;
    private double humidity;
    private double pressure;


    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(double temperature, double humidity, double pressure) {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers(temperature, humidity, pressure);
    }

    public void setMeasurements(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
