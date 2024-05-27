package com.findbug.findbugbackend.dto.MyPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MyPageAlarmListDto {

    List<MyPageAlarmDto> alarmListDto;

}
