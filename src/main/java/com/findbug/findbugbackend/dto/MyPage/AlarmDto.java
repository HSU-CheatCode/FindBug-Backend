package com.findbug.findbugbackend.dto.MyPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

/**
 * 마이페이지-단일 감지 영상 Response Dto
 *
 * 다음 자료를 리스트에 담아 전달한다.
 * 1. 카메라 이름
 * 2. 벌레 이름
 * 3. 탐지 일자
 * 4. 알람 ID
 * 5. 벌레 이미지 (URL or Image)
 */

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class AlarmDto {
    public Long alarmId;
    public String cameraName;
    public String bugName;
    public String bugImage;
    public LocalDateTime detectedDate;
}
