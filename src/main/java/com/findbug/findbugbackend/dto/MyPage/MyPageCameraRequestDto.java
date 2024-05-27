package com.findbug.findbugbackend.dto.MyPage;

import com.findbug.findbugbackend.domain.camera.CameraType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
public class MyPageCameraRequestDto {
    public String IMEI;
    public String name;
    public String description;
    public CameraType cameraType;
}
