package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.Customer;

import java.util.List;


public interface ICustomerService {

    Customer getCustomer(Long id);
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);
    List<Customer> searchCustomers(String email, String address);
}
