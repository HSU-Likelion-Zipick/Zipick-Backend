package com.example.zippickT.domain.User.service;

import com.example.zippickT.domain.User.web.dto.CreateUserReq;
import com.example.zippickT.domain.User.web.dto.CreateUserRes;
import com.example.zippickT.domain.User.web.dto.UserNickNameRes;
import jakarta.validation.Valid;

public interface UserService {

    CreateUserRes createUser(@Valid CreateUserReq createUserReq);

    UserNickNameRes getNickName(Long userId);
}
