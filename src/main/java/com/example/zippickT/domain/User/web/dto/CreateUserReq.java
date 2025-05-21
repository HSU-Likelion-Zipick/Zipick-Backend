package com.example.zippickT.domain.User.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserReq{

    @NotBlank(message = "닉네임은 필수값입니다.")
    private String nickname;

    @NotBlank(message = "성별은 필수값입니다.")
    private String sex;

    @NotBlank(message = "나이는 필수값입니다.")
    private int age;

    @NotBlank(message = "직업은 필수값입니다.")
    private String job;

    @NotBlank(message = "월수입은 필수값입니다.")
    private int month_income;

    @NotBlank(message = "여유자금은 필수값입니다.")
    private int reserve_money;

    @NotBlank(message = "이동수단은 필수값입니다.")
    private String transport;

    @NotBlank(message = "생활패턴은 필수값입니다.")
    private String lifestyle_pattern;
}
