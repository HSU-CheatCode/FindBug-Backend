package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.dto.shopPage.BugInfoDto;
import com.findbug.findbugbackend.dto.shopPage.DetectedBugInfoDto;
import com.findbug.findbugbackend.dto.shopPage.ShopPageDto;
import com.findbug.findbugbackend.service.AlarmService;
import com.findbug.findbugbackend.service.BugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyShopPageApi {

    private final AlarmService alarmService;
    private final BugService bugService;

    /**
     * 나의 가게 API
     * @param id 사용자 ID
     * @return {@link ShopPageDto} 반환
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShop/{userId}")
    public ShopPageDto getBugInformation(@PathVariable("userId") Long id) {

        // DetectedBug 내에 DetectedDate 와 Bug 정보가 모두 존재한다.
        DetectedBug detectedBug = bugService.getFirstDetectedBug(id);

        // 벌레 내부에 값이 들어있을 경우
        if (!(detectedBug == null)) {
            // 최상단 벌레 정보(Bug Entity - Img + Title + Description)
            // detectedBug 와 Bug 를 fetch join 하여 객체 그래프 사용
            Bug recentDetectedBug = detectedBug.getBug();

            BugInfoDto topInfoDto = BugInfoDto.builder()
                    .Image(recentDetectedBug.getImage())
                    .Title(recentDetectedBug.getTitle())
                    .Description(recentDetectedBug.getDescription())
                    .build();

            // 탐지 상세 정보 (Date, CamaraName)
            // 알람 객체를 이용하여 카메라 이름을 가져온다.
            DetectedBugInfoDto detectedInfoDto = DetectedBugInfoDto.builder()
                    .detectedDate(detectedBug.getDetectedDate())
                    .cameraName("camera 1")
                    .build();

            // 벌레 상세 정보 List<(Img, Title, Description)>
            List<BugInformation> bugInformationList = bugService.getDetailBugInfo(recentDetectedBug);
            List<BugInfoDto> bugInfoDtoList = new ArrayList<>();

            for (BugInformation bugInformation : bugInformationList) {
                bugInfoDtoList.add(BugInfoDto.builder()
                        .Image(bugInformation.getImage())
                        .Title(bugInformation.getTitle())
                        .Description(bugInformation.getDescription())
                        .build()
                );
            }

            return ShopPageDto.builder()
                    .topInfo(topInfoDto)
                    .detectedInfo(detectedInfoDto)
                    .detailInfo(bugInfoDtoList)
                    .build();
        }else{

            // 아직 어떠한 벌레도 발견되지 않았거나
            // 기존 발견 벌레 리스트를 모두 삭제한 경우, 기본 바퀴벌레 정보를 전달한다.

            BugInfoDto topInfoDto;
            List<BugInfoDto> bugInfoDtoList = new ArrayList<>();

            return  ShopPageDto.builder()
                    .topInfo(null)
                    .detailInfo(null)
                    .detailInfo(null)
                    .build();
        }
        // else -> 기본 바퀴벌레 값 제공


    }
}
