package com.findbug.findbugbackend.controller;

import com.findbug.findbugbackend.service.AppInitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AwsElasticBeanstalkCheckApi {

    private final AppInitService initService;

    @PostMapping("/initDB")
    public void initDatabaseApi(){
        initService.dbSeeding();
    }


    @GetMapping("/health")
    public String AwsElasticBeanstalkCheck(){
        return "ok";
    }


    @GetMapping("cameraTest/{id}")
    public String TestCamera(@PathVariable("id") Long id){
        log.info("camera request: {}", id);
        return "ok";
    }
}
