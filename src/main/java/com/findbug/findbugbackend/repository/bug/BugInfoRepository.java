package com.findbug.findbugbackend.repository.bug;


import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BugInfoRepository {

    private final EntityManager em;

    public void save(BugInformation bugInformation){
        if(bugInformation.getId() == null){
            em.persist(bugInformation);
        }else {
            em.merge(bugInformation);
        }
    }

    public List<BugInformation> findByBug(Bug bug){
        return em.createQuery("select b from BugInformation b where b.bug = :bug", BugInformation.class)
                .setParameter("bug", bug)
                .getResultList();
    }

}
