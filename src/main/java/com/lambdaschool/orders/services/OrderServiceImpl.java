package com.lambdaschool.orders.services;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Transactional
@Service(value = "orderservice")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderrepo;

    @Transactional
    @Override
    public Order save(Order order){
        return orderrepo.save(order);
    }

    @Override
    public Order findOrderById(long ordnum) {
        Order rtnOrder = orderrepo.findById(ordnum)
                .orElseThrow(()-> new EntityNotFoundException("Order" + ordnum + " Not Found"));
        return rtnOrder;
    }
}
