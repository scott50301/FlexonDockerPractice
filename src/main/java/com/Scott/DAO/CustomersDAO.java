package com.Scott.DAO;

import java.util.List;
import com.Scott.Classes.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersDAO extends CrudRepository<Customers, Long> {
    List<Customers> findAll();
}
