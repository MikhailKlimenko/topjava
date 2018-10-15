package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMeal {

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public UserMeal() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getLocalDate(){
        return this.dateTime.toLocalDate();
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
