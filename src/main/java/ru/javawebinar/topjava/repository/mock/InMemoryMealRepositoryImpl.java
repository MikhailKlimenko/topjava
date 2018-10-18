package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(int userId,Meal meal) {
        log.debug("save userId {} meal {}", userId, meal);
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
        log.debug("delete userId {}  mealId {}", userId, mealId);
        if (Objects.nonNull(repository.get(userId).get(mealId))) {
            repository.get(userId).remove(mealId);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int mealId) {
        log.debug("get userId {}  mealId {}");
        return repository.get(userId).get(mealId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.debug("getAll userId {}");
        return repository.get(
                userId).values()
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate())).collect(Collectors.toList());
    }

}

