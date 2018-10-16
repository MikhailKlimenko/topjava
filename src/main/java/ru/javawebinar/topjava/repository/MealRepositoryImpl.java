package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepositoryImpl implements MealRepository {

    private static final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    static {
        Meal meal1 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        meal1.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal1.getId(), meal1);

        Meal meal2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        meal2.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal2.getId(), meal2);

        Meal meal3 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        meal3.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal3.getId(), meal3);

        Meal meal4 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        meal4.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal4.getId(), meal4);

        Meal meal5 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        meal5.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal5.getId(), meal5);

        Meal meal6 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        meal6.setId(atomicInteger.incrementAndGet());
        mealMap.put(meal6.getId(), meal6);

    }
    @Override
    public boolean update(Meal meal) {
        if (!mealMap.isEmpty() && mealMap.containsKey(meal.getId())) {
            mealMap.replace(meal.getId(), mealMap.get(meal.getId()), meal);
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Meal meal) {
            meal.setId(atomicInteger.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return true;
    }

    @Override
    public Meal read(int id) {
        if (!mealMap.isEmpty() && mealMap.containsKey(id)) {
            return mealMap.get(id);
        }
        return null;
    }

    @Override
    public final List<Meal> readAll() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public boolean delete(int id) {
        if (!mealMap.isEmpty() && mealMap.containsKey(id)) {
            mealMap.remove(id);
            return true;
        }
        return false;
    }
}
