package com.findbug.findbugbackend.repository.bug;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DetectedBugRepository {

    private final EntityManager em;

    public void save(DetectedBug detectedBug){
        if(detectedBug.getId() == null){
            em.persist(detectedBug);
        }else{
            em.merge(detectedBug);
        }
    }

    public DetectedBug findById(Long id){
        return em.find(DetectedBug.class, id);
    }

    public List<DetectedBug> findByAlarm(Alarm alarm){
        return em.createQuery("select d from DetectedBug d where d.alarm = :alarm", DetectedBug.class)
                .setParameter("alarm", alarm)
                .getResultList();
    }

    public List<DetectedBug> findAll(){
        return em.createQuery("select d from DetectedBug d", DetectedBug.class)
                .getResultList();
    }

}
