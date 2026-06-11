package com.company.observer.code;

public class CurrentConditionsDisplay implements DisplayElement, Observer {

    private double temperature;
    private double humidity;
    private double pressure;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + " " + humidity + " " + pressure);
    }


}
