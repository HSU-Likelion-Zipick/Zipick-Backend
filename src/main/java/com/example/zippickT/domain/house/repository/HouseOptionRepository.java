package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.HouseOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseOptionRepository extends JpaRepository<HouseOption, Long> {
    Optional<HouseOption> findByOptionName(String optionName);
}
