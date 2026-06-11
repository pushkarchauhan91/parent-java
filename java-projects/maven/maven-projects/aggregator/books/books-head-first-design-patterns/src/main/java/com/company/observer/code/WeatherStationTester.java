package com.company.observer.code;

public class WeatherStationTester {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30);
        weatherData.setMeasurements(82, 70, 25);
        weatherData.setMeasurements(78, 90, 35);

    }
}
