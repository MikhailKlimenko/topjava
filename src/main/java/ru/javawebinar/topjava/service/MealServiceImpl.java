package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repositoryMeal) {
        this.repository = repositoryMeal;
    }

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) {
        if (!repository.delete(userId, id)) {
            throw new NotFoundException("Meal not found!");
        }
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal;
        if ((meal = repository.get(userId, id)) != null) {
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