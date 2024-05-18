package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.Customer;
import org.eduardomaravill.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;


    //Get customer
    @Override
    public Customer getCustomer(Long id) {
        return repository.findById(id).orElse(null);
    }

    //Get customers
    @Override
    public List<Customer> getAllCustomer(){
        List<Customer> customers = new ArrayList<>();
        for(Customer customer : repository.findAll()){
           customers.add(customer);
        }
        return customers;
    }

    //Add customer
    @Override
    public void addCustomer(Customer customer){
         repository.save(customer);
    }

    //Delete customer
    @Override
    public void deleteCustomer(Long id){
        repository.deleteById(id);
    }

    //Update customer
    @Override
    public void updateCustomer(Long id,Customer customer){
        customer.setId(id);
        repository.save(customer);
    }

    //Search customer
    @Override
    public List<Customer> searchCustomer(String email,String address){
        return repository.findByEmailOrAddress(email, address);
    }
}
