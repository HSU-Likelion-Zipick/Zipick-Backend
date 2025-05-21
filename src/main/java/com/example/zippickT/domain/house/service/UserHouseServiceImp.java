package com.example.zippickT.domain.house.service;

import com.example.zippickT.domain.house.repository.UserChargeRepository;
import com.example.zippickT.domain.house.repository.UserHouseInfoRepository;
import com.example.zippickT.domain.house.repository.UserOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHouseServiceImp implements UserHouseService {
    private UserHouseInfoRepository userHouseInfoRepository;
    private UserChargeRepository userChargeRepository;
    private UserOptionRepository userOptionRepository;
    //Reposity 가져와서 의존성 주입해야함.
}
