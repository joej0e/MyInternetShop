package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId).orElseThrow();
        Bucket bucket = bucketService
                .get(userService.get(userId).get()
                        .getBucketId()).orElseThrow();
        req.setAttribute("user", user);
        req.setAttribute("bucket", bucket);
        double amount = bucket.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
        req.setAttribute("amount", amount);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}

