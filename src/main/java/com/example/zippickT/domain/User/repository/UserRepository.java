package com.example.zippickT.domain.User.repository;

import com.example.zippickT.domain.User.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
}
