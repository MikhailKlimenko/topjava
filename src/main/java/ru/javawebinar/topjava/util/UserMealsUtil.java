package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class UserMealsUtil {
    public static List<UserMeal> mealList = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 19, 0), "Ужин", 0)

    );


    public static void main(String[] args) {

        List<UserMealWithExceed> m1 = getFilteredWithExceededByStream(mealList, LocalTime.of(7, 0), LocalTime.of(23, 0), 2000);
        List<UserMealWithExceed> m2 = getFilteredWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(23, 0), 2000);
        m1.sort(Comparator.comparing(UserMealWithExceed::getLocalDate));
        m2.sort(Comparator.comparing(UserMealWithExceed::getDateTime));
        System.out.println(m1);
        System.out.println(m2);

    }

    public static List<UserMealWithExceed>  getFilteredWithExceededByStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> maps = mealList.stream()
                .collect(groupingBy(UserMeal::getLocalDate, summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), maps.get(meal.getLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();
        Map<LocalDate, Integer> maps = new HashMap<>();
        for (UserMeal meal : mealList) {
            maps.merge(meal.getLocalDate(), meal.getCalories(), Integer::sum);
        }
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                mealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), maps.get(meal.getLocalDate()) > caloriesPerDay));
            }
        }
        return mealWithExceeds;
    }

    private static Integer apply(Integer a, Integer b) {
        return a + b;
    }
}
