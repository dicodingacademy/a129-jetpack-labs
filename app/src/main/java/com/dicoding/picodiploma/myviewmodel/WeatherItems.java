package com.dicoding.picodiploma.myviewmodel;

import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by Emeth on 10/31/2016.
 */

public class WeatherItems {

    private int id;
    private String name;
    private String currentWeather;
    private String description;
    private String temperature;

    WeatherItems(JSONObject object) {

        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String currentWeather = object.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = object.getJSONArray("weather").getJSONObject(0).getString("description");
            double tempInKelvin = object.getJSONObject("main").getDouble("temp");

            double tempInCelsius = tempInKelvin - 273;
            String temperature = new DecimalFormat("##.##").format(tempInCelsius);
            this.id = id;
            this.name = name;
            this.currentWeather = currentWeather;
            this.description = description;
            this.temperature = temperature;

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
