package com.kspringall.pi4led.controller;

import com.kspringall.pi4led.Greeting;
import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LedController {

    private static GpioPinDigitalOutput pin;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public String greeting() {

        return "ROOT Call";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/g")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tester")
    public String greetingTest() {
        return "Hello Tester!";
    }

    @RequestMapping("/light")
    public String light() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        }

        pin.toggle();

        return "OK";
    }

}
