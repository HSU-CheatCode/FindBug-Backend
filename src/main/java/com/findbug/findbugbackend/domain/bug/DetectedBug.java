package com.findbug.findbugbackend.domain.bug;
import com.findbug.findbugbackend.domain.alarm.Alarm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Jacksonized
@Getter
public class DetectedBug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detected_bug_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bug_id")
    private Bug bug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

    private LocalDateTime detected_date;
    private int count;

    public static DetectedBug createDetectedBug(Bug bug, Alarm alarm, LocalDateTime detected_date, int count) {
        return builder()
                .bug(bug)
                .alarm(alarm)
                .detected_date(detected_date)
                .count(count)
                .build();
    }

    public void updateAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}
