package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    @Query("""
            select u
            from User u
            where u.username = :username and u.isActive = :isActive
            """)
    Optional<User> loadUserByUsername(@Param("username") String username,
                                      @Param("isActive") boolean isActive);
}
