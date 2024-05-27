package com.findbug.findbugbackend.domain.member;

import com.findbug.findbugbackend.domain.Address;
import com.findbug.findbugbackend.domain.Company;
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
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String address;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime registrationDate;


    public String getRoleKey(){
        return role.getKey();
    }

    public static Member createMember(
            String address, Company company, String name, String email, String phoneNumber, LocalDateTime registrationDate) {
        return builder()
                .address(address)
                .company(company)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .registrationDate(registrationDate)
                .build();
    }

    public void updateMember(String address, Company company, String name, String email, String phoneNumber) {
        this.address = address;
        this.company = company;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
