package com.kspringall.pi4led.controller;

import com.kspringall.pi4led.Greeting;
import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LedController {

    private static GpioPinDigitalOutput pin;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger LOGGER = LoggerFactory.getLogger(LedController.class.getName());

    @RequestMapping("/")
    public String greeting() {
        return "Hello World";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/g")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tester")
    public String greetingTest() {
        return "Hello Tester!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/kimball")
    public String greetingKimball() {
        return "Hello Kimball!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/n")
    public Greeting greetingName(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info("The /n endpoint was called");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/greeting/{personName}")
    public Greeting greetingPost(@PathVariable("personName") String personName) {
        return new Greeting(counter.incrementAndGet(), String.format(template, personName));
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
