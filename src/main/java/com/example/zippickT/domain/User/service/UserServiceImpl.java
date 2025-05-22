package com.example.zippickT.domain.User.service;

import com.example.zippickT.domain.User.entity.*;
import com.example.zippickT.domain.User.exception.GenderNotFoundException;
import com.example.zippickT.domain.User.exception.JobNotFoundException;
import com.example.zippickT.domain.User.exception.LifeStyleNotFoundException;
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

        //enum에 없는 성별인지 검사
        Gender gender;
        try{
            gender = Gender.valueOf(createUserReq.getSex());
        } catch (IllegalArgumentException e){
            //없다면 404에러 발생
            throw new GenderNotFoundException();
        }

        //enum에 없는 직업인지 검사
        Job job;
        try{
            job = Job.valueOf(createUserReq.getJob());
        }catch (IllegalArgumentException e){
            //없다면 404에러 발생
            throw new JobNotFoundException();
        }

        //enum에 없는 이동수단인지 검사
        Transport transport;
        try{
            transport = Transport.valueOf(createUserReq.getTransport());
        }catch (IllegalArgumentException e){
            //없다면 404에러 발생
            throw new TransportNotFoundException();
        }

        //enum에 없는 생활패턴인지 검사
        LifeStyle lifeStyle;
        try{
            lifeStyle = LifeStyle.valueOf(createUserReq.getLifestyle_pattern());
        }catch (IllegalArgumentException e){
            //없다면 404에러 발생
            throw new LifeStyleNotFoundException();
        }

        User user = User.builder()
                .nickname(createUserReq.getNickname())
                .sex(gender)
                .age(createUserReq.getAge())
                .job(job)
                .month_income(createUserReq.getMonth_income())
                .reserve_money(createUserReq.getReserve_money())
                .transport(transport)
                .lifestyle_pattern(lifeStyle)
                .build();

        User savedUser = userRepository.save(user);
        return new CreateUserRes(savedUser.getId());
    }
}
