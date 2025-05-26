package com.example.zippickT.domain.gpt.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GptErrorCode implements BaseResponseCode {
    TRANSLATION_HOUSE_NOT_FOUND_404("RANSLATION_HOUSE_NOT_FOUND_404",404,"추천된 집의 정보가 존재하지 않습니다."),
    NOT_INSERT_YEAR_MONTHLY_MONEY_403("NOT_INSERT_YEAR_MONTHLY_MONEY_403",403,"전세 또는 월세 정보가 존재하지 않습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
