package com.example.zippickT.domain.User.web.dto;

public record CreateUserRes(
        Long userId,
        String nickname,
        String sex,
        int age,
        String job,
        int month_income,
        int reserve_money,
        String transport,
        String lifestyle_pattern
) {

}
