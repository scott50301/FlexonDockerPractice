package com.Scott.Srvices;

import com.Scott.Classes.Customers;
import com.Scott.DAO.CustomersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomersService {
    @Autowired
    private CustomersDAO customersDAO;

    public List<Customers> getData(){

        return customersDAO.findAll();
    }

    public ResponseEntity updateCustomer(long id, String firstName, String lastName, String phone, String email){
        Customers customer = customersDAO.findById( id).get();
        customer.setFirstNema(firstName);
        customer.setLastNema(lastName);
        customer.setPhone(phone);
        customer.setEmail(email);
        customersDAO.save(customer);
        return ResponseEntity.ok("The customer info has been updated");
    }

    public ResponseEntity addCustomer(String firstName, String lastName, String phone, String email) {
        Customers customer = new Customers(firstName, lastName, phone, email);
        customersDAO.save(customer);
        return ResponseEntity.ok("Successfully add the customer.");
    }

    public ResponseEntity deleteCustomerById(long id) {
        customersDAO.deleteById( id);
        return ResponseEntity.ok("Customer "+id+" has been deleted.");
    }
}
