package com.lambdaschool.orders.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    //  http://localhost:2019/customers/orders
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> rtnList = customerService.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/customer/7
    //    http://localhost:2019/customers/customer/77
    @GetMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> listCustomerById(@PathVariable long custcode) {
        Customer rtnCust = customerService.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{subname}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersLikeName(@PathVariable String subname) {
        List<Customer> rtnList = customerService.findCustomersLikeName(subname);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer (@Valid
                                             @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode")
                .buildAndExpand((newCustomer.getCustcode()))
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.OK);
    }

    //  http:localhost:2019/customers/customer/{custcode}
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> updateCustomer(@PathVariable long custcode,
                                            @Valid @RequestBody Customer updateCustomer)
    {
        updateCustomer.setCustcode(custcode);
        updateCustomer = customerService.save(updateCustomer);

        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }
}
