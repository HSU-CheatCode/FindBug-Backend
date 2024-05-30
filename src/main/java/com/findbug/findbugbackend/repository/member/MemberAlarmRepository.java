package com.findbug.findbugbackend.repository.member;

import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberAlarmRepository {

    private final EntityManager em;

    public void save(MemberAlarm memberAlarm){
        if(memberAlarm.getId() == null){
            em.persist(memberAlarm);
        }else{
            em.merge(memberAlarm);
        }
    }



    public MemberAlarm findById(Long id){
        return em.find(MemberAlarm.class, id);
    }

    public List<MemberAlarm> findByAlarm(Alarm alarm){
        return em.createQuery("select m from MemberAlarm m where m.alarm = :alarm", MemberAlarm.class)
                .setParameter("alarm", alarm)
                .getResultList();
    }

    /**
     * 멤버 알람 리스트 조회
     * @param member 멤버 객체를 받는다.
     * @return {@link MemberAlarm}을 반환한다. {@link Alarm}을 fetch join 한다.
     */
    public List<MemberAlarm> findByMember(Member member){
        String jpql = "SELECT ma FROM MemberAlarm ma " +
                "JOIN FETCH ma.alarm a " +
                "WHERE ma.member = :member";

        return em.createQuery(jpql, MemberAlarm.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<MemberAlarm> findByMemberId(Long memberId){
        return em.createQuery("select ma from MemberAlarm ma where ma.member.id = :memberId", MemberAlarm.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<MemberAlarm> findNonCheckedAlarm(Member member){
        return em.createQuery("select m from MemberAlarm m where m.isChecked = :false", MemberAlarm.class)
                .setParameter("false", false)
                .getResultList();
    }

    // 한개의 MemberAlarm 을 찾을 때 사용된다.
    public List<MemberAlarm> findByMemberAndAlarm(Member member, Alarm alarm){
        return em.createQuery("select m from MemberAlarm m where m.member = :member and m.alarm = :alarm", MemberAlarm.class)
                .setParameter("member", member)
                .setParameter("alarm", alarm)
                .getResultList();
    }

    public List<MemberAlarm> findAll() {
        return em.createQuery("select m from MemberAlarm m", MemberAlarm.class)
                .getResultList();
    }

    public boolean existsByMember(Member member){
        return em.createQuery("select count(m) from MemberAlarm m where m.member = :member", Long.class)
                .setParameter("member", member)
                .getSingleResult() > 0; // 0보다 클 경우 true
    }

    public void removeByMemberAndAlarm(Member member, Alarm alarm){
        em.remove(findByMemberAndAlarm(member, alarm));
    }

    public void removeByMemberAndAlarmList(Member member, List<Alarm> alarmList){

    }

    public void removeAllByMemberId(Long memberId) {
        List<MemberAlarm> memberAlarms = this.findByMemberId(memberId);

        for(MemberAlarm memberAlarm : memberAlarms){
            em.remove(memberAlarm);
        }
    }
}
