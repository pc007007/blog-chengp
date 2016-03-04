package com.chengp.repository;

import com.chengp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 3/3/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
