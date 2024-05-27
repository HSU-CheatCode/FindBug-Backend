package com.findbug.findbugbackend.domain.alarm;

import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.camera.Camera;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Jacksonized
@Getter
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @OneToMany(mappedBy = "alarm")
    private List<DetectedBug> detectedBugs;

    private String imageUrl;

    private LocalDateTime createAt;

    public static Alarm createAlarm(Camera camera, List<DetectedBug> detectedBugs) {

        return builder()
                .camera(camera)
                .detectedBugs(detectedBugs)
                .build();
    }

    public void addDetectedBug(DetectedBug detectedBug) {
        detectedBugs.add(detectedBug);
        detectedBug.updateAlarm(this);
    }
}
