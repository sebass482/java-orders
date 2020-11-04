package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

//    http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{ordnum}", produces = {"application/json"})
    public ResponseEntity<?> listOrderById(@PathVariable long ordnum)
    {
        Order rtnOrder = orderService.findOrderById(ordnum);
        return new ResponseEntity<>(rtnOrder, HttpStatus.OK);
    }



}
