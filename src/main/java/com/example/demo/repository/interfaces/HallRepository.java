package com.example.demo.repository.interfaces;

import com.example.demo.entity.booking.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>{

    Optional<Hall> findByHallImages(Long id);
    Optional<Hall> findByHallImagesAndSeances(Long image, Long seance);

    List<Hall> findAll();
}
