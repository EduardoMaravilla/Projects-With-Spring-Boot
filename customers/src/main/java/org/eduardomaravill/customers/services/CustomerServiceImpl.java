package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.Customer;
import org.eduardomaravill.customers.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;


    //Get customer
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    //Get customers
    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        for(Customer customer : customerRepository.findAll()){
           customers.add(customer);
        }
        return customers;
    }

    //Add customer
    @Override
    public void addCustomer(Customer customer){
         customerRepository.save(customer);
    }

    //Delete customer
    @Override
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    //Update customer
    @Override
    public void updateCustomer(Long id,Customer customer){
        customer.setId(id);
        customerRepository.save(customer);
    }

    //Search customer
    @Override
    public List<Customer> searchCustomers(String email, String address){
        return customerRepository.findByEmailOrAddress(email, address);
    }
}
