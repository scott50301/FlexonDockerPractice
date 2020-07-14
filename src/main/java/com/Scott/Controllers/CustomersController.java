package com.Scott.Controllers;

import com.Scott.DAO.CustomersDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.Scott.Classes.Customers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        List<Customers> res = dao.getData("SELECT * FROM `contacts_tbl`");
        return res;
    }

    @PostMapping(path="/addNewCustomer")
    public String addNewCustomer(@RequestBody String firstName, String lastName, BigDecimal phone, String email) throws SQLException, ClassNotFoundException, IOException {
        CustomersDAO dao = new CustomersDAO();
        dao.insertData(firstName, lastName, phone, email);
        return "Successfully wrote to the file.";
    }


    @PostMapping(path="/updateCustomer")
    public String updateCustomer(@RequestBody int id, String firstName, String lastName, BigDecimal phone, String email) throws SQLException, ClassNotFoundException {
        CustomersDAO dao = new CustomersDAO();
        dao.updateData(id, firstName, lastName, phone, email);
        return  "The contact info has been updated";
    }

    @PostMapping(path="/deleteCustomer")
    public String deleteCustomer(@RequestBody int id) throws SQLException, ClassNotFoundException {
        CustomersDAO dao = new CustomersDAO();
        dao.deleteById(id);
        return  "The contact info has been deleted";
    }

//    public static void main(String[] args)  {
//        // TODO Auto-generated method stub
//
//
//        Connection conn = null;
//
//        try {
//            boolean exit = false;
//            int choice;
//            Scanner scanner = new Scanner(System.in);
//            CustomersDAO dao = new CustomersDAO();
//
//            String sql = "";
//            while (!exit){
//                System.out.println("What do you want to do today? (1)Add (2)update (3)delete (4)export information (5)exit (please enter the number)");
//                if (!scanner.hasNextInt()){
//                    System.out.println("Please enter valid operation code");
//                    scanner = new Scanner(System.in);
//                    continue;
//                }
//                choice = Integer.parseInt(scanner.nextLine());
//                ResultSet rs;
//                String firstName = null;
//                String lastName = null;
//                BigDecimal phone = null;
//                String email = null;
//                List<Customers> result = new ArrayList<Customers>();
//                switch (choice){
//                    case 1:
//                        System.out.println("Please enter the first name");
//                        firstName  = scanner.nextLine();
//                        System.out.println("Please enter the last name");
//                        lastName  = scanner.nextLine();
//                        System.out.println("Please enter the phone number");
//                        phone =  new BigDecimal(scanner.nextLine());
//                        System.out.println("Please enter the email");
//                        email = scanner.nextLine();
//
//                        dao.insertData(firstName,lastName,phone,email);
//
//                        break;
//                    case 2:
//                        sql = "SELECT * FROM `contacts_tbl`";
//                        result = dao.getData(sql);
//                        showInfo(result);
//                        System.out.println("Which data do you want to update? (please enter the contactID)");
//                        if (!scanner.hasNextInt()){
//                            System.out.println("Please enter valid contactID");
//                            scanner = new Scanner(System.in);
//                            continue;
//                        }
//                        int updateId  = Integer.parseInt(scanner.nextLine());
//                        sql = "SELECT * FROM `contacts_tbl` WHERE  `contactID` ="+updateId;
//                        result = dao.getData(sql);
//
//                        firstName = result.get(0).getFirstNema();
//                        lastName = result.get(0).getLastNema();
//                        phone = result.get(0).getPhone();
//                        email = result.get(0).getEmail();
//
//                        System.out.println("Current firstName ："+firstName+" (fill the previous value if you don't want to change this)");
//                        firstName = scanner.nextLine();
//
//                        System.out.println("Current lastName ："+lastName+" (fill the previous value if you don't want to change this)");
//                        lastName = scanner.nextLine();
//
//
//                        System.out.println("Current phone number ："+phone+" (fill the previous value if you don't want to change this)");
//                        phone = new BigDecimal(scanner.nextLine());
//
//
//                        System.out.println("Current email ："+email+" (fill the previous value if you don't want to change this)");
//                        email = scanner.nextLine();
//
//                        dao.updateData(updateId, firstName, lastName, phone, email);
//
//                        break;
//                    case 3:
//                        sql = "SELECT * FROM `contacts_tbl`";
//                        result = dao.getData(sql);
//                        showInfo(result);
//                        System.out.println("Which data do you want to delete? (please enter the contactID)");
//                        if (!scanner.hasNextInt()){
//                            System.out.println("Please enter valid contactID");
//                            scanner = new Scanner(System.in);
//                            continue;
//                        }
//                        int deleteId  = Integer.parseInt(scanner.nextLine());
//                        dao.deleteById(deleteId);
//                        break;
//                    case 4:
//                        dao.exportDate();
//                        break;
//                    case 5:
//                        exit = true;
//                        break;
//                }
//            }
//
//            System.out.println("Created table in given database...");
//
//        } catch (SQLException  e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void showInfo(List<Customers> result) {
//        for (Customers res : result){
//            System.out.println(res.getId() +"\t"+ res.getFirstNema()+"\t" + res.getLastNema()+"\t"+res.getPhone()+"\t"+res.getEmail());
//        }
//
//
//    }
}
