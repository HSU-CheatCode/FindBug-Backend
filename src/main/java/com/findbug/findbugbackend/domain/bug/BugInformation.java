package com.findbug.findbugbackend.domain.bug;

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
public class BugInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_information_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bug_id")
    private Bug bug;


    @Enumerated(EnumType.STRING)
    private BugInfoType type;

    private String image;
    private String title;
    private String description;

    public static BugInformation createBugInformation(Bug bug, String image, BugInfoType type, String description) {
        return builder()
                .bug(bug)
                .type(type)
                .image(image)
                .description(description)
                .build();
    }
}
