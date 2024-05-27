package com.findbug.findbugbackend.dto.MyPage;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MyPageAlarmDto {
    public Long alarmId;
    public String imageUrl;
    LocalDateTime createAt;
    public String bugName;
    public String cameraName;
}
