package com.example.hibernate.web;

import com.example.hibernate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/echo")
    public Object test(){
        return "success";
    }

    @RequestMapping("/saveCustomer")
    public Object saveCustomer(){
        customerService.saveCustomer();
        return "success";
    }
}
