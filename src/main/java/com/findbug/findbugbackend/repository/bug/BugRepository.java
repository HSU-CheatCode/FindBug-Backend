package com.findbug.findbugbackend.repository.bug;

import com.findbug.findbugbackend.domain.bug.Bug;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BugRepository {

    private final EntityManager em;

    public void save(final Bug bug) {
        if(bug.getId() == null) {
            em.persist(bug);
        }else{
            em.merge(bug);
        }
    }

    public Bug findById(final Long id) {
        return em.find(Bug.class, id);
    }

    public List<Bug> findAll() {
        return em.createQuery("select b from Bug b", Bug.class)
                .getResultList();
    }
}
