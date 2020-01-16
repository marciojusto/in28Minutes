package com.in28minutes.microservices.limitsservice.controller;

import com.in28minutes.microservices.limitsservice.bean.LimitConfiguration;
import com.in28minutes.microservices.limitsservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    private final Configuration configuration;

    @Autowired
    public LimitsConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}
