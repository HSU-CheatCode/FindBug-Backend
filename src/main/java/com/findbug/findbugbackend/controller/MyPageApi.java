package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.dto.MyPage.MyPageDto;
import com.findbug.findbugbackend.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyPageApi {


    private final AlarmService alarmService;


    /**
     * 마이페이지/알람조회 - 사용자의 벌레 탐지 알람 리스트를 조회한다.
     * @param userId
     * @return {@link MyPageDto}
     */
    @GetMapping("myPage/{userId}/alarmList")
    public MyPageDto getAlarmList(
            @PathVariable("userId") Long userId){

        return MyPageDto.builder()
                .build();
    }

    /**
     * 마이페이지/카메라등록 - 사용자가 사용할 카메라를 등록한다.
     */
    @PostMapping("myPage/{userId}/setCamera")
    public void setCamera(){

    }

    // 2차 MVP

//    // 벌레 알람 단일 내용 삭제
//    @PostMapping("alarm/{userId}/delete/{alarmId}")
//    public String deleteAlarm(
//            @PathVariable("userId") Long userId, @PathVariable String alarmId) {
//        return "ok";
//    }

}
