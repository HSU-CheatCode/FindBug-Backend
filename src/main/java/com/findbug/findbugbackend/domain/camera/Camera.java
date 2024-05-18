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
    private String name;

    @Enumerated(EnumType.STRING)
    private CameraType cameraType;

    @Enumerated(EnumType.STRING)
    private CameraSoftwareVersion cameraSoftwareVersion;

}
