package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyShopPageApi {

    private final AlarmService alarmService;

    /**
     * 나의 가게 Page API
     * @request  - 없음
     * @response - 벌레 발견시간 및 예방 정보
     *
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShop/{userId}")
    public void getBugInformation(@PathVariable("userId") Long id){

        // 벌레 탐지 여부 확인 - memberAlarms 확인
        // if (벌레 탐지) -> Bug 벌레 정보와 Alarm 벌레 탐지 정보 제공
        Bug

        if(!memberAlarms.isEmpty()){

            MemberAlarm displayAlarm = memberAlarms.get(0);

            // top

            // member를 통해서 첫번째 벌레를 찾는 레포지토리가 필요해 보인다.
            DetectedBug displayBug = displayAlarm.getAlarm().getDetectedBugs().get(0);


            // detected



            // member

        }else{

        }
        // else -> 기본 바퀴벌레 값 제공



    }




}
