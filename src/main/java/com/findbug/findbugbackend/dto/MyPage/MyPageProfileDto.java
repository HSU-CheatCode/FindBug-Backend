package com.findbug.findbugbackend.dto.MyPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MyPageProfileDto {
    public String imageUrl;
    public String name;
    public String loginType;
}
