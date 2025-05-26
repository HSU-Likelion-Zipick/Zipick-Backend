package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserOptionRepository extends JpaRepository<UserOption, Long> {
    //List<UserOption> findByMemberId(Long id);
}
