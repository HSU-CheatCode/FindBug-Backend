package com.findbug.findbugbackend.dto.MyShopPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
@Getter
@Setter
public class MyShopStatusDto {
    boolean isDetected;
}
