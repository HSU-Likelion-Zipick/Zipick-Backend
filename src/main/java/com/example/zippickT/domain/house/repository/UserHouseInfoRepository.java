package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.UserHouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHouseInfoRepository extends JpaRepository<UserHouseInfo, Long> {
}
