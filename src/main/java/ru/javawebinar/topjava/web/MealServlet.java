package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private Meal meal = new Meal(LocalDateTime.now(), "", 1);
    private List<MealWithExceed> data = MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doGet");
        String action = request.getParameter("action");

        switch (action == null ? "info" : action) {
            case "update":
                request.setAttribute("data", meal);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
            case "info":
                default:

                    request.setAttribute("data", data);
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals doPost");
        req.setCharacterEncoding("UTF-8");
        log.debug("1");
        String action = req.getParameter("action");
        log.debug("2");
        if ("submit".equals(action)) {
            log.debug("3");

            meal = new Meal(LocalDateTime.now(), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
            log.debug("3 meal {}", meal);
            data.addAll(MealsUtil.getFilteredWithExceeded(Collections.singletonList(meal), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));

        }
        req.setAttribute("data", meal);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
