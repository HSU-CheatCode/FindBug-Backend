package com.findbug.findbugbackend.domain.member;

import com.findbug.findbugbackend.domain.valueTable.Address;
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
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private LoginMethod loginMethod;

    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;
}
