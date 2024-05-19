package com.findbug.findbugbackend.repository.member;


import com.findbug.findbugbackend.domain.camera.Camera;
import com.findbug.findbugbackend.domain.member.Member;
import com.findbug.findbugbackend.domain.member.MemberCamera;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberCameraRepository {

    private final EntityManager em;

    public void save(final MemberCamera memberCamera){
        if(memberCamera.getId() == null){
            em.persist(memberCamera);
        }else{
            em.merge(memberCamera);
        }
    }

    public MemberCamera findById(final Long id){
        return em.find(MemberCamera.class, id);
    }

    public List<MemberCamera> findByCamera(final Camera camera) {
        return em.createQuery("select m from MemberCamera m where m.camera = :camera", MemberCamera.class)
                .setParameter("camera", camera)
                .getResultList();
    }

    public List<MemberCamera> findByMember(final Member member){
        return em.createQuery("select m from MemberCamera m where m.member = :member", MemberCamera.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<MemberCamera> findAll(){
        return em.createQuery("select m from MemberCamera m", MemberCamera.class)
                .getResultList();
    }
}
