package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl orderService = new OrderServiceImpl();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    @Inject
    private static OrderDao orderDao;
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Order add(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        List<Item> itemsCopy = new ArrayList<Item>(items);
        Order order = new Order(itemsCopy, user.getId());
        orderDao.create(order);
        orderDao.getOrdersByUserId(user.getId()).add(order);
        Bucket bucket = bucketDao.get(user.getId()).orElseThrow();
        bucket.getItems().clear();
        return order;
    }

    @Override
    public List<Order> getAllOrdersForUser(User user) {
        return orderDao.getOrdersByUserId(user.getId());
    }
}

