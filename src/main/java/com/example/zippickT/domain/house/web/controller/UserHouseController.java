package com.example.zippickT.domain.house.web.controller;

import com.example.zippickT.domain.house.service.UserHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserHouseController {
    private UserHouseService userHouseService;
    //UserHouseService 떼어와서 의존성주입해야함
}
