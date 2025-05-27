package com.example.zippickT.domain.house.web.controller;

import com.example.zippickT.domain.house.service.UserHouseService;
import com.example.zippickT.domain.house.web.dto.CountHouseDateRes;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataReq;
import com.example.zippickT.domain.house.web.dto.CreateHouseDataRes;
import com.example.zippickT.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("/{user_id}/HouseCount")
    public ResponseEntity<SuccessResponse<?>> getUserHouseCount(@PathVariable Long user_id){
        CountHouseDateRes createHouseDateRes = userHouseService.countHouse(user_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(createHouseDateRes));
    }
}
