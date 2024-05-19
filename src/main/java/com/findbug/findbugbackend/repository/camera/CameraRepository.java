package com.findbug.findbugbackend.repository.camera;

import com.findbug.findbugbackend.domain.camera.Camera;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CameraRepository {

    private final EntityManager em;

    public void save(final Camera camera) {
        if (camera.getId() == null) {
            em.persist(camera);
        }else{
            em.merge(camera);
        }
    }

    public Camera findById(final Long id) {
        return em.find(Camera.class, id);
    }

    public List<Camera> findAll() {
        return em.createQuery("from Camera", Camera.class)
                .getResultList();
    }
}
