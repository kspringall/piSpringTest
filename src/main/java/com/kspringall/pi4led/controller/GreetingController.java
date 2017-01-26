package com.kspringall.pi4led.controller;

import com.kspringall.pi4led.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class.getName());

    @RequestMapping("/greeting")
    public Greeting greetingWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info("The /greeting endpoint was called");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
