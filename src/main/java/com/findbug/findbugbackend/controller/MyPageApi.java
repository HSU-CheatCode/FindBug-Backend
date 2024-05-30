package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.dto.MyPage.*;
import com.findbug.findbugbackend.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyPageApi {

    private final MyPageService myPageService;

    /**
     * 마이페이지 / 프로필 페이지
     * 본인의 프로필을 간략하게 확인한다.
     * @param memberId - 사용자 ID
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myPage/{memberId}")
    public MyPageProfileDto myPageGetProfileApi(@PathVariable("memberId") Long memberId) {
        return myPageService.getProfile(memberId);
    }

    /**
     * 마이페이지 / 벌레 감지 알람 페이지
     * 감지된 벌레 발견 정보를 조회한다.
     * @param memberId - 사용자 ID
     * @return {@link MyPageAlarmListDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myPage/{memberId}/alarms")
    public MyPageAlarmListDto myPageGetAlarmApi(@PathVariable("memberId") Long memberId) {
        return myPageService.getAlarmList(memberId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("myPage/{memberId}/alarms")
    public String myPageRemoveAllAlarmApi(@PathVariable("memberId") Long memberId) {
        return myPageService.removeAlarmList(memberId);
    }

    /**
     * 마이페이지 / 카메라 등록
     * 카메라를 등록한다.
     * @param {@link MyPageCameraRequestDto}
     * @return {@link MyPageCameraResponseDto} true - 성공 / false - 실패
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("myPage/{memberId}/camera")
    public MyPageCameraResponseDto myPageSetCameraApi(
            @PathVariable("memberId") Long memberId,
            @RequestBody MyPageCameraRequestDto myPageCameraRequestDto) {
        return myPageService.joinCamera(memberId, myPageCameraRequestDto);
    }


    /**
     * 마이페이지 / 회원 정보 수정 페이지 / 회원 정보 조회
     * 사전에 회원 정보를 조회에서 입력한다.
     * @param memberId - 사용자 ID
     * @return {@link MyPageMemberResponseDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myPage/{memberId}/info")
    public MyPageMemberInformationDto myPageGetMemberInfoApi(@PathVariable("memberId") Long memberId) {
        return myPageService.getMemberInfo(memberId);
    }


    /**
     * 마이페이지 / 회원 정보 수정
     * 회원 정보를 수정한다.
     * @param memberId - 사용자 ID
     * @return {@link MyPageMemberResponseDto} true - 성공 / false - 실패
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("myPage/{memberId}/info")
    public MyPageMemberResponseDto myPageUpdateMemberApi(
            @PathVariable("memberId") Long memberId,
            @RequestBody MyPageMemberInformationDto myPageMemberInformationDto) {
        return myPageService.updateMember(memberId, myPageMemberInformationDto);
    }

}
