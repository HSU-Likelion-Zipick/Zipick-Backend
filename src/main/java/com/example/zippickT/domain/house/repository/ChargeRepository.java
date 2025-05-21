package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    Optional<Charge> findByChargeName(String chargeName);
}
