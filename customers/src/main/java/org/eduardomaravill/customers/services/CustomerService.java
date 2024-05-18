package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.Customer;

import java.util.List;


public interface CustomerService {

    Customer getCustomer(Long id);
    List<Customer> getAllCustomer();
    void addCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);
    List<Customer> searchCustomer(String email, String address);
}
