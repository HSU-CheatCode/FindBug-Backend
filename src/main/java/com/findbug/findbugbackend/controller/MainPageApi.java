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
     * 메인페이지
     * 메인 페이지 정보와 팝업 정보를 위한 데이터를 반환한다.
     */
    @GetMapping("main/{userId}")
    public void getMainPage(@PathVariable("userId") Long userId){

        // MainPage 관련 데이터는 별도로 데이터베이스에 저장하지 않았음.
        // 노가다로 작성할 것


        // 비로그인 상태 - 추후 검증코드 및 세션 방식으로 업데이트 할 것
        if(userId == null){

        }else{

        }
    }
}
