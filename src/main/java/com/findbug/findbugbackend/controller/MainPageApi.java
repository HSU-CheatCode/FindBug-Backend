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
     * 메인 페이지 Api
     * 이벤트 팝업 등 다양한 이미지를 제공한다.
     *
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("main")
    public void getMainPageInformation(){

    }

    /**
     * 마이페이지-메인 / 프로필 Api
     *
     * 사용자의 프로필, 이름, 로그인 상태를 보여준다
     * 이름은 처음에 임의로 지정하여 제공한다.
     *
     * @param id
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myPage/{userId}/profile")
    public void getUserProfile(@PathVariable("userId") Long id){

    }


    // 로그인

    // 로그인 - OAuth2

    // 회원가입

    // 회원수정
}
