package com.findbug.findbugbackend.controller;

import com.findbug.findbugbackend.service.InitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AwsElasticBeanstalkCheckApi {

    private final InitService initService;
    @GetMapping("initDB")
    public void initDatabaseApi(){
        initService.dbSeeding();
    }
}
