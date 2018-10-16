package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;

import java.util.List;

public class MealServiceImpl implements MealService {
    private static MealRepository mealRepository = new MealRepositoryImpl();
    @Override
    public void update(Meal meal) {
        mealRepository.update(meal);
    }

    @Override
    public void create(Meal meal) {
        mealRepository.create(meal);
    }

    @Override
    public Meal read(int id) {
        return mealRepository.read(id);
    }

    @Override
    public void delete(int id) {
        mealRepository.delete(id);
    }

    public List<Meal> readAll() {
        return mealRepository.readAll();
    }
}
