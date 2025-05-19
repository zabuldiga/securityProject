package com.example.security.repository;

import com.example.security.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findAll();
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users_roles(user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addRoleToUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
