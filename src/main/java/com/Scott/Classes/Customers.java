package com.Scott.Classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    public Customers(){

    }

    public Customers( String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public long getId(){
        return id;
    }

    public void setFirstNema(String firstName){
        this.firstName = firstName;
    }

    public String getFirstNema(){
        return firstName;
    }

    public void setLastNema(String lastName){
        this.lastName = lastName;
    }

    public String getLastNema(){
        return lastName;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
