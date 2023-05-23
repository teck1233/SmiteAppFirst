package com.example.demo.repositories;

import com.example.demo.models.SmiteHero;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmiteHeroRepository extends JpaRepository<SmiteHero, Integer> {
    SmiteHero findByNameAndRole(String name, String role);
    SmiteHero findById(int id);
    List<SmiteHero> findAllByMode(String mode, Sort id);

    List<SmiteHero> findAllByModeAndEnemy(String mode, String enemy);


}
