package com.findbug.findbugbackend.domain.member;

import com.findbug.findbugbackend.domain.alarm.Alarm;
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
public class MemberAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_alarm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isChecked;


    public void checkAlarm(){
        this.isChecked = true;
    }

    public static MemberAlarm createMemberAlarm(Alarm alarm, Member member) {
        return builder()
                .alarm(alarm)
                .member(member)
                .build();
    }
}
