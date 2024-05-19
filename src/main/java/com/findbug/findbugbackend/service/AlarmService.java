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
     * - 알람 리스트 조회 -
     * (issue) 마이페이지 / 감지영상에서는 카메라 정보도 필요하다. memberAlarm, alarm, detectedBug, camera 정보가 모두 필요하다.
     * (issue) 이를 알람 서비스에서 모두 처리하는 것이 옳은가? -> fetch join이 필요할 것이다.
     * 회원 ID에 해당하는 벌레 탐지 목록을 찾고 반환합니다.
     * 해당 메서드는 주어진 회원 ID로 {@link Member} 엔티티를 조회한 다음,
     * 해당 회원과 연관된 {@link MemberAlarm} 엔티티를 찾습니다.
     * 마지막으로 연관된 {@link Alarm} 엔티티의 목록을 수집하여 반환합니다.
     * @param memberId {@link MemberAlarm} 을 조회하기 위한 회원 ID
     * @return 주어진 회원과 연관된 {@link Alarm} 엔티티들의 목록 반환
     */
    public List<Alarm> findAlarmList(Long memberId) {
        Member member = memberRepository.findById(memberId);
        return memberAlarmRepository.findByMember(member).stream()
                .map(MemberAlarm::getAlarm)
                .collect(Collectors.toList());
    }

    /**
     * - 단일 알람 조회 -
     * (issue) 알람 조회 시 member 와 alarm_id 를 토대로 memberAlarm 을 찾아야 하지 않는가.
     * (issue) 사용자가 소유한 알람만 조회할 수 있도록 조치해야 하지 않는가?
     * 알람 ID에 해당하는 알람을 찾고 반환합니다.
     * 해당 메서드는 주어진 알람 ID와 사용자 ID로 {@link Alarm} 엔티티를 조회하여 반환합니다.
     * @param alarmId {@link Alarm} 을 조회하기 위한 알람 ID
     * @return 알람 ID와 연관된 {@link Alarm} 엔티티 반환
     */

    public Alarm findAlarm(Long memberId, Long alarmId){
        return alarmRepository.findById(alarmId);
    }

    /**
     * - 벌레 탐지 여부 조회 (알람 갯수 반환) -
     * 사용자가 확인하지 않은 벌레 탐지 내역이 있는지 확인합니다.
     * 사용자 ID를 토대로 {@link MemberAlarm}을 조회 후, 벌레 탐지 내역이 있는지 확인합니다.
     * @param memberId 벌레 탐지 여부를 조회하기 위한 사용자 ID
     * @return true(탐지) / false(미탐지)
     */
    public boolean isDetectedBug(Long memberId){
        Member member = memberRepository.findById(memberId);
        return memberAlarmRepository.existsByMember(member);
    }

    /**
     * - 사용자 단일 알람 삭제 -
     * 벌레 탐지 알람 {@link MemberAlarm}을 삭제한다.
     * 여기서는 {@link MemberAlarm}만 다룬다. {@link Alarm}은 이후 배치처리로 일괄 삭제한다.
     * @param memberId MemberAlarm 조회를 위한 member 엔티티 조회
     * @param alarmId MemberAlarm 조회를 위한 alarm 엔티티 조회
     */
    @Transactional
    public void deleteMemberAlarm(Long memberId, Long alarmId){
        Member member = memberRepository.findById(memberId);
        Alarm alarm = alarmRepository.findById(alarmId);
        memberAlarmRepository.findByMemberAndAlarm(member, alarm);

    }

    /**
     * - 사용자 복수 알람 삭제 -
     * @param memberId
     * @param alarmIdList
     */
    @Transactional
    public void deleteMemberAlarmList(Long memberId, List<Long> alarmIdList){}

    /**
     * - 사용자 알람 전체 삭제 -
     * @param memberId
     */

    @Transactional
    public void deleteMemberAlarmAll(Long memberId){}



}
