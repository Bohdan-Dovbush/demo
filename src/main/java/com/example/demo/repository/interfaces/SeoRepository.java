package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Seo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeoRepository extends JpaRepository<Seo, Long> {
}
