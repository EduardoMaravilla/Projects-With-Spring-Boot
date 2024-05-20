package org.eduardomaravill.customers.controllers;

import org.eduardomaravill.customers.entities.Customer;
import org.eduardomaravill.customers.services.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping("/customer/{id}")//Get customer
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping("/customers")//Get customers
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer")//Add customer
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")//Delete customer
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("/customer/{id}")//Update customer
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
    }

    @GetMapping("/customers/search")//Search customer
    public List<Customer> searchCustomers(@RequestParam(name = "email", required = false) String email
            , @RequestParam(name = "address", required = false) String address) {
        return customerService.searchCustomers(email, address);
    }

}
