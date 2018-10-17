package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.security.Security;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> save(SecurityUtil.authUserId(), meal));
    }

    @Override
    public Meal save(int userId,Meal meal) {
        Objects.requireNonNull(meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer, Meal> mealmap = repository.get(userId) == null ? new ConcurrentHashMap<>() : repository.get(userId);
        mealmap.remove(meal.getId());
        mealmap.put(meal.getId(), meal);
        repository.put(userId, mealmap);
        return meal;
    }

    @Override
    public boolean delete(int userId, int mealId) {
        if (Objects.nonNull(repository.get(userId).get(mealId))) {
            repository.get(userId).remove(mealId);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.get(
                userId).values()
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate())).collect(Collectors.toList());
    }

}

