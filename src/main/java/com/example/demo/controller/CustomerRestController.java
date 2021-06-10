package com.example.demo.controller;

import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("customer id = " + id + " doesn't exist"));
    }

    // you also can update customer if you add existing id to json
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("customer id = " + id + " doesn't exist"));

        updatedCustomer.setNickname(customer.getNickname());
        updatedCustomer.setCreatedDate(customer.getCreatedDate());

        return customerRepository.save(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomerById(@PathVariable long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("customer id = " + id + " doesn't exist"));

        customerRepository.delete(customer);
        // another way
        // customerRepository.deleteById(id);

        return "customer with id = " + id + " deleted";
    }
}
