package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName("Bob");
        user.setSurname("Martin");
        user.setLogin("bob");
        user.setPassword("1");
        user.setId(0L);
        userService.create(user);
        User admin = new User();
        admin.setName("Super");
        admin.setSurname("Mario");
        admin.setLogin("admin");
        admin.setPassword("1");
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/servlet/index");
    }
}

