package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.UserCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChargeRepository extends JpaRepository<UserCharge, Long> {
}
