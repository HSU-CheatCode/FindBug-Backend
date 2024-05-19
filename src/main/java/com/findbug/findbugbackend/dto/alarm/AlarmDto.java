package com.findbug.findbugbackend.dto.alarm;

import com.findbug.findbugbackend.dto.DetectedBugDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

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
