package com.example.zippickT.domain.User.repository;

import com.example.zippickT.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
