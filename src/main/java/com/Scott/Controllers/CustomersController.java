package com.Scott.Controllers;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import com.Scott.Classes.Customers;
import com.Scott.Srvices.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomersController {
    @Autowired
    CustomersService customersService;
//    @GetMapping(path="/exportAllCustomers")
//    public String exportAllCustomers() throws SQLException, ClassNotFoundException, IOException {
//
//        dao.exportDate();
//
//        return "Successfully wrote to the file.";
//    }

    @GetMapping(path="/getAllCustomers")
    public ResponseEntity<List<Customers>> getAllCustomers() throws SQLException, ClassNotFoundException, IOException {
        return ResponseEntity.ok(customersService.getData());
    }



    @PostMapping(path="/addCustomer")
    public ResponseEntity<String> addCustomer(String firstName,
                                 String lastName,
                                 String phone,
                                 String email) throws SQLException, ClassNotFoundException, IOException {

        return customersService.addCustomer(firstName, lastName, phone, email);
    }


    @PutMapping(path="/updateCustomer/{id}/{firstName}/{lastName}/{phone}/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable long id,
                                 @PathVariable String firstName,
                                 @PathVariable String lastName,
                                 @PathVariable String phone,
                                 @PathVariable String email) throws SQLException, ClassNotFoundException {


        return  customersService.updateCustomer(id, firstName, lastName, phone, email);
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) throws SQLException, ClassNotFoundException {

        return customersService.deleteCustomerById(id);
    }

}
