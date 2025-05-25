package com.example.zippickT.domain.house.repository;

import com.example.zippickT.domain.house.entity.UserHouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserHouseInfoRepository extends JpaRepository<UserHouseInfo, Long> {
    List<UserHouseInfo> findByMemberId(Long id);
}
