package com.findbug.findbugbackend.service;


import com.findbug.findbugbackend.domain.alarm.Alarm;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberAlarm;
import com.findbug.findbugbackend.repository.alarm.AlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberAlarmRepository;
import com.findbug.findbugbackend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberAlarmRepository memberAlarmRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveAlarm(Alarm alarm){
        // 카메라로부터 알람 수신
        // MemberAlarm 에 저장
        // case 1 : DB에 직접 저장
        // case 2 : S3에 저장 후 DB에 URL 저장
    }

    /**
     * 사용자 알람 조회 - 사용자의 {@link Alarm} 리스트를 조회한다.
     * @param memberId {@link Member} 사용자 id
     * @return 주어진 회원과 연관된 {@link Alarm} 엔티티들의 목록 반환
     */
    @Transactional
    public List<Alarm> findAlarmList(Long memberId) {
        Member member = memberRepository.findById(memberId);

        return memberAlarmRepository.findByMember(memberRepository.findById(memberId))
                .stream()
                .peek(MemberAlarm::checkAlarm)
                .map(MemberAlarm::getAlarm)
                .collect(Collectors.toList());
    }

    /**
     * 사용자 단일 알람 조회 - 사용자의 단일 {@link Alarm}을 조회한다.
     * @param alarmId {@link Alarm} 을 조회하기 위한 식별자
     * @param memberId 실제로 {@link Alarm}을 가지고 있는지 검증하는 식별자
     * @return 알람 ID와 연관된 {@link Alarm} 엔티티 반환
     */

    public Alarm findAlarm(Long memberId, Long alarmId){
        // MemberAlarm 을 통한 검증 절차 추가 필요
        return alarmRepository.findById(alarmId);
    }

    /**
     * 사용자 알람 확인 여부 조회 - 사용자가 확인하지 못한 {@link Alarm}이 있는지 조회한다.
     * @param memberId 벌레 탐지 여부를 조회하기 위한 사용자 ID
     * @return true(탐지) / false(미탐지)
     */
    public List<MemberAlarm> hasNonCheckAlarmList(Long memberId){
        // 재구성 필요
        Member member = memberRepository.findById(memberId);
        return memberAlarmRepository.findNonCheckedAlarm(member);
    }

    /**
     * 사용자 알람 삭제 - 사용자가 삭제 명령을 내린 {@link MemberAlarm}을 삭제합니다
     * @param memberId MemberAlarm 조회를 위한 member 엔티티 조회
     * @param alarmId MemberAlarm 조회를 위한 alarm 엔티티 조회
     */
    @Transactional
    public void deleteMemberAlarm(Long memberId, List<Long> alarmId){
        // 재구성 필요
        // checked 확인 후
        // 일괄 처리 필요 (단일, 복수 모두 처리 가능하도록)
    }


    
}
