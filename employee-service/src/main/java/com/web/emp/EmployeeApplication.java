package com.web.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.web.util"})
//@ComponentScan(basePackages = {"com.web.util.wrapper", "com.web.util.filters"})
@EnableJpaRepositories(basePackages = {"com.web.util.repos"})
@EntityScan(basePackages = {"com.web.util.entities"})
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
