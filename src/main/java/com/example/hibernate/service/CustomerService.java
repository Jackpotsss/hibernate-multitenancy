package com.example.hibernate.service;

import com.example.hibernate.dao.CustomerRepository;
import com.example.hibernate.entity.Customer;
import com.example.hibernate.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void saveCustomer(){
        String currentTenantId = TenantContext.getCurrentTenantId();
        Customer customer = new Customer(currentTenantId);
        customerRepository.save(customer);
    }


}
