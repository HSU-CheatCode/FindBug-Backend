package com.findbug.findbugbackend.repository.alarm;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public void removeById(final Long id){ em.remove(em.find(Alarm.class, id)); }

    public List<Alarm> findAll() {
        return em.createQuery("select a from Alarm a", Alarm.class)
                .getResultList();
    }

    public Alarm findRecentAlarmByMemberId(final Long memberId) {
        return em.createQuery("select a from Alarm a " +
                        "join fetch Camera " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId " +
                        "order by a.createAt desc", Alarm.class)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getSingleResult();
    }

    public List<Alarm> findByMemberId(final Long memberId) {
        return em.createQuery("select a from Alarm a " +
                        "join fetch Camera " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId", Alarm.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public LocalDateTime findLocalDateTimeByMemberId(final Long memberId){
        return em.createQuery("select a.createAt from Alarm a " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId " +
                        "order by a.createAt desc", LocalDateTime.class)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getSingleResult();
    }
}
