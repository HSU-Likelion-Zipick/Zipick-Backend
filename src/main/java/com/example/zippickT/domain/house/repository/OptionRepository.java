package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Optional<Option> findByOptionName(String optionName);
}
