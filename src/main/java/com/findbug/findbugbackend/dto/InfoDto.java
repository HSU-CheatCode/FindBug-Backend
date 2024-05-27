package com.findbug.findbugbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class InfoDto {
    public String imageUrl;
    public String title;
    public String description;
}
