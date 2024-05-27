package com.findbug.findbugbackend.dto.MyShopPage;

import com.findbug.findbugbackend.dto.InfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MyShopDetectedPageDto {
    public InfoDto BugInfoDto;
    public DetectedInfoDto alarmInfoDto;
    public List<InfoDto> detectedInfoDtoList;
}
