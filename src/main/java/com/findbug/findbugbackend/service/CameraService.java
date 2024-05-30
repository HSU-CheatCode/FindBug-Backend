package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.camera.Camera;
import com.findbug.findbugbackend.domain.camera.CameraType;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.repository.alarm.AlarmRepository;
import com.findbug.findbugbackend.repository.bug.BugRepository;
import com.findbug.findbugbackend.repository.bug.DetectedBugRepository;
import com.findbug.findbugbackend.repository.camera.CameraRepository;
import com.findbug.findbugbackend.repository.member.MemberAlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberCameraRepository;
import com.findbug.findbugbackend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CameraService {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKeyId;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretAccessKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final CameraRepository cameraRepository;
    private final AlarmRepository alarmRepository;
    private final DetectedBugRepository detectedBugRepository;
    private final BugRepository bugRepository;
    private final MemberAlarmRepository memberAlarmRepository;
    private final MemberRepository memberRepository;


    public String uploadImage(String imei, String bugName, LocalDateTime detectedTime, MultipartFile file) throws IOException {

        // 카메라 등록 여부 조회 및 카메라 조회
        if(!cameraRepository.existByIMEI("imei")){
            cameraRepository.save(
                    Camera.builder()
                            .type(CameraType.RASPBERRYPI_3_B_PLUS)
                            .IMEI(imei)
                            .build()
                    );
        }
        Camera camera = cameraRepository.findByIMEI(imei);

        // 이미지 업로드
        String fileName = file.getOriginalFilename();
        String s3Url = uploadToS3(file.getInputStream(), fileName);

        log.info("s3Url: {}", s3Url);

        // 벌레 탐지 알람 저장
        Alarm alarm = Alarm.builder()
                .camera(camera)
                .imageUrl(s3Url)
                .createAt(detectedTime)
                .build();
        alarmRepository.save(alarm);
        detectedBugRepository.save(DetectedBug.builder()
                .bug(bugRepository.findById(1L))
                .alarm(alarm)
                .detectedDate(detectedTime)
                .build()
        );
        List<Member> memberList = memberRepository.findByCamera(camera);
        for(Member member : memberList){
            memberAlarmRepository.save(MemberAlarm.builder()
                    .alarm(alarm)
                    .member(member)
                    .isChecked(false)
                    .build());
        }

        return s3Url;
    }

    private String uploadToS3(InputStream inputStream, String fileName) throws IOException {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, inputStream.available()));

        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
    }
}
