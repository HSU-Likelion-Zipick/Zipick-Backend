package com.example.zippickT.domain.house.service;

import com.example.zippickT.domain.User.entity.User;
import com.example.zippickT.domain.User.exception.UserNotFoundException;
import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.house.entity.*;
import com.example.zippickT.domain.house.exception.ChargeNotFoundException;
import com.example.zippickT.domain.house.exception.OptionNotFoundException;
import com.example.zippickT.domain.house.repository.*;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataReq;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserHouseServiceImp implements UserHouseService {
    private ChargeRepository chargeRepository;
    private HouseOptionRepository houseOptionRepository;
    private UserHouseInfoRepository userHouseInfoRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public CreateHouseDataRes save(Long userId, CreateHouseDataReq createHouseDataReq) {

        //유저가 실제로 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserHouseInfo userHouseInfo = UserHouseInfo.builder()
                .user(user)
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

        //Charge DB에 유저가 입력한 ChargeName이 존재하는지 확인
        for(String chargeName : createHouseDataReq.getSelectedCharges()){
            Charge charge = chargeRepository.findByChargeName(chargeName)
                    .orElseThrow(ChargeNotFoundException::new);
            UserCharge userCharge = new UserCharge();
            userCharge.setUserHouseInfo(userHouseInfo);
            userCharge.setCharge(charge);

            userHouseInfo.getUserCharges().add(userCharge);
        }

        //Option DB에 유저가 입력한 OptionName이 존재하는지 확인
        for(String optionName: createHouseDataReq.getSelectedOptions()){
            HouseOption option = houseOptionRepository.findByOptionName(optionName)
                    .orElseThrow(OptionNotFoundException::new);
            UserOption userOption = new UserOption();
            userOption.setUserHouseInfo(userHouseInfo);
            userOption.setOption(option);

            userHouseInfo.getUserOptions().add(userOption);
        }
        UserHouseInfo res = userHouseInfoRepository.save(userHouseInfo);
        return new CreateHouseDataRes(res.getId(),res.getUser().getId());
    }
    //Reposity 가져와서 의존성 주입해야함.
}
