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

    public List<MemberAlarm> findByMember(Member member){
        return em.createQuery("select m from MemberAlarm m where m.member = :member", MemberAlarm.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<MemberAlarm> findAll() {
        return em.createQuery("select m from MemberAlarm m", MemberAlarm.class)
                .getResultList();
    }




}
