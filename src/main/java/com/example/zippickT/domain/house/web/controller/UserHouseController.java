package com.example.zippickT.domain.house.web.controller;

import com.example.zippickT.domain.house.entity.UserHouseInfo;
import com.example.zippickT.domain.house.service.UserHouseService;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataReq;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataRes;
import com.example.zippickT.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserHouseController {
    private final UserHouseService userHouseService;
    //UserHouseService 떼어와서 의존성주입해야함

    @PostMapping("/{user_id}/HouseData")
    public ResponseEntity<SuccessResponse<?>> createUserHouseInfo(@PathVariable Long user_id,
                                                                  @RequestBody CreateHouseDataReq createHouseDataReq){
        CreateHouseDataRes createHouseDataRes = userHouseService.save(user_id, createHouseDataReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(createHouseDataRes));
    }
}
