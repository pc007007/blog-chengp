package com.chengp.repository;

import com.chengp.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 3/7/16.
 */
@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
