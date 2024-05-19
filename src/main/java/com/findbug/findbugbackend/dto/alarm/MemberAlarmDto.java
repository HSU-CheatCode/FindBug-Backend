package com.findbug.findbugbackend.dto.alarm;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MemberAlarmDto {

    public Long id;

}
