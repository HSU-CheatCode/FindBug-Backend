package com.findbug.findbugbackend.dto.shopPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class DetectedBugInfoDto {
    public String cameraName;
    public LocalDateTime detectedDate;

}
