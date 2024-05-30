package com.findbug.findbugbackend.service;


import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.camera.Camera;
import com.findbug.findbugbackend.domain.camera.CameraType;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.domain.member.MemberCamera;
import com.findbug.findbugbackend.dto.MyPage.*;
import com.findbug.findbugbackend.repository.alarm.AlarmRepository;
import com.findbug.findbugbackend.repository.camera.CameraRepository;
import com.findbug.findbugbackend.repository.member.MemberAlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberCameraRepository;
import com.findbug.findbugbackend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final MemberCameraRepository memberCameraRepository;
    private final MemberAlarmRepository memberAlarmRepository;
    private final CameraRepository cameraRepository;

    public MyPageProfileDto getProfile(Long memberId){
        Member member = memberRepository.findById(memberId);
        return MyPageProfileDto.builder()
                .name(member.getName())
                .imageUrl(member.getImageUrl())
                .loginType("카카오 로그인")
                .build();
    }

    public MyPageAlarmListDto getAlarmList(Long memberId){
        List<MyPageAlarmDto> alarmListDto = alarmRepository.findByMemberId(memberId).stream()
                .map(alarm -> {

                    DetectedBug detectedBug = alarm.getDetectedBugs().isEmpty() ? null : alarm.getDetectedBugs().get(0);

                    String bugName = detectedBug != null ? detectedBug.getBug().getName() : null;

                    return MyPageAlarmDto.builder()
                            .alarmId(alarm.getId())
                            .imageUrl(alarm.getImageUrl())
                            .bugName(bugName)
                            .createAt(alarm.getCreateAt())
                            .cameraName(alarm.getCamera().getName())
                            .build();
                })
                .sorted(Comparator.comparing(MyPageAlarmDto::getCreateAt))
                .collect(Collectors.toList());

        return MyPageAlarmListDto.builder()
                .alarmListDto(alarmListDto)
                .build();
    }

    @Transactional
    public MyPageCameraResponseDto joinCamera(Long memberId, MyPageCameraRequestDto myPageCameraRequestDto) {

        Member member = memberRepository.findById(memberId);

        Camera camera = Camera.createCamera(
                myPageCameraRequestDto.getCameraType(),
                myPageCameraRequestDto.getImei(),
                myPageCameraRequestDto.getName(),
                myPageCameraRequestDto.getDescription()
        );

        Boolean isCameraSaved = cameraRepository.existByIMEI(myPageCameraRequestDto.getImei());
        log.info("{} is exist = {}", camera.getIMEI(), isCameraSaved);

        if(!isCameraSaved){
            // Camera가 등록되지 아니한 경우
            return UpdateCamera(camera, member);
        }else{
            Camera savedCamera = cameraRepository.findByIMEI(myPageCameraRequestDto.getImei());
            Boolean isMemberCameraSaved = memberCameraRepository.existByCamera(savedCamera);
            if(!isMemberCameraSaved){
                // MemberAlarm update
                List<Alarm> alarmList = alarmRepository.findByCamera(savedCamera);
                savedCamera.updateCamera(
                        CameraType.RASPBERRYPI_3_B_PLUS,
                        myPageCameraRequestDto.getName(),
                        myPageCameraRequestDto.getDescription()
                );

                for(Alarm alarm : alarmList){
                    memberAlarmRepository.save(MemberAlarm.builder()
                            .alarm(alarm)
                            .member(member)
                            .isChecked(false)
                            .build());
                }
                return UpdateCamera(savedCamera, member);
            }
            else{
                return new MyPageCameraResponseDto(false);
            }
        }
    }

    private MyPageCameraResponseDto UpdateCamera(Camera camera, Member member) {
        MemberCamera memberCamera = MemberCamera.createMemberCamera(
                camera,
                member,
                LocalDateTime.now()
        );
        memberCameraRepository.save(memberCamera);
        return new MyPageCameraResponseDto(true);
    }

    public MyPageMemberInformationDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId);
        return MyPageMemberInformationDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress())
                .phone(member.getPhoneNumber())
                .build();
    }

    @Transactional
    public MyPageMemberResponseDto updateMember(Long memberId, MyPageMemberInformationDto myPageMemberInformationDto) {
        Member member = memberRepository.findById(memberId);

        // 넣을 수 있는 값인지에 대한 검증 필요
            // 넣을 수 없는 값일 경우 false 반환

        member.updateMember(
                myPageMemberInformationDto.getAddress(),
                null,
                myPageMemberInformationDto.getName(),
                myPageMemberInformationDto.getEmail(),
                myPageMemberInformationDto.getPhone()
        ); // 추후 Company Update 추가할 것   //Dirty Check

        memberRepository.save(member);

        return new MyPageMemberResponseDto(true);
    }

    @Transactional
    public String removeAlarmList(Long memberId) {
        memberAlarmRepository.removeAllByMemberId(memberId);
        return "ok";
    }
}
