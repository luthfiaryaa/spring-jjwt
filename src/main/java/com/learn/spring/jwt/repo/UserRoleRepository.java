package com.learn.spring.jwt.repo;

import com.learn.spring.jwt.domain.dao.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserIdAndRole(Long userId, String role);

}
