package com.hdscode.belajarspringaop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testHello(){
        Assertions.assertEquals("hello world",helloService.hello("world"));
    }

    @Test
    void testBye(){
        Assertions.assertEquals("bye world",helloService.bye("world"));
    }

    @Test
    void testTest(){
        helloService.test();
    }

}