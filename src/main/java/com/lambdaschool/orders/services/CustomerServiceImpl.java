package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerrepo")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerrepo;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long custcode) {
        Customer rtnCust = customerrepo.findById(custcode)
                .orElseThrow(()-> new EntityNotFoundException("Customer " + custcode + " Not Found"));
        return rtnCust;

    }

    @Override
    public List<Customer> findCustomersLikeName(String subname) {
        List<Customer> rtnList = customerrepo.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }


    @Transactional
    @Override
    public Customer save(Customer customer) {
        return null;
    }


}
