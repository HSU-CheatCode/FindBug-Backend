package com.findbug.findbugbackend.domain;

import com.findbug.findbugbackend.domain.member.Member;
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
public class PestControlCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pest_control_company_id")
    private Long id;
    private String name;
    private String email;
    private String homepage;
    private String phoneNumber;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String companyDescription;
    private String businessDescription;
    private byte[] logoImage;
}
