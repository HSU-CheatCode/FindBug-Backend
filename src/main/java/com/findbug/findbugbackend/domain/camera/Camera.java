package com.findbug.findbugbackend.domain.camera;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Jacksonized
@Getter
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CameraType type;

    private String IMEI;
    private String name;
    private String description;

    public static Camera createCamera(CameraType type, String IMEI, String name, String description) {
        return builder()
                .type(type)
                .IMEI(IMEI)
                .name(name)
                .description(description)
                .build();
    }

    public void updateCamera(CameraType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
}
