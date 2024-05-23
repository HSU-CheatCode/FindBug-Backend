package com.findbug.findbugbackend.repository.bug;


import com.findbug.findbugbackend.domain.bug.Bug;
import com.findbug.findbugbackend.domain.bug.BugInformation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BugInfoRepository {

    private final EntityManager em;

    public void save(BugInformation bugInformation){
        if(bugInformation.getId() == null){
            em.persist(bugInformation);
        }else {
            em.merge(bugInformation);
        }
    }

    /**
     * 벌레 상세 정보 리스트 조회 - 벌레 상세 정보에 대한 리스트를 반환한다.
     * 차후, 리스트에 종류가 추가될 경우, 단일 테이블 전략 우선 사용 후 다른 전략으로 교체할 것
     * @param bug 벌레 객체를 받는다
     * @return {@link BugInformation} 객체 리스트를 반환한다.
     */
    public List<BugInformation> findByBug(Bug bug){
        return em.createQuery("select b from BugInformation b where b.bug = :bug", BugInformation.class)
                .setParameter("bug", bug)
                .getResultList();
    }

}
