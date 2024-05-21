package com.findbug.findbugbackend.repository.bug;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    /**
     * 최신 발견 벌레 단일 조회 - 최근 발견된 벌레 중 하나를 단일 조회 한다.
     * @param memberId 조회를 위한 사용자 Id
     * @return {@link DetectedBug} 발견된 단일 최신 벌레 정보를 반환한다.
     */
    public DetectedBug findFirstDetectedBugByMember(Long memberId){
        String jpql = "SELECT db FROM DetectedBug db "+
                "JOIN db.alarm a " +
                "JOIN MemberAlarm ma ON ma.alarm = a " +
                "WHERE ma.member.id = :memberId " +
                "ORDER BY db.id ASC";
        return em.createQuery(jpql, DetectedBug.class)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getSingleResult();
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
