package com.lambdaschool.orders.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

//    http://localhost:2019/customers/orders
    @Autowired
    CustomerService customerService;
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customer> rtnList = customerService.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //

    @GetMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> listCustomerById(@PathVariable long custcode)
    {
        Customer rtnCust = customerService.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);
    }



    @GetMapping(value = "/namelike/{subname}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersLikeName(@PathVariable String subname)
    {
        List<Customer> rtnList = customerService.findCustomersLikeName(subname);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

















//    http://localhost:2019/customers/customer/7
//    http://localhost:2019/customers/customer/77
//    http://localhost:2019/customers/namelike/mes





}
