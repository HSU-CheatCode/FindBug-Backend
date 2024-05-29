package com.findbug.findbugbackend.dto.MainPage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class EventDto {

    public String name;
    public String description;
    public String imageUrl;

}
