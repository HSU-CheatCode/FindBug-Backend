package com.findbug.findbugbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainPageApi {

    /**
     * 메인페이지 - 메인 페이지 정보 제공 및 팝업 정보 제공
     * @param userId
     */
    @GetMapping("main/{userId}/")
    public void getMainPage(@PathVariable("userId") Long userId){

    }

}
