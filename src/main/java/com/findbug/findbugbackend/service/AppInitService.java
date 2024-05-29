package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInfoType;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.camera.Camera;
import com.findbug.findbugbackend.domain.camera.CameraType;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.domain.member.MemberCamera;
import com.findbug.findbugbackend.repository.bug.BugInfoRepository;
import com.findbug.findbugbackend.repository.bug.DetectedBugRepository;
import com.findbug.findbugbackend.repository.member.MemberAlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberCameraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppInitService {

    private final MemberCameraRepository memberCameraRepository;
    private final MemberAlarmRepository memberAlarmRepository;
    private final BugInfoRepository bugInfoRepository;
    private final DetectedBugRepository detectedBugRepository;

    public void dbSeeding(){

        // Member Seeding
        Member member = Member.builder()
                .name("박종범")
                .email("dismas5184@gmail.com")
                .imageUrl("")
                .address("경기도 하남시 수리북로13번길 38-9")
                .phoneNumber("010-4893-1134")
                .build();


        // Camera Seeding
        Camera camera = Camera.builder()
                .IMEI("#HE332EJK")
                .name("HSU 라즈베리파이3B+")
                .description("운영 중인 식당 카메라")
                .type(CameraType.RASPBERRYPI_3_B_PLUS)
                .build();

        // Alarm Seeding
        Alarm alarm1 = Alarm.builder()
                .imageUrl("A")
                .camera(camera)
                .createAt(LocalDateTime.of(2024, 5, 27, 14, 42, 0))
                .build();
        Alarm alarm2 = Alarm.builder()
                .imageUrl("A")
                .camera(camera)
                .createAt(LocalDateTime.of(2024, 5, 25, 12, 35, 0))
                .build();
        Alarm alarm3 = Alarm.builder()
                .imageUrl("A")
                .camera(camera)
                .createAt(LocalDateTime.of(2024, 5, 28, 12, 32, 0))
                .build();
        Alarm alarm4 = Alarm.builder()
                .imageUrl("A")
                .camera(camera)
                .createAt(LocalDateTime.of(2024, 5, 20, 4, 50, 0))
                .build();
        Alarm alarm5 = Alarm.builder()
                .imageUrl("A")
                .camera(camera)
                .createAt(LocalDateTime.of(2024, 5, 15, 2, 40, 0))
                .build();



        //MemberAlarm Seeding
        MemberAlarm memberAlarm1 = MemberAlarm.builder()
                .alarm(alarm1)
                .member(member)
                .isChecked(false)
                .build();
        MemberAlarm memberAlarm2 = MemberAlarm.builder()
                .alarm(alarm2)
                .member(member)
                .isChecked(false)
                .build();
        MemberAlarm memberAlarm3 = MemberAlarm.builder()
                .alarm(alarm3)
                .member(member)
                .isChecked(false)
                .build();
        MemberAlarm memberAlarm4 = MemberAlarm.builder()
                .alarm(alarm4)
                .member(member)
                .isChecked(false)
                .build();
        MemberAlarm memberAlarm5 = MemberAlarm.builder()
                .alarm(alarm5)
                .member(member)
                .isChecked(false)
                .build();

        memberAlarmRepository.save(memberAlarm1);
        memberAlarmRepository.save(memberAlarm2);
        memberAlarmRepository.save(memberAlarm3);
        memberAlarmRepository.save(memberAlarm4);
        memberAlarmRepository.save(memberAlarm5);




        //MemberCamera Seeding
        MemberCamera memberCamera1 = MemberCamera.builder()
                .camera(camera)
                .member(member)
                .build();

        memberCameraRepository.save(memberCamera1);

        //Bug Seeding
        Bug roach = Bug.builder()
                .name("바퀴벌레")
                .title("바퀴벌레")
                .description("33cm 내외 크기\n250일 생존")
                .image("")
                .build();

        //Bug Detected Info Seeding
        BugInformation detectedInfo1 = BugInformation.builder()
                .title("환경 관리")
                .description("음식물 철저히 관리\n" +
                        "실내 청소를 통한 청결 유지\n" +
                        "바퀴의 은신처와 먹이 제거\n" +
                        "습한 환경 지양")
                .image("")
                .bug(roach)
                .type(BugInfoType.EXTERMINATE)
                .build();
        bugInfoRepository.save(detectedInfo1);
        BugInformation detectedInfo2 = BugInformation.builder()
                .title("트랩 설치")
                .description("바퀴 수가 적거나 은신처가 별로 없을 때 사용\n" +
                        "유인용 먹이를 넣은 끈끈이 트랩은 은폐된 장소에 배치")
                .image("")
                .bug(roach)
                .type(BugInfoType.EXTERMINATE)
                .build();
        bugInfoRepository.save(detectedInfo2);
        BugInformation detectedInfo3 = BugInformation.builder()
                .title("살충제 사용")
                .description("바퀴 박멸 시 살충제를 사용함으로써 확실하게 박멸한다.")
                .image("")
                .bug(roach)
                .type(BugInfoType.EXTERMINATE)
                .build();
        bugInfoRepository.save(detectedInfo3);

        //Bug Area Info Seeding
        BugInformation areaInfo1 = BugInformation.builder()
                .title("배수관")
                .description("연결된 배수관을\n통해 벌레가 유입될 수 있어요!\n청소를 자주 해주세요!")
                .image("")
                .bug(roach)
                .type(BugInfoType.AREA)
                .build();
        bugInfoRepository.save(areaInfo1);
        BugInformation areaInfo2 = BugInformation.builder()
                .title("신문 및 박스")
                .description("종이류를 방치하지 마세요!\n새로운 종이류를 가져올 때\n바퀴류가 없는지 확인하세요!")
                .image("")
                .bug(roach)
                .type(BugInfoType.AREA)
                .build();
        bugInfoRepository.save(areaInfo2);
        BugInformation areaInfo3 = BugInformation.builder()
                .title("출입문 및 문 하부")
                .description("문틈으로 벌레가\n침투할 수 있어요!\n방충망을 꼭 사용하세요!")
                .image("")
                .bug(roach)
                .type(BugInfoType.AREA)
                .build();
        bugInfoRepository.save(areaInfo3);

        BugInformation predictInfo1 = BugInformation.builder()
                .title("음식물 철저히 관리")
                .image("")
                .bug(roach)
                .type(BugInfoType.PREDICT)
                .build();
        bugInfoRepository.save(predictInfo1);
        BugInformation predictInfo2 = BugInformation.builder()
                .title("실내 청소를 통한 청결 유지")
                .image("")
                .bug(roach)
                .type(BugInfoType.PREDICT)
                .build();
        bugInfoRepository.save(predictInfo2);
        BugInformation predictInfo3 = BugInformation.builder()
                .title("바퀴의 은신처와 먹이 제공")
                .image("")
                .bug(roach)
                .type(BugInfoType.PREDICT)
                .build();
        bugInfoRepository.save(predictInfo3);
        BugInformation predictInfo4 = BugInformation.builder()
                .title("습한 환경 지양")
                .image("")
                .bug(roach)
                .type(BugInfoType.PREDICT)
                .build();
        bugInfoRepository.save(predictInfo4);

        //DetectedBug Seeding
        DetectedBug detectedBug1 = DetectedBug.builder()
                .count(1)
                .bug(roach)
                .alarm(alarm1)
                .build();
        detectedBugRepository.save(detectedBug1);
        DetectedBug detectedBug2 = DetectedBug.builder()
                .count(1)
                .bug(roach)
                .alarm(alarm2)
                .build();
        detectedBugRepository.save(detectedBug2);
        DetectedBug detectedBug3 = DetectedBug.builder()
                .count(1)
                .bug(roach)
                .alarm(alarm3)
                .build();
        detectedBugRepository.save(detectedBug3);
        DetectedBug detectedBug4 = DetectedBug.builder()
                .count(1)
                .bug(roach)
                .alarm(alarm4)
                .build();
        detectedBugRepository.save(detectedBug4);
        DetectedBug detectedBug5 = DetectedBug.builder()
                .count(1)
                .bug(roach)
                .alarm(alarm5)
                .build();
        detectedBugRepository.save(detectedBug5);




    }
}
