package com.example.zippickT.domain.User.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserReq{

    @NotBlank(message = "닉네임은 필수값입니다.")
    private String nickname;

    @NotBlank(message = "성별은 필수값입니다.")
    private String sex;

    @NotNull(message = "나이는 0 이상이어야 합니다.")
    @Min(0)
    private int age;

    @NotBlank(message = "직업은 필수값입니다.")
    private String job;

    @NotNull(message = "월수입은 0 이상이여야 합니다.")
    @Min(0)
    private int month_income;

    @NotNull(message = "여유자금은 0 이상이어야 합니다.")
    @Min(0)
    private int reserve_money;

    @NotBlank(message = "이동수단은 필수값입니다.")
    private String transport;

    @NotBlank(message = "생활패턴은 필수값입니다.")
    private String lifestyle_pattern;
}
