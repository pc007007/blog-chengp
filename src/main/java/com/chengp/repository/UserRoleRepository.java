package com.chengp.repository;

import com.chengp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 3/4/16.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>{

}
