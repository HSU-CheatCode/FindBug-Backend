package com.findbug.findbugbackend.service;

import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import com.findbug.findbugbackend.repository.bug.BugInfoRepository;
import com.findbug.findbugbackend.repository.bug.BugRepository;
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
}
