package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInfoType;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.dto.InfoDto;
import com.findbug.findbugbackend.dto.MyShopPage.DetectedInfoDto;
import com.findbug.findbugbackend.dto.MyShopPage.MyShopDetectedPageDto;
import com.findbug.findbugbackend.dto.MyShopPage.MyShopPredictPageDto;
import com.findbug.findbugbackend.repository.alarm.AlarmRepository;
import com.findbug.findbugbackend.repository.bug.BugInfoRepository;
import com.findbug.findbugbackend.repository.bug.BugRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyShopPageService {

    private final BugRepository bugRepository;
    private final BugInfoRepository bugInfoRepository;
    private final AlarmRepository alarmRepository;


    public Boolean getMyShopStatus(Long memberId){
        //check 7 day have passed
        LocalDateTime recentDetectedTime = alarmRepository.findRecentDetectedTimeByMemberId(memberId);
        log.info("recentDetectedTime={}", recentDetectedTime);
        // true - 미탐지 , false - 탐지
        return Duration.between(recentDetectedTime, LocalDateTime.now()).toDays() < 7;
    }

    public MyShopPredictPageDto getPredictPage(){
        //create BugInfoDto
        List<Bug> bugList = bugRepository.findAll();
        Bug bug = bugList.get(new Random().nextInt(bugList.size()));
        InfoDto bugInfoDto = InfoDto.builder()
                .imageUrl(bug.getImage())
                .title(bug.getTitle())
                .description(bug.getDescription())
                .build();

        //create areaInfoDtoList
        List<BugInformation> bugAreaInfoList = bugInfoRepository.findByBugAndType(bug, BugInfoType.AREA);
        List<InfoDto> areaInfoDtoList = bugAreaInfoList.stream()
                .map(bugInfo -> InfoDto.builder()
                        .imageUrl(bugInfo.getImage())
                        .title(bugInfo.getTitle())
                        .description(bugInfo.getDescription())
                        .build())
                .collect(Collectors.toList());

        //create predictInfoDtoList
        List<BugInformation> bugPredictInfoList = bugInfoRepository.findByBugAndType(bug, BugInfoType.PREDICT);
        List<InfoDto> predictInfoDtoList = bugPredictInfoList.stream()
                .map(bugInfo -> InfoDto.builder()
                        .imageUrl(bugInfo.getImage())
                        .title(bugInfo.getTitle())
                        .description(bugInfo.getDescription())
                        .build())
                .collect(Collectors.toList());

        //return dto
        return MyShopPredictPageDto.builder()
                .bugInfoDto(bugInfoDto)
                .areaInfoDtoList(areaInfoDtoList)
                .predictInfoDtoList(predictInfoDtoList)
                .build();
    }

    public MyShopDetectedPageDto getDetectedPage(Long memberId){
        //repository
        Alarm recentAlarm = alarmRepository.findRecentAlarmByMemberId(memberId);
        log.info("recentAlarmId = {}", recentAlarm.getId());
        List<DetectedBug> detectedBugList = recentAlarm.getDetectedBugs();  // List.isEmpty() 검증 코드 추가 필요
        Bug bug = null;
        if (!detectedBugList.isEmpty()) {
            bug = detectedBugList.get(0).getBug();
        }

        List<BugInformation> bugDetectedInfoDtoList = new ArrayList<>();
        if (bug != null) {
            bugDetectedInfoDtoList = bugInfoRepository.findByBugAndType(bug, BugInfoType.EXTERMINATE);
        }

        InfoDto bugInfoDto;
        if(bug == null){
            Bug alterBug = bugRepository.findById(1L);
            bugInfoDto = InfoDto.builder()
                    .imageUrl(alterBug.getImage())
                    .title(alterBug.getTitle())
                    .description(alterBug.getDescription())
                    .build();
        }else{
            bugInfoDto = InfoDto.builder()
                    .imageUrl(bug.getImage())
                    .title(bug.getTitle())
                    .description(bug.getDescription())
                    .build();
        }



        //create DetectedInfo
        DetectedInfoDto detectedInfoDto = DetectedInfoDto.builder()
                .detectedDate(recentAlarm.getCreateAt())
                .cameraName(recentAlarm.getCamera().getName())  // fetch joined
                .build();

        //create detectedInfoDtoList
        List<InfoDto> detectedInfoDtoList = bugDetectedInfoDtoList.stream()
                .map(bugInfo -> InfoDto.builder()
                        .imageUrl(bugInfo.getImage())
                        .title(bugInfo.getTitle())
                        .description(bugInfo.getDescription())
                        .build())
                .collect(Collectors.toList());

        return MyShopDetectedPageDto.builder()
                .bugInfoDto(bugInfoDto)
                .alarmInfoDto(detectedInfoDto)
                .detectedInfoDtoList(detectedInfoDtoList)
                .build();
    }
}
