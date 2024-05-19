package com.findbug.findbugbackend.repository;

import com.findbug.findbugbackend.domain.Company;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final EntityManager em;

    public void save(Company company){
        if(company.getId() == null){
            em.persist(company);
        }else{
            em.merge(company);
        }
    }
}
