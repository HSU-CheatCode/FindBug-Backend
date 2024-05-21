package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import com.findbug.findbugbackend.domain.bug.DetectedBug;
import com.findbug.findbugbackend.repository.bug.BugInfoRepository;
import com.findbug.findbugbackend.repository.bug.BugRepository;
import com.findbug.findbugbackend.repository.bug.DetectedBugRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BugService {

    private final BugRepository bugRepository;
    private final DetectedBugRepository detectedBugRepository;
    private final BugInfoRepository bugInfoRepository;

    /**
     * 단일 벌레 정보 조회 - 단일 벌레 정보를 조회한다.
     * @param bugId {@link Bug} 벌레 id
     * @return {@link Bug} 벌레 객체 반환
     */
    public Bug getBugInfo(Long bugId) {
        return bugRepository.findById(bugId);
    }

    /**
     * 상세 벌레 정보 조회 - 해당 벌레에 대한 상세 정보를 조회한다.
     * @param bug {@link Bug} 벌레 객체
     * @return {@link BugInformation} 벌레 상세 정보 리스트 반환
     */
    public List<BugInformation> getDetailBugInfo(Bug bug) {
        return bugInfoRepository.findByBug(bug);
    }


    /**
     * 최근 감지 단일 {@link Bug} 정보 조회 - 최근 감지된 벌레 정보(한마리)를 조회한다.
     * @param userId 사용자 id로 조회한다.
     * @return {@link Bug} 객체를 반환한다.
     */
    public Bug getFirstBug(Long userId){
        return bugRepository.findFirstBugsByMemberId(userId);
    }

    /**
     * 최근 감지 단일 {@link DetectedBug} - 최근 감지된 상세 벌레 정보를 조회한다.
     * @param memberId 사용자 id로 조회한다.
     * @return {@link DetectedBug} 객체를 반환한다
     */
    public DetectedBug getFirstDetectedBug(Long memberId){
        return detectedBugRepository.findFirstDetectedBugByMember(memberId);
    }

}
