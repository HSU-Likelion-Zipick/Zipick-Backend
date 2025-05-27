package com.example.zippickT.domain.house.service;

import com.example.zippickT.domain.User.entity.Member;
import com.example.zippickT.domain.User.exception.UserNotFoundException;
import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.house.entity.*;
import com.example.zippickT.domain.house.exception.ChargeNotFoundException;
import com.example.zippickT.domain.house.exception.OptionNotFoundException;
import com.example.zippickT.domain.house.repository.*;
import com.example.zippickT.domain.house.web.dto.CountHouseDateRes;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataReq;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserHouseServiceImp implements UserHouseService {
    private final ChargeRepository chargeRepository;
    private final HouseOptionRepository houseOptionRepository;
    private final UserHouseInfoRepository userHouseInfoRepository;
    private final UserRepository userRepository;
    private final UserChargeRepository userChargeRepository;
    private final UserOptionRepository userOptionRepository;

    @Override
    @Transactional
    public CreateHouseDataRes save(Long userId, CreateHouseDataReq createHouseDataReq) {
        Member member = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserHouseInfo userHouseInfo = UserHouseInfo.builder()
                .member(member)
                .houseName(createHouseDataReq.getHouseName())
                .kind(createHouseDataReq.getKind())
                .size(createHouseDataReq.getManagement())
                .management(createHouseDataReq.getManagement())
                .parking(createHouseDataReq.isParking())
                .elevator(createHouseDataReq.isElevator())
                .direction(createHouseDataReq.getDirection())
                .completion_date(createHouseDataReq.getCompletion_date())
                .year_rent(createHouseDataReq.getYear_rent())
                .monthly_rent(createHouseDataReq.getMonthly_rent())
                .deposit(createHouseDataReq.getDeposit())
                .full_floors(createHouseDataReq.getFull_floors())
                .floor(createHouseDataReq.getFloor())
                .build();

        UserHouseInfo res = userHouseInfoRepository.save(userHouseInfo);

        for(String chargeName : createHouseDataReq.getSelectedCharges()){
            Charge charge = chargeRepository.findByChargeName(chargeName)
                    .orElseThrow(ChargeNotFoundException::new);
            UserCharge userCharge = new UserCharge();
            userCharge.setUserHouseInfo(userHouseInfo);
            userCharge.setCharge(charge);
            userChargeRepository.save(userCharge);

        }

        for(String optionName: createHouseDataReq.getSelectedOptions()){
            HouseOption option = houseOptionRepository.findByOptionName(optionName)
                    .orElseThrow(OptionNotFoundException::new);
            UserOption userOption = new UserOption();
            userOption.setUserHouseInfo(userHouseInfo);
            userOption.setHouseOption(option);
            userOptionRepository.save(userOption);

        }

        return new CreateHouseDataRes(res.getId(),res.getMember().getId());
    }

    @Override
    public CountHouseDateRes countHouse(Long userId) {
        int cnt = userHouseInfoRepository.countByMemberId(userId);
        return new CountHouseDateRes(userId, cnt);
    }
}
