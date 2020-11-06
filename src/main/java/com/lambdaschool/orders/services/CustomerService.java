package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();
    List<Customer> findCustomersLikeName(String subname);
    Customer findCustomerById(long custcode);
    Customer save(Customer customer);
    void delete(long custcode);

    void deleteAllCustomers();
}
