package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;
@Controller
public class MealRestController {

    private final MealService service;

//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }


    public Meal save(Meal meal) {
        return service.save(SecurityUtil.authUserId(), meal);
    }

    public void delete(int mealId) {
        service.delete(SecurityUtil.authUserId(), mealId);
    }

    public Meal get(int mealId) {
        return service.get(SecurityUtil.authUserId(), mealId);
    }

    public List<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }




}