package com.findbug.findbugbackend.domain.member;

import com.findbug.findbugbackend.domain.camera.Camera;
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
public class MemberCamera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_camera_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime registrationDate;

    public static MemberCamera setMemberCamera(
            Camera camera, Member member, LocalDateTime registrationDate){
        return builder()
                .camera(camera)
                .member(member)
                .registrationDate(registrationDate)
                .build();
    }

    public void updateCamera(Camera camera){
        this.camera = camera;
    }
}
