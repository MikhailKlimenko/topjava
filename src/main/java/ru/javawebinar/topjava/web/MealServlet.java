package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MealService mealService = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("doGet");
        String action = request.getParameter("action");
        try {
            switch (action == null ? "info" : action) {
                case "edit":
                    edit(request, response);
                    break;
                case "create":
                    create(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "info":
                default:
                    list(request, response);
                    break;
            }
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("list");
        List<MealWithExceed> list = MealsUtil.getFilteredWithExceeded(mealService.readAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        list.sort(Comparator.comparing(MealWithExceed::getDateTime));
        request.setAttribute("data", list);
       request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("create");
        request.setAttribute("data", new Meal(LocalDateTime.now(), "", 0));
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("edit");
        request.setAttribute("data", mealService.read(Integer.parseInt(request.getParameter("dat.id"))));
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        log.debug("delete");
        mealService.delete(Integer.parseInt(request.getParameter("dat.id")));
        request.setAttribute("data", MealsUtil.getFilteredWithExceeded(mealService.readAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log.debug("update");
        Meal meal;
        if (!request.getParameter("id").equals("")) {
            System.out.println();
            meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            meal.setId(Integer.parseInt(request.getParameter("id")));
            mealService.update(meal);
        } else {
            meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            mealService.create(meal);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        log.debug("doPost");
        String action = req.getParameter("action");
        switch (action) {
            case "update":
                update(req, resp);
        }
        resp.sendRedirect("/topjava/meals");
    }
}
