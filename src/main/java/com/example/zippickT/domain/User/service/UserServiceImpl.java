package com.example.zippickT.domain.User.service;

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
        return null;
    }
}
