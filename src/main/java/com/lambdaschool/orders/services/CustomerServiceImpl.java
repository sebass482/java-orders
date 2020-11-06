package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.CustomerRepository;
import com.lambdaschool.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerrepo")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerrepo;

    @Autowired
    OrderRepository orderrepo;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long custcode) {
        Customer rtnCust = customerrepo.findById(custcode)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + custcode + " Not Found"));
        return rtnCust;

    }

    @Override
    public List<Customer> findCustomersLikeName(String subname) {
        List<Customer> rtnList = customerrepo.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }


//    @Transactional
//    @Override
//    public Customer save(Customer customer) {
//        return null;
//    }

    @Transactional
    @Override
    public void delete(long custcode) {
        if (customerrepo.findById(custcode).isPresent()) {
            customerrepo.deleteById(custcode);
        } else {
            throw new EntityNotFoundException("Customer " + custcode + " Not Found");
        }
    }

    // Valid Data
    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();
        if (customer.getCustcode() != 0) {
            findCustomerById(customer.getCustcode());
            newCustomer.setCustcode(customer.getCustcode());
        }
        // single value fields
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        // collections
        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = orderrepo.findById(o.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + o.getOrdnum() + " Not Found"));
            newCustomer.getOrders().add(newOrder);
        }
        return customerrepo.save(newCustomer);
    }
    @Transactional
    @Override
    public void deleteAllCustomers() {
        customerrepo.deleteAll();
    }
}
//        @Override
//    public Customer deleteCustomerByCustCode(long custcode) {
//        Customer rtnOrder = customerrepo.findById(custcode)
//                .orElseThrow(()-> new EntityNotFoundException("Order" + custcode + " Not Found"));
//        return rtnOrder;
//    }

