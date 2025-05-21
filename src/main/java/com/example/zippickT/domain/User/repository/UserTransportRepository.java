package com.example.zippickT.domain.User.repository;

import com.example.zippickT.domain.User.entity.UserTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransportRepository extends JpaRepository<UserTransport, Long> {
}
