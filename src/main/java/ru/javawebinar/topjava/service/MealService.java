package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    Meal save(int userId, Meal meal);

    void delete(int userId, int mealId);

    Meal get(int userId, int mealId);

    List<Meal> getAll(int userId);

}