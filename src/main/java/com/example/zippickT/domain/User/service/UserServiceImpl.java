package com.example.zippickT.domain.User.service;

import com.example.zippickT.domain.User.entity.*;
import com.example.zippickT.domain.User.exception.TransportNotFoundException;
import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.User.web.dto.CreateUserReq;
import com.example.zippickT.domain.User.web.dto.CreateUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public CreateUserRes createUser(CreateUserReq createUserReq) {
        User user = User.builder()
                .nickname(createUserReq.getNickname())
                .sex(Gender.valueOf(createUserReq.getSex()))
                .age(createUserReq.getAge())
                .job(Job.valueOf(createUserReq.getJob()))
                .month_income(createUserReq.getMonth_income())
                .reserve_money(createUserReq.getReserve_money())
                .transport(Transport.valueOf(createUserReq.getTransport()))
                .lifestyle_pattern(LifeStyle.valueOf(createUserReq.getLifestyle_pattern()))
                .build();

        User savedUser = userRepository.save(user);
        return new CreateUserRes(savedUser.getId());
    }
}
