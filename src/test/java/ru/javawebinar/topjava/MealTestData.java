package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
public class MealTestData {
    public static final Meal MEAL = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);

    public static void assertMatch(Meal actual, Meal expected) {
        System.out.println("actual   " + actual);
        System.out.println("expected   " + expected);

        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        System.out.println("actual   " + actual);
        System.out.println("expected   " + Arrays.toString(expected));

        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        System.out.println("actual   " + actual);
        System.out.println("expected   " + expected);

        assertThat(actual).isEqualTo(expected);
    }
}
