package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
    Optional<Charge> findByChargeName(String chargeName);
}
