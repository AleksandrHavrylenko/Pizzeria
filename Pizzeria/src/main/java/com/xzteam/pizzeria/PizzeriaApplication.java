package com.xzteam.pizzeria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PizzeriaApplication {

    @Autowired
    private MainController mainController;

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

//    @PostConstruct
//    private void init() {
//        mainController.showAllDishes();
//        mainController.showTests();
//    }
}
