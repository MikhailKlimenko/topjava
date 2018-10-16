package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    boolean update(Meal meal);

    boolean create(Meal meal);

    Meal read(int id);

    List<Meal> readAll();

    boolean delete(int id);

}
