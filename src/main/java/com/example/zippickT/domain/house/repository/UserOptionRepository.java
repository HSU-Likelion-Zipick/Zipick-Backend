package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOptionRepository extends JpaRepository<UserOption, Long> {
}
