package com.example.zippickT.domain.house.service;

import com.example.zippickT.domain.house.entity.UserHouseInfo;
import com.example.zippickT.domain.house.web.dto.CountHouseDateRes;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataReq;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataRes;

public interface UserHouseService {
    CreateHouseDataRes save(Long userId, CreateHouseDataReq createHouseDataReq);

    CountHouseDateRes countHouse(Long userId);
}
