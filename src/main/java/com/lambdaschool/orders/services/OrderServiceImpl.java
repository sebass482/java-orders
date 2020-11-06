package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.models.Payment;
import com.lambdaschool.orders.repositories.CustomerRepository;
import com.lambdaschool.orders.repositories.OrderRepository;
import com.lambdaschool.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderservice")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderrepo;
    @Autowired
    PaymentRepository paymentrepo;
    @Autowired
    CustomerRepository customerrepo;

    @Override
    public Order findOrderById(long ordnum) {
        Order rtnOrder = orderrepo.findById(ordnum)
                .orElseThrow(()-> new EntityNotFoundException("Order" + ordnum + " Not Found"));
        return rtnOrder;
    }
    @Transactional
    @Override
    public void delete(long ordnum) {
        if (orderrepo.findById(ordnum).isPresent()) {
            orderrepo.deleteById(ordnum);
        } else {
            throw new EntityNotFoundException("Order " + ordnum + " Not Found");
        }
    }
    @Transactional
    @Override
    public void deleteAllOrders() {
        orderrepo.deleteAll();
    }
    // Valid Data
    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();
        if (order.getOrdnum() != 0) {
            findOrderById(order.getOrdnum());
            newOrder.setOrdnum(order.getOrdnum());
        }
        // single value fields
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setPayments(order.getPayments());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setCustomer(customerrepo.findById(order.getCustomer().getCustcode())
                                .orElseThrow(() -> new EntityNotFoundException("Customer" + order.getCustomer().getCustcode() + " Not Found")));
        // collections
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments())
        {
            Payment newPayment = paymentrepo.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.getPayments().add(newPayment);
        }
        return orderrepo.save(newOrder);
    }
//    @Override
//    public Order update(Order order, long ordnum) {
//        Order updateOrder = findOrderById(ordnum);
//
//        // single value fields
//        if(order.hasvalueforordamount){
//            updateOrder.setOrdamount(order.getOrdamount());
//        }
//        if(order.hasvalueforadvanceamount){
//            updateOrder.setAdvanceamount(order.getAdvanceamount());
//        }
//        if(order.getOrderdescription()!=null){
//            updateOrder.setOrderdescription(order.getOrderdescription());
//        }
////        if(order.getCustomer() =! null ){
////            updateOrder.setCustomer(order.getOrderdescription());
////        }
//        if(order.getPayments().size()>0){
//            updateOrder.getPayments().clear();
//            for (Payment p : order.getPayments())
//            {
//                Payment newPayment = paymentrepo.findById(p.getPaymentid())
//                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
//                updateOrder.getPayments().add(newPayment);
//            }
//        }
//
//        // collections
//        updateOrder.getPayments().clear();
//        for (Payment p : order.getPayments())
//        {
//            Payment newPayment = paymentrepo.findById(p.getPaymentid())
//                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
//            updateOrder.getPayments().add(newPayment);
//        }
//        return orderrepo.save(updateOrder);
//    }
}
