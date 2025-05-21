package com.example.zippickT.domain.User.web.controller;

import com.example.zippickT.domain.User.entity.User;
import com.example.zippickT.domain.User.service.UserService;
import com.example.zippickT.domain.User.web.dto.CreateUserReq;
import com.example.zippickT.domain.User.web.dto.CreateUserRes;
import com.example.zippickT.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    //의존성 주입
    private final UserService userService;

    //사용자 정보 생성
    @GetMapping("/MyData")
    public ResponseEntity<SuccessResponse<?>> createUserDate(
            @RequestBody @Valid CreateUserReq createUserReq
    ){
        CreateUserRes createUserRes = userService.createUser(createUserReq);

        return null;
    }

}
