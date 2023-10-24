package com.hdscode.belajarspringaop.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class HelloService {

    public String hello(String name){
        return "hello " + name;
    }

    public String bye(String name){
        return "bye " + name;
    }

    public void test(){
        log.info("test service");
    }

}
