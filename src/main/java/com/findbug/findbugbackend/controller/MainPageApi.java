package com.findbug.findbugbackend.controller;

import com.findbug.findbugbackend.dto.MainPage.MainPageDto;
import com.findbug.findbugbackend.service.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainPageApi {


    private final MainPageService mainPageService;
    /**
     * 메인페이지
     * 메인 페이지 정보와 팝업 정보를 위한 데이터를 반환한다.
     */
    @GetMapping("mainPage/{userId}")
    public MainPageDto getMainPage(@PathVariable("userId") Long userId){

        return mainPageService.getMainPage(userId);


    }
}
