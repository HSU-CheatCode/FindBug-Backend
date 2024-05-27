package com.findbug.findbugbackend.dto.MainPage;

import com.findbug.findbugbackend.dto.InfoDto;
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
public class MainPageDto {
    Boolean isExistCamera;
    Boolean isDetectedBug;

    List<EventDto> eventDtoList;
    List<InfoDto> infoDtoList;
}
