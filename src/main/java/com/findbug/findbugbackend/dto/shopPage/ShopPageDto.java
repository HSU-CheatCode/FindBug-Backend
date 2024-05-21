package com.findbug.findbugbackend.dto.shopPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class ShopPageDto {

    // 벌레 정보
    public BugInfoDto topInfo;

    // 벌레 감지 정보
    public DetectedBugInfoDto detectedInfo;

    // 벌레 상세 정보
    public List<BugInfoDto> detailInfo;
    
}
