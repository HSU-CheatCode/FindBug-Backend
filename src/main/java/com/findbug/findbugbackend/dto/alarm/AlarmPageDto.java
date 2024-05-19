package com.findbug.findbugbackend.dto.alarm;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import java.util.List;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class AlarmPageDto {
    public List<AlarmDto> alarmList;
}
