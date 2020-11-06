package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

public interface OrderService {
    Order save(Order order);
    Order findOrderById(long ordnum);
    void delete(long ordnum);
    void deleteAllOrders();
}
