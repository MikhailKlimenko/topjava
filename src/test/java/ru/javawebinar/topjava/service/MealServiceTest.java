package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.MEAL;
import static ru.javawebinar.topjava.MealTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;


    @Test
    public void get() {
        Meal create = mealService.create(MEAL, 100000);
        create.setId(create.getId());
        assertMatch(mealService.get(create.getId(), 100000), create);
    }

    @Test(expected = NotFoundException.class)
    public void getAlienMeal() {
        Meal create = mealService.create(MEAL, 100001);
        create.setId(create.getId());
        assertMatch(mealService.get(create.getId(), 100000), create);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        Meal create = mealService.create(MEAL, 100000);
        create.setId(create.getId());
        mealService.delete(create.getId(), 100000);
        mealService.get(create.getId(), 100000);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlienMeal() {
        Meal create = mealService.create(MEAL, 100001);
        create.setId(create.getId());
        mealService.delete(create.getId(), 100000);
        mealService.get(create.getId(), 100001);
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {

    }

    @Test
    public void getAll() {
        Meal meal1 = mealService.create(MEAL, 100002);
        assertMatch(mealService.getAll(100002), Collections.singletonList(meal1));
    }

    @Test
    public void update() {
        Meal create = mealService.create(MEAL, 100000);
        create.setId(create.getId());
        create.setCalories(130);
        mealService.update(create, 100000);
        assertMatch(mealService.get(create.getId(), 100000), create);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlienMeal() {
        Meal create = mealService.create(MEAL, 100000);
        create.setId(create.getId());
        create.setCalories(130);
        mealService.update(create, 100001);
    }


    @Test
    public void create() {
        Meal create = mealService.create(MEAL, 100000);
        create.setId(create.getId());
        assertMatch(mealService.get(create.getId(), 100000), create);
    }

}