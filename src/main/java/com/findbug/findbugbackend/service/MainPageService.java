package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.controller.MainPageApi;
import com.findbug.findbugbackend.dto.InfoDto;
import com.findbug.findbugbackend.dto.MainPage.EventDto;
import com.findbug.findbugbackend.dto.MainPage.MainPageDto;
import com.findbug.findbugbackend.repository.alarm.AlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberAlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberCameraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainPageService {

    private final MemberCameraRepository memberCameraRepository;
    private final MemberAlarmRepository memberAlarmRepository;
    private final AlarmRepository alarmRepository;
    public MainPageDto getMainPage(Long memberId) {

        Boolean isExistCamera;
        Boolean isDetectedBug;

        if(memberId != null){
            isExistCamera = memberCameraRepository.existByMemberId(memberId);
            LocalDateTime recentDetectedTime = alarmRepository.findRecentDetectedTimeByMemberId(memberId);
            isDetectedBug = Duration.between(recentDetectedTime, LocalDateTime.now()).toDays() < 7;
        }else{
            isExistCamera = null;
            isDetectedBug = null;
        }


        List<EventDto> eventDtoList = new ArrayList<>();
        eventDtoList.add(EventDto.builder().name("한성대 이벤트 1").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/title_1.png").build());
        eventDtoList.add(EventDto.builder().name("한성대 이벤트 2").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/title_2.png").build());
        eventDtoList.add(EventDto.builder().name("한성대 이벤트 3").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/title_3.png").build());

        List<InfoDto> infoDtoList = new ArrayList<InfoDto>();
        infoDtoList.add(InfoDto.builder().title("해충 확인").description("AI를 이용하여 카메라로 확인된\n해충을 확인해드립니다.").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/main_1.png").build());
        infoDtoList.add(InfoDto.builder().title("솔루션 제공").description("검증된 해충 박멸 방법을\n알려드립니다").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/main_2.png").build());
        infoDtoList.add(InfoDto.builder().title("저렴한 가격").description("저렴한 가격으로 매장 내 벌레를\n박멸할 수 있습니다.").imageUrl("https://findbugs-bukkit.s3.ap-northeast-2.amazonaws.com/main_3.png").build());


        return MainPageDto.builder()
                .isDetectedBug(isDetectedBug)
                .isExistCamera(isExistCamera)
                .eventDtoList(eventDtoList)
                .infoDtoList(infoDtoList)
                .build();
    }
}
