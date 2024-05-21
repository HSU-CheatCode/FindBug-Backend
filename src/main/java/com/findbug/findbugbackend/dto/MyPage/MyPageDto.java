package com.findbug.findbugbackend.dto.MyPage;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import java.util.List;

/**
 * 마이페이지-감지영상 Response Dto
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
public class MyPageDto {
    public List<AlarmDto> alarmList;
}
