package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.dto.alarm.AlarmDto;
import com.findbug.findbugbackend.dto.alarm.AlarmPageDto;
import com.findbug.findbugbackend.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AlarmApiController {


    private final AlarmService alarmService;

    // 벌레 발견 여부 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("alarm/{userId}")
    public boolean getBugExists(@PathVariable Long userId) {
        return true;
    }

    /**
     * 마이페이지 / 감지영상
     * [알람ID, 카메라이름, 벌레이름, 벌레사진, 감지일시 반환]
     * @param userId 사용자 아이디 기반으로 모든 영상 리스트 반환
     * @return {@link AlarmPageDto} 감지영상 리스트 반환
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("alarm/{userId}/list")
    public void getBugAlarmList(@PathVariable("id") Long userId) {

    }

    // 벌레 알람 단일 내용 삭제
    @PostMapping("alarm/{userId}/delete/{alarmId}")
    public String deleteAlarm(
            @PathVariable("userId") Long userId, @PathVariable String alarmId) {
        return "ok";
    }

    // 벌레 알람 단일 내용 조회

    // 사용자 벌레 알람 정보 등록 (MQTT protocol)

}
