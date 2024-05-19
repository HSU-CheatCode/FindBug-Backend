package com.findbug.findbugbackend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class DetectedBugDto {
    public Long bugId;
    public String bugName;
    public Long count;
    public LocalDateTime detectedDate;
}
