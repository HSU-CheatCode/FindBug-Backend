package com.findbug.findbugbackend.repository.alarm;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AlarmRepository {

    private final EntityManager em;

    public void save(final Alarm alarm) {
        if(alarm.getId() == null) {
            em.persist(alarm);
        }else{
            em.merge(alarm);
        }
    }

    public Alarm findById(final Long id) {
        return em.find(Alarm.class, id);
    }

    public List<Alarm> findAll() {
        return em.createQuery("select a from Alarm a", Alarm.class)
                .getResultList();
    }
}
