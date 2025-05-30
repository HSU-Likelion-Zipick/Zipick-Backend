package com.example.zippickT.domain.User.web.controller;

import com.example.zippickT.domain.User.service.UserService;
import com.example.zippickT.domain.User.web.dto.CreateUserReq;
import com.example.zippickT.domain.User.web.dto.CreateUserRes;
import com.example.zippickT.domain.User.web.dto.UserNickNameRes;
import com.example.zippickT.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    //의존성 주입
    private final UserService userService;

    //사용자 정보 생성
    @PostMapping("/MyData")
    public ResponseEntity<SuccessResponse<?>> createUserDate(
            @RequestBody @Valid CreateUserReq createUserReq
    ){
        CreateUserRes createUserRes = userService.createUser(createUserReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createUserRes));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<SuccessResponse<?>> getUserNickName(
            @PathVariable Long user_id
    ) {
        UserNickNameRes userNicknameRes = userService.getNickName(user_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(userNicknameRes));
    }
}
