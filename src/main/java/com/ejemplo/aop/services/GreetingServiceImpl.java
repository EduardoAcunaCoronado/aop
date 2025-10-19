package com.ejemplo.aop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String person, String phrase) {
        String greeting = person + " " + phrase;
        System.out.println(greeting);
        return greeting;
    }

}
