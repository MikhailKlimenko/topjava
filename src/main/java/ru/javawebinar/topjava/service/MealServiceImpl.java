package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int mealId) {
        if (!repository.delete(userId, mealId)) {
            throw new NotFoundException("Meal not found!");
        }
    }

    @Override
    public Meal get(int userId, int mealId) {
        Meal meal;
        if ((meal = repository.get(userId, mealId)) != null) {
            return meal;
        }
        throw new NotFoundException("Meal not found!");
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meal;
        if (!(meal = repository.getAll(userId)).isEmpty()) {
            return meal;
        }
        throw new NotFoundException("Meal's not found!");
    }
}