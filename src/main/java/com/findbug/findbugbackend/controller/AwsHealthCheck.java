package com.findbug.findbugbackend.controller;

import com.findbug.findbugbackend.service.AppInitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AwsHealthCheck {

    private final AppInitService initService;

    @PostMapping("/initDB")
    public String initDatabaseApi(){
        initService.dbSeeding();
        return "initOK";
    }

    @GetMapping("/health")
    public String AwsElasticBeanstalkCheck(){
        return "ok";
    }

    @GetMapping("/")
    public String defaultHealthCheck(){
        return "ok";
    }
}
