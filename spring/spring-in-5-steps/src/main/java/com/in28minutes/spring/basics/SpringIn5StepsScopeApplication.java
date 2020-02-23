package com.in28minutes.spring.basics;

import com.in28minutes.spring.basics.scope.PersonDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringIn5StepsScopeApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(SpringIn5StepsScopeApplication.class, args);

        PersonDAO personDAO = applicationContext.getBean(PersonDAO.class);

        PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

        log.info("{}", personDAO);
        log.info("{}", personDAO.getJdbcConnection());

        log.info("{}", personDAO2);
        log.info("{}", personDAO2.getJdbcConnection());
    }

}