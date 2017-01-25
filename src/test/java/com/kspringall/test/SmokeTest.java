package com.kspringall.test;

/**
 * Created by kspringall on 1/24/2017.
 */

import com.kspringall.pi4led.Application;
import com.kspringall.pi4led.controller.GreetingController;
import com.kspringall.pi4led.controller.LedController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SmokeTest {

    @Autowired
    private LedController ledController;

    @Autowired
    private GreetingController greetingController;

    @Test
    public void contextLedLoads() throws Exception {
        assertThat(ledController).isNotNull();
    }

    @Test
    public void contextGreetingLoads() throws Exception {
        assertThat(greetingController).isNotNull();
    }

}