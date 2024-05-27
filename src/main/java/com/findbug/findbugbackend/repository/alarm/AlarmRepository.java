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

    /**
     * 1. 알람 레포지토리 - 최신 알람 조회
     * Camera Fetch joined
     * @param memberId
     * @return {@link Alarm}
     */
    public Alarm findRecentAlarmByMemberId(final Long memberId) {
        return em.createQuery("select a from Alarm a " +
                        "join fetch a.camera c " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId " +
                        "order by a.createAt desc", Alarm.class)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getSingleResult();
    }

    /**
     * 2. 알람 레포지토리 - 사용자 ID를 이용한 알람 리스트 조회
     * @param memberId
     * @return
     */
    public List<Alarm> findByMemberId(final Long memberId) {
        return em.createQuery("select a from Alarm a " +
                        "join fetch a.camera c " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId", Alarm.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 3. 알람 레포지토리 - 사용자 ID를 이용한 최신 탐지 시간 확인
     * @param memberId
     * @return
     */
    public LocalDateTime findRecentDetectedTimeByMemberId(final Long memberId){
        return em.createQuery("select a.createAt from Alarm a " +
                        "join MemberAlarm ma on a.id = ma.alarm.id " +
                        "where ma.member.id = :memberId " +
                        "order by a.createAt desc", LocalDateTime.class)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getSingleResult();
    }
}
