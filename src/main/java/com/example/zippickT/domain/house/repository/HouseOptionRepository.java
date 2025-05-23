package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.HouseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HouseOptionRepository extends JpaRepository<HouseOption, Long> {
    Optional<HouseOption> findByOptionName(String optionName);
}
