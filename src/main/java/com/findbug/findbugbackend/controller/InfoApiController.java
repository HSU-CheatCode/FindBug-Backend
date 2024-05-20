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
public class InfoApiController {

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
     * 나의 가게 Page Api
     *
     * 다음 정보를 제공한다
     * Bug 벌레 정보 제공 (name, description)
     * Alarm 벌레 탐지 정보 제공 (없을 시 null)
     * BugInformantion 벌레 정보 제공. image, title, description 으로 제공
     *
     * @request - 없음
     * @response - 벌레 발견시간 및 예방 정보
     *
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShop/{userId}")
    public void getBugInformation(@PathVariable("userId") Long id){

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


}
