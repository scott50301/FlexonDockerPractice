package com.Scott.Controllers;

import com.Scott.DAO.CustomersDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import com.Scott.Classes.Customers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomersController {

    @GetMapping(path="/exportAllCustomers")
    public String exportAllCustomers() throws SQLException, ClassNotFoundException, IOException {
        CustomersDAO dao = new CustomersDAO();
        dao.exportDate();

        return "Successfully wrote to the file.";
    }

    @GetMapping(path="/getAllCustomers")
    public List<Customers> getAllCustomers() throws SQLException, ClassNotFoundException, IOException {
        CustomersDAO dao = new CustomersDAO();
        return dao.getData();
    }

    @PostMapping(path="/addNewCustomer")
    public String addNewCustomer(String firstName,
                                 String lastName,
                                 BigDecimal phone,
                                 String email) throws SQLException, ClassNotFoundException, IOException {
        CustomersDAO dao = new CustomersDAO();
        dao.insertData(firstName, lastName, phone, email);
        return "Successfully add the customer.";
    }


    @PutMapping(path="/updateCustomer/{id}/{firstName}/{lastName}/{phone}/{email}")
    public String updateCustomer(@PathVariable int id,
                                 @PathVariable String firstName,
                                 @PathVariable String lastName,
                                 @PathVariable BigDecimal phone,
                                 @PathVariable String email) throws SQLException, ClassNotFoundException {
        CustomersDAO dao = new CustomersDAO();
        dao.updateData(id, firstName, lastName, phone, email);
        return  "The contact info has been updated";
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) throws SQLException, ClassNotFoundException {
        CustomersDAO dao = new CustomersDAO();
        dao.deleteById(id);

        return new ResponseEntity<>("Customer "+id+" has been deleted.", HttpStatus.OK);
    }

}
