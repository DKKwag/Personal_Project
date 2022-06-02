package com.personal.project.repository;

import com.personal.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //ID중복확인을 위한 변수 선언
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);

    Optional<User> findByUsername(String username);
}
