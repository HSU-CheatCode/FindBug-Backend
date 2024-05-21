package com.findbug.findbugbackend.dto.shopPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class BugInfoDto {
    public String Title;
    public String Description;
    public String Image;
}
