package com.findbug.findbugbackend.domain.valueTable;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Jacksonized
public class Address {
    private String address;
    //지역명1
    private String region_1depth;
    //지역명2
    private String region_2depth;
    //도로명
    private String region_3depth;

    private String region_4depth;
}
